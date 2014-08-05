package com.xXkyleXx.apg;

import com.xXkyleXx.apg.tileentities.TileEntityGeothermalPump;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityGeothermalPump.class, "geothermalPump");
	}

}
