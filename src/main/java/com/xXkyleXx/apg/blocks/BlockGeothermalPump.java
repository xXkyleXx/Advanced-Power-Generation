package com.xXkyleXx.apg.blocks;

import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.xXkyleXx.apg.AdvancedPowerGeneration;
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
	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityGeothermalPump pump = (TileEntityGeothermalPump) world.getTileEntity(x, y, z);
		pump.rebuildValidSteamOutputs();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if(!world.isRemote) {

			TileEntityGeothermalPump pump = (TileEntityGeothermalPump) world.getTileEntity(x, y, z);
		    if(pump.steamOutputs != null) {
			player.addChatMessage(new ChatComponentText("Number of Steam Outputs: "+pump.steamOutputs.size()));
			player.addChatMessage(new ChatComponentText("Valid Pipe Length:"+pump.getValidPipeLength()));
			player.addChatMessage(new ChatComponentText("Water Level:"+pump.fluidTank.getFluidAmount()));
			
			}
		}	

		


		return false;
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativetabs, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
	}
	
	@Override
	public int damageDropped(int meta) {
	return meta;
	}
	
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGeothermalPump();
	}
}
