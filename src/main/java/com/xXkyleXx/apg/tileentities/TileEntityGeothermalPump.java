package com.xXkyleXx.apg.tileentities;

import java.util.HashSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants.NBT;


public class TileEntityGeothermalPump extends TileEntity {
	
	public boolean needsRebuild = true;
	private boolean needsLoadFromCoords = false;
	
	public HashSet<TileEntitySteamOutput> steamOutputs = new HashSet();
	public int[][] steamOutputCoords;

	public TileEntityGeothermalPump() {
		
	}

	
	@Override
	public void invalidate(){
		if(steamOutputs != null){
			for(TileEntitySteamOutput outputs: steamOutputs) {
				outputs.controlPump.remove(this);
			}
		}
		super.invalidate();
	}
	
	
	
	@Override
	public void updateEntity() {
		if(needsRebuild){
			rebuildValidSteamOutputs();
			needsRebuild = false;
		}
		if(needsLoadFromCoords){
			loadFromCoords();
			needsLoadFromCoords = false;
		}
	}


	public HashSet getValidSteamOutputs() {
		HashSet SO = new HashSet();

		for(int i=0; i<20; i++) {
			for(int j=0; j<20; j++) {
				TileEntity TE = worldObj.getTileEntity((xCoord-10)+i, yCoord, (zCoord-10)+j);
				if(TE != null && TE instanceof TileEntitySteamOutput && TE.getDistanceFrom(xCoord, yCoord, zCoord) > 25) {
					TileEntitySteamOutput output = (TileEntitySteamOutput) TE;
					
						SO.add(TE);										
				}
			}
		}

		return SO;
		}


	public int getAverageHieght() {
		return worldObj.provider.getAverageGroundLevel();
		
	}
	
	
	
	public void rebuildValidSteamOutputs() {
			steamOutputs = getValidSteamOutputs();
			notifySteamOutputs();
	}


	public void getSteamOutputCoords() {
		steamOutputCoords = new int[steamOutputs.size()][3];
		for(TileEntitySteamOutput outputs: steamOutputs){
			for(int i=0; i<steamOutputs.size(); i++){
				steamOutputCoords[i][0] = outputs.xCoord; 
				steamOutputCoords[i][1] = outputs.yCoord;
				steamOutputCoords[i][2] = outputs.zCoord;
			}
		}
	}
	
	public void loadFromCoords() {
		if(steamOutputCoords != null) {
			for(int i=0; i<steamOutputCoords.length; i++){
				int	x = steamOutputCoords[i][0];
				int	y = steamOutputCoords[i][1];
				int	z = steamOutputCoords[i][2];
				steamOutputs.add((TileEntitySteamOutput) worldObj.getTileEntity(x, y, z));
			}
		}
	}
	
	public void notifySteamOutputs(){
		if(steamOutputs != null){
			for(TileEntitySteamOutput outputs: steamOutputs) {
				if(outputs.controlPump != null){
				outputs.controlPump.add(this);
			}
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
        
        needsRebuild = nbtTagCompound.getBoolean("rebuild");
        
         if(nbtTagCompound.hasKey("outputs")) {
        	NBTTagList tagList = nbtTagCompound.getTagList("outputs", NBT.TAG_COMPOUND);
        	steamOutputCoords = new int[tagList.tagCount()][3];
        	for(int i = 0; i < tagList.tagCount(); i++) {
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
	public void writeToNBT(NBTTagCompound nbtTagCompound){
		super.writeToNBT(nbtTagCompound);
		
		nbtTagCompound.setBoolean("rebuild", needsRebuild);
		
			NBTTagList outputs = new NBTTagList();
			nbtTagCompound.setTag("outputs", outputs);

			for(TileEntitySteamOutput output: steamOutputs) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setInteger("x", output.xCoord);
				tagCompound.setInteger("y", output.yCoord);
				tagCompound.setInteger("z", output.zCoord);

				outputs.appendTag(tagCompound);
			}
		}
	
}
	
