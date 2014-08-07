package com.xXkyleXx.apg.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySteamOutput extends TileEntity {

	public TileEntityGeothermalPump controlPump;
	
	public void notifyOnBreak() {
		if(controlPump != null){
			controlPump.rebuildValidSteamOutputs();;
		}
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound){
        super.writeToNBT(nbtTagCompound);
	}
	
	
}
