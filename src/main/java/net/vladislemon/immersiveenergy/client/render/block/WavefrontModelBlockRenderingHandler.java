package net.vladislemon.immersiveenergy.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;
import net.vladislemon.immersiveenergy.ImmersiveEnergy;
import net.vladislemon.immersiveenergy.client.render.tessellator.LocalOffsetTessellator;
import net.vladislemon.immersiveenergy.common.block.BlockBase;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

public class WavefrontModelBlockRenderingHandler<T extends BlockBase> extends AbstractTypedBlockRenderingHandler<T> {
    public static final Map<BlockBase, WavefrontModelBlockRenderingHandler<?>> REGISTRY = new HashMap<>();
    protected final WavefrontObject model;

    public WavefrontModelBlockRenderingHandler(T block) {
        super(block);
        ResourceLocation resource = new ResourceLocation(ImmersiveEnergy.MOD_ID + ":models/" + getBlock().getName() + ".obj");
        model = (WavefrontObject) AdvancedModelLoader.loadModel(resource);
        REGISTRY.put(block, this);
    }

    public WavefrontObject getModel() {
        return model;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glScalef(0.4375f, 0.4375f, 0.4375f);
        GL11.glTranslatef(-.5f, -.5f, -.5f);
        model.renderAll();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.getBlockMetadata(x, y, z) != 0) {
            return false;
        }
        model.tessellateAll(new LocalOffsetTessellator(Tessellator.instance, x, y, z));
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
