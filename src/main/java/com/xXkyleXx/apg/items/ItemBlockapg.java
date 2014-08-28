package com.xXkyleXx.apg.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockapg extends ItemBlock {

	public ItemBlockapg(Block block) {
		super(block);
		setCreativeTab(CreativeTabapg.APG_TAB);
	}

	@Override
	public int getMetadata(int meta) {
	return meta;
	}
}
