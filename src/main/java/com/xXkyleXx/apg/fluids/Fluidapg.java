package com.xXkyleXx.apg.fluids;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.xXkyleXx.apg.AdvancedPowerGeneration;
import com.xXkyleXx.apg.items.CreativeTabapg;

public class Fluidapg extends BlockFluidClassic {
	
	public static Fluid fluid;
	
	public Fluidapg(Fluid fluid, Material material) {
		super(fluid, material);
		this.fluid = fluid;
		setCreativeTab(CreativeTabapg.APG_TAB);
	}
	
	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", AdvancedPowerGeneration.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(fluid.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	

}
