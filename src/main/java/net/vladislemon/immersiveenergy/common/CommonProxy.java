package net.vladislemon.immersiveenergy.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent ignoredEvent) {
    }

    public void init(FMLInitializationEvent ignoredEvent) {
        Content.init();
    }

    public void postInit(FMLPostInitializationEvent ignoredEvent) {
    }
}
