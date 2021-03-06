package com.xXkyleXx.apg.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.xXkyleXx.apg.tileentities.TileEntityGeothermalPump;
import com.xXkyleXx.apg.tileentities.TileEntitySteamOutput;

public class BlockSteamOutput extends Blockapg implements ITileEntityProvider {

	public BlockSteamOutput() {
		super();
		this.setBlockName("steamOutput");	
		this.setHardness(1.5F);
	}
	
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(!world.isRemote) {
			TileEntitySteamOutput output = (TileEntitySteamOutput) world.getTileEntity(x, y, z);
			player.addChatMessage(new ChatComponentText("x:" + x + " y:" + y + " z:" + z));
			player.addChatMessage(new ChatComponentText("Number of Pumps: "+ output.controlPump.size()));
			player.addChatMessage(new ChatComponentText("Steam Level:"+output.fluidTank.getFluidAmount()));
			
			}
		return false;
		}	
	
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntitySteamOutput output = (TileEntitySteamOutput) world.getTileEntity(x, y, z);
		output.setControlPumps();
		for(TileEntityGeothermalPump pumps: output.controlPump) {
		pumps.steamOutputs.add(output);
		
		}
	}


	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		
		return new TileEntitySteamOutput();
	}

}
