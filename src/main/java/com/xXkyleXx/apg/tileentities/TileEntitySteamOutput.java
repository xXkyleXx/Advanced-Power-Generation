package com.xXkyleXx.apg.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySteamOutput extends TileEntity {

	public TileEntityGeothermalPump controlPump;
	
	
	
	
	@Override
	public void invalidate(){
		if(controlPump != null){
			controlPump.rebuildValidSteamOutputs();
		}
		super.invalidate();
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
