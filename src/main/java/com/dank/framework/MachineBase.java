package com.dank.framework;

import com.dank.framework.blocks.BlockStates;
import com.dank.framework.util.TileHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class MachineBase extends BlockContainer {

	public static final PropertyEnum<MachineConfig> CONFIG_UP    = PropertyEnum.create("config_up", MachineConfig.class);
	public static final PropertyEnum<MachineConfig> CONFIG_DOWN  = PropertyEnum.create("config_down", MachineConfig.class);
	public static final PropertyEnum<MachineConfig> CONFIG_NORTH = PropertyEnum.create("config_north", MachineConfig.class);
	public static final PropertyEnum<MachineConfig> CONFIG_SOUTH = PropertyEnum.create("config_south", MachineConfig.class);
	public static final PropertyEnum<MachineConfig> CONFIG_EAST  = PropertyEnum.create("config_east", MachineConfig.class);
	public static final PropertyEnum<MachineConfig> CONFIG_WEST  = PropertyEnum.create("config_west", MachineConfig.class);

	public MachineBase(Material materialIn) {
		super(materialIn);
		setDefaultState(blockState.getBaseState()
				.withProperty(BlockStates.FACING_HORIZONTAL, EnumFacing.NORTH)
				.withProperty(BlockStates.ACTIVE,false)
				.withProperty(CONFIG_UP,MachineConfig.DISABLED)
				.withProperty(CONFIG_DOWN,MachineConfig.DISABLED)
				.withProperty(CONFIG_NORTH,MachineConfig.DISABLED)
				.withProperty(CONFIG_SOUTH,MachineConfig.DISABLED)
				.withProperty(CONFIG_EAST,MachineConfig.DISABLED)
				.withProperty(CONFIG_WEST,MachineConfig.DISABLED)
		);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this,
				BlockStates.FACING_HORIZONTAL,
				BlockStates.ACTIVE,
				CONFIG_UP,
				CONFIG_DOWN,
				CONFIG_NORTH,
				CONFIG_SOUTH,
				CONFIG_EAST,
				CONFIG_WEST
		);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntityMachine machine = TileHelper.getTileEntity(worldIn,pos,TileEntityMachine.class);
		return state
				.withProperty(BlockStates.ACTIVE, machine.isActive())
				.withProperty(BlockStates.FACING_HORIZONTAL,machine.getForward())
				.withProperty(CONFIG_UP,MachineConfig.DISABLED)
				.withProperty(CONFIG_DOWN,MachineConfig.DISABLED)
				.withProperty(CONFIG_NORTH,MachineConfig.DISABLED)
				.withProperty(CONFIG_SOUTH,MachineConfig.DISABLED)
				.withProperty(CONFIG_EAST,MachineConfig.DISABLED)
				.withProperty(CONFIG_WEST,MachineConfig.DISABLED)
				;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean active = state.getValue(BlockStates.ACTIVE);
		if(active)
			return 15;
		return super.getLightValue(state, world, pos);
	}
}
