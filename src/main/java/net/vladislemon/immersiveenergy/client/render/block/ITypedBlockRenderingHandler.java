package net.vladislemon.immersiveenergy.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.vladislemon.immersiveenergy.common.block.BlockBase;

public interface ITypedBlockRenderingHandler<T extends BlockBase> extends ISimpleBlockRenderingHandler {

    T getBlock();
}
