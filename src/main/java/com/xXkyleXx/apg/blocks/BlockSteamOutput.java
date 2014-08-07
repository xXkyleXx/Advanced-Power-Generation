package com.xXkyleXx.apg.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.xXkyleXx.apg.tileentities.TileEntitySteamOutput;

public class BlockSteamOutput extends Blockapg implements ITileEntityProvider {

	public BlockSteamOutput() {
		super();
		this.setBlockName("steamOutput");	
		this.setHardness(1.5F);
	}
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);

	}


	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		
		return new TileEntitySteamOutput();
	}

}
