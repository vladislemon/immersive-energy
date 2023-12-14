package net.vladislemon.immersiveenergy.common.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public abstract class BlockMultiCustomRendered<T extends TileEntity> extends BlockMulti<T> {
    protected final int renderType;

    protected BlockMultiCustomRendered(Material material, String name, Class<T> tileEntityClass) {
        super(material, name, tileEntityClass);
        renderType = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return renderType;
    }
}
