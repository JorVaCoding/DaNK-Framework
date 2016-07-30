package com.dank.framework.util;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictUtils {
	public static void register(Item item, String[] variants, String prefix){
		for (int i = 0; i < variants.length; ++i) {
			String[] name = variants.clone();
			OreDictionary.registerOre(prefix + StringUtils.capitalize(name[i]),new ItemStack(item,1,i));
		}
	}
	
	public static final String[] dusts = new String[]{
			"copper",
			"tin",
			"silver",
			"blizz",
			"charcoal",
			"coal",
			"cryotheum",
			"sulfur",
			"lead",
			"ferrous",
			"shiny",
			"mithril",
			"niter",
			"mana",
			"bronze",
			"invar",
			"gold",
			"iron",
			"electrum",
			"signalum",
			"lumium",
			"enderium",
	};
	
	public static final String[] gears = new String[]{
			"copper",
			"tin",
			"silver",
			"lead",
			"ferrous",
			"shiny",
			"mithril",
			"bronze",
			"invar",
			"gold",
			"iron",
			"electrum",
			"signalum",
			"lumium",
			"enderium",
	};
	
	public static final String[] ingots = new String[]{
			"copper",
			"tin",
			"silver",
			"lead",
			"ferrous",
			"shiny",
			"mithril",
			"bronze",
			"invar",
			"electrum",
			"signalum",
			"lumium",
			"enderium",
	};
	
	
}
