package com.dank.framework;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class TileEntityBase extends TileEntity{

	public TileEntityBase() {
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	@Override
	public void onChunkUnload() {
		if (!this.isInvalid())
			this.invalidate();
	}

	public void onChunkLoad() {
		if (this.isInvalid())
			this.validate();
		markForUpdate();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound data = new NBTTagCompound();
		writeToNBT(data);
		return new SPacketUpdateTileEntity(this.pos, 1, data);
	}

	public void markForFullUpdate() {
		this.markForUpdate();
		this.markForLightUpdate();
		this.markDirty();
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity s35PacketUpdateTileEntity) {
		readFromNBT(s35PacketUpdateTileEntity.getNbtCompound());
		worldObj.markBlockRangeForRenderUpdate(this.pos, this.pos);
		markForUpdate();
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
}
