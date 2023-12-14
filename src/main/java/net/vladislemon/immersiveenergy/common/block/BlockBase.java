package net.vladislemon.immersiveenergy.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.vladislemon.immersiveenergy.ImmersiveEnergy;

public abstract class BlockBase extends Block {
    protected final String name;

    protected BlockBase(Material material, String name) {
        super(material);
        this.name = name;
        GameRegistry.registerBlock(this, name);
        setBlockName(name);
        setBlockTextureName(ImmersiveEnergy.MOD_ID + ":" + name);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    public String getName() {
        return name;
    }
}
