package com.xXkyleXx.apg.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySteamOutput extends TileEntity {

	public TileEntityGeothermalPump controlPump;
	
	
	
	
	@Override
	public void invalidate(){
		if(controlPump != null){
			controlPump.SteamOutputs.remove(this);
		}
		super.invalidate();
	}
	

	
	
	
	
	public void setControlPump() {
		controlPump = findControlPump();
	}
	
	
	
	public TileEntityGeothermalPump findControlPump(){ 
		
		
	double dist = 0;
	double closest = Integer.MAX_VALUE;
	TileEntityGeothermalPump C = null;

	for(int i=0; i<20; i++) {
		for(int j=0; j<20; j++) {
			TileEntity TE = worldObj.getTileEntity((xCoord-10)+i, yCoord, (zCoord-10)+j);
			if(TE != null && TE instanceof TileEntityGeothermalPump && TE.getDistanceFrom(xCoord, yCoord, zCoord) > 25) {
				dist = TE.getDistanceFrom(xCoord, yCoord, zCoord);
				if(dist < closest) {
					C = (TileEntityGeothermalPump) TE;
				}
			}


		}

	}



	return C;
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
