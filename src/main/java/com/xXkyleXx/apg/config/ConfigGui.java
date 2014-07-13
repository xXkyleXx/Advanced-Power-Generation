package com.xXkyleXx.apg.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;

public class ConfigGui extends GuiConfig {
    
	public ConfigGui(GuiScreen parent) {
        super(parent,
                new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                "ConfigHandler", false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}