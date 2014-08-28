package com.xXkyleXx.apg.fluids;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.xXkyleXx.apg.AdvancedPowerGeneration;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(AdvancedPowerGeneration.MODID)
public class ModFluids {

	public static Fluid steam;

	public static Fluidapg blockSteam;

	public static void init() {

		steam = new Fluid("steam").setDensity(-100).setLuminosity(0).setTemperature(1000).setViscosity(200).setGaseous(true);
		
		//fluids
		FluidRegistry.registerFluid(steam);

		//fluid blocks
		blockSteam = new BlockFluidSteam(steam, Material.water);		
		
		GameRegistry.registerBlock(blockSteam, "blockSteam");
		
	}
}
