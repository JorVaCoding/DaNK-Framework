package com.dank.framework;

import static com.dank.framework.util.OreDictUtils.dusts;

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

public class ItemDust extends Item {


	public ItemDust() {
		this.setUnlocalizedName("framework.dust");
		this.setRegistryName(Framework.MOD_ID, "dust");
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setHasSubtypes(true);
		register();
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (int meta = 0; meta < dusts.length; meta++)
		{
			subItems.add(new ItemStack(itemIn, 1, meta));
		}
	}

	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= dusts.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + dusts[meta];
	}

	public void register() {
		GameRegistry.register(this);
		OreDictUtils.register(this, dusts, "dust");
		if (Platform.isClient())
			registerItemRenderer();
	}

	public void registerItemRenderer() {
		for (int i = 0; i < dusts.length; ++i) {
			String[] name = dusts.clone();
			registerItemModel(this, i, name[i]);
		}
	}

	static void registerItemModel(Item i, int meta, String variant) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}
}