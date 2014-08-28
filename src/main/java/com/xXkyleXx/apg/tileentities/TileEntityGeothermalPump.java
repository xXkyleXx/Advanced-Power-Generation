package com.xXkyleXx.apg.tileentities;

import java.util.HashSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.xXkyleXx.apg.blocks.ModBlocks;
import com.xXkyleXx.apg.fluids.ModFluids;

public class TileEntityGeothermalPump extends TileEntity implements IFluidHandler {

	
	private int searchSize = 30;
	private ThreadValidPipeLength thread;
	private int validPipeLength = 0;
	public FluidTank fluidTank = new FluidTank(10000);
	private int maxTransferRate = 10;
	//private int updateTime = 10;
	
	public HashSet<TileEntitySteamOutput> steamOutputs = new HashSet();
	public int[][] steamOutputCoords;
	private boolean needsLoadFromCoords = false;
	
	public TileEntityGeothermalPump() {

	}

	@Override
	public void invalidate() {
		if (steamOutputs != null) {
			for (TileEntitySteamOutput outputs : steamOutputs) {
				outputs.controlPump.remove(this);
			}
		}
		super.invalidate();
	}

	@Override
	public void updateEntity() {
		
		if (needsLoadFromCoords) {
			loadFromCoords();
			needsLoadFromCoords = false;
		}
		if (worldObj.getWorldTime() % 100 == 0) {
			if (getPipeLength() > 15 && hasGrate()) {
				resetThread();
				thread.start();
			}else{
				validPipeLength = 0;
			}
		}
		if(fluidTank.getFluidAmount() > 0 && steamOutputs.size() > 0) {
			int drainRate = Math.min(maxTransferRate, fluidTank.getFluidAmount());
			int fillRate = drainRate / steamOutputs.size();
			fluidTank.drain(drainRate, true);

			for(TileEntitySteamOutput output: steamOutputs) {
				Fluid fluid = ModFluids.steam;
				FluidStack fluidstack = new FluidStack(fluid, fillRate);
				output.fluidTank.fill(fluidstack, true);
			}
		}

	}

	public HashSet getValidSteamOutputs() {
		HashSet SO = new HashSet();

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				TileEntity TE = worldObj.getTileEntity((xCoord - 10) + i, yCoord, (zCoord - 10) + j);
				if (TE != null && TE instanceof TileEntitySteamOutput && TE.getDistanceFrom(xCoord, yCoord, zCoord) > 25) {
					TileEntitySteamOutput output = (TileEntitySteamOutput) TE;

					SO.add(TE);
				}
			}
		}

		return SO;
	}


	public void rebuildValidSteamOutputs() {
		steamOutputs = getValidSteamOutputs();
		notifySteamOutputs();
	}

	public void getSteamOutputCoords() {
		steamOutputCoords = new int[steamOutputs.size()][3];
		for (TileEntitySteamOutput outputs : steamOutputs) {
			for (int i = 0; i < steamOutputs.size(); i++) {
				steamOutputCoords[i][0] = outputs.xCoord;
				steamOutputCoords[i][1] = outputs.yCoord;
				steamOutputCoords[i][2] = outputs.zCoord;
			}
		}
	}

	public void loadFromCoords() {
		if (steamOutputCoords != null) {
			for (int i = 0; i < steamOutputCoords.length; i++) {
				int x = steamOutputCoords[i][0];
				int y = steamOutputCoords[i][1];
				int z = steamOutputCoords[i][2];
				steamOutputs.add((TileEntitySteamOutput) worldObj.getTileEntity(x, y, z));
			}
		}
	}

	public void notifySteamOutputs() {
		if (steamOutputs != null) {
			for (TileEntitySteamOutput outputs : steamOutputs) {
				if (outputs.controlPump != null) {
					outputs.controlPump.add(this);
				}
			}
		}
	}

	public int getPipeLength() {
		int y = yCoord - 1;
		int length = 0;
		while (worldObj.getBlock(xCoord, y, zCoord) == ModBlocks.geothermalPipe) {
			length++;
			y--;

		}
		return length;
	}

	public boolean hasGrate() {
		return worldObj.getBlock(xCoord, yCoord - getPipeLength() - 1, zCoord) == ModBlocks.grate;
	}

	public void setValidPipeLength(int length) {
		validPipeLength = length;
	}
	
	public int getValidPipeLength() {
		return validPipeLength;
	}
	public int getSearchSize() {
		return searchSize;
	}
	
	private void resetThread() {
		thread = new ThreadValidPipeLength(this);
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);

		
		if (nbtTagCompound.hasKey("outputs")) {
			NBTTagList tagList = nbtTagCompound.getTagList("outputs", NBT.TAG_COMPOUND);
			steamOutputCoords = new int[tagList.tagCount()][3];
			for (int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
				int x = tagCompound.getInteger("x");
				int y = tagCompound.getInteger("y");
				int z = tagCompound.getInteger("z");

				steamOutputCoords[i][0] = x;
				steamOutputCoords[i][1] = y;
				steamOutputCoords[i][2] = z;

				needsLoadFromCoords = true;

			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);

	
		NBTTagList outputs = new NBTTagList();
		nbtTagCompound.setTag("outputs", outputs);

		for (TileEntitySteamOutput output : steamOutputs) {
			NBTTagCompound tagCompound = new NBTTagCompound();
			tagCompound.setInteger("x", output.xCoord);
			tagCompound.setInteger("y", output.yCoord);
			tagCompound.setInteger("z", output.zCoord);

			outputs.appendTag(tagCompound);
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(canFill(from, resource.getFluid())) {
			return fluidTank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(from.ordinal() == 1 && fluid.getName() == "water") {
			return true;
		}
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { fluidTank.getInfo() };
	}

}
