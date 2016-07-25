package com.dank.framework;

import com.dank.framework.util.ConfigMap;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;

public abstract class TileEntityMachine extends TileEntity implements ITickable {

	private ConfigMap map = new ConfigMap();

	public TileEntityMachine() {



		map.put(EnumFacing.UP,MachineConfig.DISABLED);
		map.put(EnumFacing.DOWN,MachineConfig.DISABLED);
		map.put(EnumFacing.NORTH,MachineConfig.DISABLED);
		map.put(EnumFacing.SOUTH,MachineConfig.DISABLED);
		map.put(EnumFacing.EAST,MachineConfig.DISABLED);
		map.put(EnumFacing.WEST,MachineConfig.DISABLED);
	}

	@Override
	public void update() {
		this.onMachineTick();
	}

	public void markForUpdate() {
		if (this.worldObj != null) {
			Block block = worldObj.getBlockState(this.pos).getBlock();
			this.worldObj.notifyBlockUpdate(this.pos, worldObj.getBlockState(this.pos), worldObj.getBlockState(this.pos), 3);

			int xCoord = this.pos.getX();
			int yCoord = this.pos.getY();
			int zCoord = this.pos.getZ();

			this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord - 1, zCoord), block);
			this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord + 1, zCoord), block);
			this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord - 1, yCoord, zCoord), block);
			this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord + 1, yCoord, zCoord), block);
			this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord, zCoord - 1), block);
			this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord, zCoord + 1), block);
		}
	}

	public void markForLightUpdate() {
		if (this.worldObj.isRemote) {
			this.worldObj.notifyBlockUpdate(this.pos, worldObj.getBlockState(this.pos), worldObj.getBlockState(this.pos), 3);
		}

		this.worldObj.checkLightFor(EnumSkyBlock.BLOCK, this.pos);
	}

	public void markForFullUpdate() {
		this.markForUpdate();
		this.markForLightUpdate();
		this.markDirty();
	}

	public abstract void onMachineTick();

	private EnumFacing forward;

	public EnumFacing getForward() {
		return forward;
	}

	public abstract boolean isActive();

	public MachineConfig getConfigSide(EnumFacing side) {
		return map.get(side);
	}
}
