package net.vladislemon.immersiveenergy.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface ITypedTileEntityProvider<T extends TileEntity> extends ITileEntityProvider {

    @Override
    T createNewTileEntity(World world, int metadata);
}
