package com.xXkyleXx.apg.blocks;

import com.xXkyleXx.apg.AdvancedPowerGeneration;
import com.xXkyleXx.apg.items.ItemBlockGeothermalPump;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(AdvancedPowerGeneration.MODID)
public class ModBlocks {

	
	public static final Blockapg geothermalPump = new BlockGeothermalPump();
	public static final Blockapg steamOutput = new BlockSteamOutput();
	public static final Blockapg geothermalPipe = new BlockGeothermalPipe();
	public static final Blockapg grate = new BlockGrate();
	
	public static void init() {



		GameRegistry.registerBlock(steamOutput, "steamOutput");
		GameRegistry.registerBlock(geothermalPump, ItemBlockGeothermalPump.class, "geothermalPump");
		GameRegistry.registerBlock(geothermalPipe, "geothermalPipe");
		GameRegistry.registerBlock(grate, "grate");
	}

}
