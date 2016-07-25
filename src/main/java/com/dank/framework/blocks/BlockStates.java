package com.dank.framework.blocks;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

public class BlockStates {
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	public static final PropertyDirection FACING_HORIZONTAL = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyDirection FACING_ALL = PropertyDirection.create("facing");
	public static final PropertyDirection FACING_VERTICAL = PropertyDirection.create("facing", EnumFacing.Plane.VERTICAL);
}
