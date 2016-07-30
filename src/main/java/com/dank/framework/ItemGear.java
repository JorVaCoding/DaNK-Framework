package com.dank.framework;

import static com.dank.framework.util.OreDictUtils.gears;

import java.util.List;

import com.dank.framework.util.OreDictUtils;
import com.dank.framework.util.Platform;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemGear extends Item {

	public ItemGear() {
		this.setUnlocalizedName("framework.gear");
		this.setRegistryName(Framework.MOD_ID, "gear");
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setHasSubtypes(true);
		register();
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (int meta = 0; meta < gears.length; meta++)
		{
			subItems.add(new ItemStack(itemIn, 1, meta));
		}
	}

	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= gears.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + gears[meta];
	}

	public void register() {
		GameRegistry.register(this);
		OreDictUtils.register(this, gears, "gear");
		if (Platform.isClient())
			registerItemRenderer();
	}

	public void registerItemRenderer() {
		for (int i = 0; i < gears.length; ++i) {
			String[] name = gears.clone();
			registerItemModel(this, i, name[i]);
		}
	}

	static void registerItemModel(Item i, int meta, String variant) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}
}