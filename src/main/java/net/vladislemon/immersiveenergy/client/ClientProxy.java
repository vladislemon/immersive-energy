package net.vladislemon.immersiveenergy.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.vladislemon.immersiveenergy.client.render.block.WavefrontModelBlockRenderingHandler;
import net.vladislemon.immersiveenergy.common.CommonProxy;
import net.vladislemon.immersiveenergy.common.Content;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        RenderingRegistry.registerBlockHandler(new WavefrontModelBlockRenderingHandler<>(Content.Blocks.blockMultiTest));
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
