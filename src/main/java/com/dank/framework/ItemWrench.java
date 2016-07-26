package com.dank.framework;

import com.dank.framework.util.Platform;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWrench extends Item {
	public ItemWrench() {
		this.setUnlocalizedName("wrench");
		this.setRegistryName(Framework.MOD_ID, "wrench");
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setMaxStackSize(1);
		register();
	}

	public void register() {
		GameRegistry.register(this);
		if(Platform.isClient())
			registerItemRenderer();
	}


	@Override
	public String getUnlocalizedName() {
		String itemName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

		String test = String.format("item.%s.%s", Framework.MOD_ID, itemName);
		return test;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String itemName = getUnwrappedUnlocalizedName(super.getUnlocalizedName(stack));

		String test = String.format("item.%s.%s", Framework.MOD_ID, itemName);
		return test;
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	public void registerItemRenderer() {
		final String resourcePath = String.format("%s:%s", Framework.MOD_ID, "tool/wrench");

		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(resourcePath, "inventory"));
	}
}
