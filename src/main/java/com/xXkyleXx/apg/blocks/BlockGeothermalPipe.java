package com.xXkyleXx.apg.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGeothermalPipe extends Blockapg {
	
	
	public BlockGeothermalPipe() {
		super();
		this.setBlockName("geothermalPipe");	
		this.setHardness(1.5F);
	}	 

	
	@SideOnly(Side.CLIENT)
	IIcon[] icons;
	
	@SideOnly(Side.CLIENT)	
	@Override
	public void registerBlockIcons(IIconRegister iIconRegister) {
		icons = new IIcon[2];
		
		icons[0] = iIconRegister.registerIcon("apg:geothermalPipe_Top");
		icons[1] = iIconRegister.registerIcon("apg:geothermalPipe_Side");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 1 || side == 0) {
			return icons[0];
		}else{
			return icons[1];
		
		}
	}
	
	
	
	
	
}
