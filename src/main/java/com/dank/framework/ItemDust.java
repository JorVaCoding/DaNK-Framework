package com.dank.framework;

import com.dank.framework.util.Platform;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ItemDust extends Item {

	public static final String[] ores = new String[]{
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

	public ItemDust() {
		this.setUnlocalizedName("framework.dust");
		this.setRegistryName(Framework.MOD_ID, "dust");
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setHasSubtypes(true);
		register();
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (int meta = 0; meta < ores.length; meta++)
		{
			subItems.add(new ItemStack(itemIn, 1, meta));
		}
	}

	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= ores.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + ores[meta];
	}

	public void register() {
		GameRegistry.register(this);
		registerOreDict();
		if (Platform.isClient())
			registerItemRenderer();
	}

	public void registerItemRenderer() {
		for (int i = 0; i < ores.length; ++i) {
			String[] name = ores.clone();
			registerItemModel(this, i, name[i]);
		}
	}
	public void registerOreDict() {
		for (int i = 0; i < ores.length; ++i) {
			String[] name = ores.clone();
			OreDictionary.registerOre("dust"+ StringUtils.capitalize(name[i]),new ItemStack(this,1,i));
		}
	}

	static void registerItemModel(Item i, int meta, String variant) {
		ResourceLocation loc = i.getRegistryName();
		ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "type=" + variant));
	}
}