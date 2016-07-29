package com.dank.framework;

import net.minecraft.item.Item;

public class ModItems {
	public static Item WRENCH;
	public static Item INGOT;
	public static Item GEAR;
	public static Item DUST;


	public static void preInit() {
		WRENCH = new ItemWrench();
		INGOT = new ItemIngot();
		DUST = new ItemDust();
		GEAR = new ItemGear();
	}
}
