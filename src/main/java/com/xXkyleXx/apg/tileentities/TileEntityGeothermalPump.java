package com.xXkyleXx.apg.tileentities;

import java.util.ArrayList;
import java.util.HashSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;


public class TileEntityGeothermalPump extends TileEntity {
	
	
	public HashSet<TileEntitySteamOutput> SteamOutputs;


	public TileEntityGeothermalPump() {

	}

	@Override
	public void updateEntity() {

	}


	public HashSet getValidSteamOutputs() {
		HashSet SO = new HashSet();

		for(int i=0; i<20; i++) {
			for(int j=0; j<20; j++) {
				TileEntity TE = worldObj.getTileEntity((xCoord-10)+i, yCoord, (zCoord-10)+j);
				if(TE != null && TE instanceof TileEntitySteamOutput && TE.getDistanceFrom(xCoord, yCoord, zCoord) > 25) {
						SO.add(TE);					
				}
			}
		}


		return SO;
	}


	public void rebuildValidSteamOutputs() {
			SteamOutputs = getValidSteamOutputs();
			notifySteamOutputs();
	}


	public void notifySteamOutputs(){
		if(SteamOutputs != null){
			for(TileEntitySteamOutput outputs: SteamOutputs) {
				outputs.controlPump = (TileEntityGeothermalPump) worldObj.getTileEntity(xCoord, yCoord, zCoord);
			}
		}
	}


	 
	 
	 
	 /*
	public TileEntity getClosestSteamOutput(World world, int x, int y, int z){ 
		
		
		double dist = 0;
		double closest = Integer.MAX_VALUE;
		TileEntity A = world.getTileEntity(x, y, z);
		TileEntity B = null;
		TileEntity C = null;
		
		for(int i=0; i<20; i++) {
			for(int j=0; j<20; j++) {
				Block block = world.getBlock((x-10)+i, y, (z-10)+j);
				if (block.hasTileEntity(0)) {
					TileEntity TE = world.getTileEntity((x-10)+i, y, (z-10)+j);
					if(TE instanceof TileEntitySteamOutput) {
						B = TE;
						dist = getDistance(A,B);
						if(dist < closest) { 
							C = TE;
						}


					}

				}
			}
		}
		return C;
	} 

	
	public double getDistance(TileEntity A, TileEntity B) {
		return Math.sqrt((B.xCoord - A.xCoord)*(B.xCoord - A.xCoord)+(B.yCoord - A.yCoord)*(B.yCoord - A.yCoord)+(B.zCoord - A.yCoord)*(B.zCoord - A.yCoord));
	} */
	
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound){
        super.writeToNBT(nbtTagCompound);
	}
	
}
