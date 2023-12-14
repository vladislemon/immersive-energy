package net.vladislemon.immersiveenergy.client.render.tessellator;

import net.minecraft.client.renderer.Tessellator;

public class LocalOffsetTessellator extends DelegatingTessellator {
    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;

    public LocalOffsetTessellator(Tessellator tessellator, double offsetX, double offsetY, double offsetZ) {
        super(tessellator);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    @Override
    public void addVertexWithUV(double x, double y, double z, double u, double v) {
        super.addVertexWithUV(x + offsetX, y + offsetY, z + offsetZ, u, v);
    }

    @Override
    public void addVertex(double x, double y, double z) {
        super.addVertex(x + offsetX, y + offsetY, z + offsetZ);
    }
}
