package com.dank.framework;

import net.minecraft.item.Item;

public class ModItems {
	public static Item WRENCH;



	public static void preInit() {
		WRENCH = new ItemWrench();
	}
}
