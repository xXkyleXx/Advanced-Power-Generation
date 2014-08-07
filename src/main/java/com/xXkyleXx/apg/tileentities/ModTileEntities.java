package com.xXkyleXx.apg.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityGeothermalPump.class, "geothermalPump");
		GameRegistry.registerTileEntity(TileEntitySteamOutput.class, "steamOutput");
	}
}



