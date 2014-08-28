package com.xXkyleXx.apg.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockGeothermalPump extends ItemBlockapg {

	public ItemBlockGeothermalPump(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	
	@Override
	public int getMetadata(int meta) {
	return meta;
	}
	
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {

		switch (itemstack.getItemDamage()) {
		case 0:
			return "tile.apg:geothermalPumpTier1";
		case 1:
			return "tile.apg:geothermalPumpTier2";
		case 2:
			return "tile.apg:geothermalPumpTier3";
				default:
			return "tile.apg:geothermalPumpTier1";

		}
	}
}

