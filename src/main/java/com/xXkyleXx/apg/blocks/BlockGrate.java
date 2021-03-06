package com.xXkyleXx.apg.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrate extends Blockapg {
	
	public BlockGrate() {
		super();
		this.setBlockName("grate");	
		this.setHardness(1.5F);
	}	 

	
	@SideOnly(Side.CLIENT)
	IIcon[] icons;
	
	@SideOnly(Side.CLIENT)	
	@Override
	public void registerBlockIcons(IIconRegister iIconRegister) {
		icons = new IIcon[2];
		
		icons[0] = iIconRegister.registerIcon("apg:grate_Top");
		icons[1] = iIconRegister.registerIcon("apg:grate_Side");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return icons[0];
		}else{
			return icons[1];
		
		}
	}
	

}
