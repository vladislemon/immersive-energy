package net.vladislemon.immersiveenergy.client.render.block;

import net.vladislemon.immersiveenergy.common.block.BlockBase;

public abstract class AbstractTypedBlockRenderingHandler<T extends BlockBase> implements ITypedBlockRenderingHandler<T> {
    protected final T block;

    protected AbstractTypedBlockRenderingHandler(T block) {
        this.block = block;
    }

    @Override
    public T getBlock() {
        return block;
    }

    @Override
    public int getRenderId() {
        return block.getRenderType();
    }
}
