package com.xXkyleXx.apg.tileentities;

import java.util.HashSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fluids.FluidTank;

public class TileEntitySteamOutput extends TileEntity {

	
	
	private boolean needsLoadFromCoords = false;
	
	public HashSet<TileEntityGeothermalPump> controlPump = new HashSet();
	public int[][] geothermalPumpCoords;
	public FluidTank fluidTank = new FluidTank(10000);
	
	public void updateEntity(){
		if(needsLoadFromCoords){
			loadFromCoords();
			needsLoadFromCoords = false;
		}
	}
	
	
	public void loadFromCoords() {
		if(geothermalPumpCoords != null) {
			for(int i=0; i<geothermalPumpCoords.length; i++){
				int	x = geothermalPumpCoords[i][0];
				int	y = geothermalPumpCoords[i][1];
				int	z = geothermalPumpCoords[i][2];
				controlPump.add((TileEntityGeothermalPump) worldObj.getTileEntity(x, y, z));
			}
		}
	}
	
	
	
	@Override
	public void invalidate(){
		if(controlPump != null){
			for(TileEntityGeothermalPump cpump: controlPump) {
				cpump.steamOutputs.remove(this);
			}
		}
		super.invalidate();
	}
	

	
	
	
	
	public void setControlPumps() {
		controlPump = findControlPump();
	}
	
	
	
	public HashSet<TileEntityGeothermalPump> findControlPump(){ 

		HashSet<TileEntityGeothermalPump> cpumps = new HashSet();

		for(int i=0; i<20; i++) {
			for(int j=0; j<20; j++) {
				TileEntity TE = worldObj.getTileEntity((xCoord-10)+i, yCoord, (zCoord-10)+j);
				if(TE != null && TE instanceof TileEntityGeothermalPump && TE.getDistanceFrom(xCoord, yCoord, zCoord) > 25) {
					TileEntityGeothermalPump cpump = (TileEntityGeothermalPump) TE;	
					cpumps.add(cpump);
				}
			}

		}



		return cpumps;
	} 
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);      
        
         if(nbtTagCompound.hasKey("pumps")) {
        	NBTTagList tagList = nbtTagCompound.getTagList("pumps", NBT.TAG_COMPOUND);
        	geothermalPumpCoords = new int[tagList.tagCount()][3];
        	for(int i = 0; i < tagList.tagCount(); i++) {
        		NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
        		int x = tagCompound.getInteger("x");
        		int y = tagCompound.getInteger("y");
        		int z = tagCompound.getInteger("z");
        		
        		geothermalPumpCoords[i][0] = x; 
				geothermalPumpCoords[i][1] = y;
				geothermalPumpCoords[i][2] = z;
				
				needsLoadFromCoords = true;
        		
        	}
        } 
	}
	

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound){
		super.writeToNBT(nbtTagCompound);
		
			NBTTagList pumps = new NBTTagList();
			nbtTagCompound.setTag("pumps", pumps);

			for(TileEntityGeothermalPump pump: controlPump) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setInteger("x", pump.xCoord);
				tagCompound.setInteger("y", pump.yCoord);
				tagCompound.setInteger("z", pump.zCoord);

				pumps.appendTag(tagCompound);
			}
		}
	
}
	
