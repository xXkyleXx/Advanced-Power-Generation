package com.xXkyleXx.apg.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.xXkyleXx.apg.AdvancedPowerGeneration;

public class CreativeTabapg {

	public static final CreativeTabs APG_TAB = new CreativeTabs(AdvancedPowerGeneration.MODID){
		@Override
        public Item getTabIconItem()
        {
            return  Items.nether_star;
        }

        @Override
        public String getTranslatedTabLabel()
        {
            return "Advanced Power Generation";
        }
	};
}
