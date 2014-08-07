package com.xXkyleXx.apg;

import com.xXkyleXx.apg.blocks.ModBlocks;
import com.xXkyleXx.apg.config.ConfigHandler;
import com.xXkyleXx.apg.fluids.ModFluids;
import com.xXkyleXx.apg.tileentities.ModTileEntities;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AdvancedPowerGeneration.MODID, name = AdvancedPowerGeneration.NAME, version = AdvancedPowerGeneration.VERSION, guiFactory = "com.xXkyleXx.apg.config.ConfigGuiFactory")
public class AdvancedPowerGeneration {

	@Mod.Instance("MODID")
	public static AdvancedPowerGeneration instance;

	@SidedProxy(clientSide = "com.xXkyleXx.apg.ClientProxy", serverSide = "com.xXkyleXx.apg.CommonProxy")
	public static CommonProxy proxy;

	public static final String NAME = "Advanced Power Generation";
	public static final String MODID = "apg";
	public static final String VERSION = "1.7.10-0.0.1";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event);
		
		ModBlocks.init();
		ModFluids.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		ModTileEntities.init();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
