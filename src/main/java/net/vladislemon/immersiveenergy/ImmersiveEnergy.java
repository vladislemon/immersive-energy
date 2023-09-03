package net.vladislemon.immersiveenergy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.vladislemon.immersiveenergy.client.ClientProxy;
import net.vladislemon.immersiveenergy.common.CommonProxy;

@Mod(
        modid = ImmersiveEnergy.MOD_ID,
        version = ImmersiveEnergy.VERSION,
        name = ImmersiveEnergy.MOD_NAME,
        acceptedMinecraftVersions = "[1.7.10]"
)
public class ImmersiveEnergy {
    public static final String MOD_ID = "GRADLETOKEN_MODID";
    public static final String MOD_NAME = "GRADLETOKEN_MODNAME";
    public static final String VERSION = "GRADLETOKEN_VERSION";
    private static final CommonProxy PROXY = FMLCommonHandler.instance().getSide() == Side.CLIENT
            ? new ClientProxy()
            : new CommonProxy();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }
}
