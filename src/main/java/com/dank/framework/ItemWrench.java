package com.dank.framework;

import com.dank.framework.util.Platform;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
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
	public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return false;
	}

	@Override
	public String getUnlocalizedName() {
		String itemName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

		String test = String.format("item.%s.%s", Framework.MOD_ID, itemName);
		return test;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if(block instanceof IWrenchable) {
			return ((IWrenchable) block).onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
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
