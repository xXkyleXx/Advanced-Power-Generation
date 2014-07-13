package com.xXkyleXx.apg.config;

import net.minecraftforge.common.config.Configuration;

import com.xXkyleXx.apg.AdvancedPowerGeneration;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	public static Configuration config;

	public static int testConfigInteger = 1;
	public static String testConfigString = "yes";
	public static boolean testConfigBool = true;

	public static void init(FMLPreInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(AdvancedPowerGeneration.MODID);
		config = new Configuration(event.getSuggestedConfigurationFile());

		syncConfig();
	}

	public static void syncConfig() {
		testConfigInteger = config.getInt("My Config Integer",Configuration.CATEGORY_GENERAL, testConfigInteger, 0,Integer.MAX_VALUE, "An Integer!");
		testConfigString = config.getString("My Config String",Configuration.CATEGORY_GENERAL, testConfigString, "A String!");
		testConfigBool = config.getBoolean("test boolean",Configuration.CATEGORY_GENERAL, testConfigBool, "A Boolean!");

		if (config.hasChanged()) {
			config.save();

		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(AdvancedPowerGeneration.MODID))
			syncConfig();
	}
}