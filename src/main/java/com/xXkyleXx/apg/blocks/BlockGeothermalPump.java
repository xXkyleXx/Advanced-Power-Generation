package com.xXkyleXx.apg.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.xXkyleXx.apg.tileentities.TileEntityGeothermalPump;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGeothermalPump extends Blockapg implements ITileEntityProvider {

	
	public BlockGeothermalPump() {
		super();
		this.setBlockName("geothermalPump");	
		this.setHardness(1.5F);
	}	 
	
	
	@SideOnly(Side.CLIENT)
	IIcon[] icons;
	
	@SideOnly(Side.CLIENT)	
	@Override
	public void registerBlockIcons(IIconRegister iIconRegister) {
		icons = new IIcon[2];
		
		icons[0] = iIconRegister.registerIcon("apg:geothermalpump_top");
		icons[1] = iIconRegister.registerIcon("apg:geothermalpump_side");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return icons[0];
		}else{
			return icons[1];
		
		}
	}
	
	
	@Override
	 public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(!world.isRemote) {
			TileEntity TE = getClosestSteamOutput(world, x, y, z);
			System.out.print(TE.xCoord);
			
		}
	    
		
			return false;
	    }

	
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
					if(TE instanceof TileEntityGeothermalPump) {
						B = TE;
						dist = getDistance(A,B);
						if(dist < closest) { 
							C = TE;
						}


						//outputs++;

					}

				}
			}
		}
		return C;
	}

	public double getDistance(TileEntity A, TileEntity B) {
		return Math.sqrt((B.xCoord - A.xCoord)*(B.xCoord - A.xCoord)+(B.yCoord - A.yCoord)*(B.yCoord - A.yCoord)+(B.zCoord - A.yCoord)*(B.zCoord - A.yCoord));
	}
	
	
	
	
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGeothermalPump();
	}
}
