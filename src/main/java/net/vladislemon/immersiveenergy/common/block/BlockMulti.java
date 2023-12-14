package net.vladislemon.immersiveenergy.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockMulti<T extends TileEntity> extends BlockBase implements ITypedTileEntityProvider<T> {
    protected BlockMulti(Material material, String name, Class<T> tileEntityClass) {
        super(material, name);
        GameRegistry.registerTileEntity(tileEntityClass, name);
    }

    public T getTileEntity(World world, int x, int y, int z) {
        return MultiBlockUtil.getTileEntity(world, x, y, z, this);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return metadata == 0;
    }

    @Override
    public T createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return createNewTileEntity(world, metadata);
        }
        return null;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        MultiBlockUtil.breakMultiBlock(world, x, y, z, block, metadata);
        super.breakBlock(world, x, y, z, block, metadata);
    }
}
