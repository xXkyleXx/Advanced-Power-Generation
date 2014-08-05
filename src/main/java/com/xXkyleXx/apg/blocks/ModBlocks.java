package com.xXkyleXx.apg.blocks;

import com.xXkyleXx.apg.AdvancedPowerGeneration;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(AdvancedPowerGeneration.MODID)
public class ModBlocks {

	public static final Blockapg geothermalPump = new BlockGeothermalPump();

	public static void init() {




		GameRegistry.registerBlock(geothermalPump, "geothermalPump");

	}
}