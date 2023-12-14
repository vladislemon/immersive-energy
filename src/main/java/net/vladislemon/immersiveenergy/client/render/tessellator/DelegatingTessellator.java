package net.vladislemon.immersiveenergy.client.render.tessellator;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.TesselatorVertexState;

public class DelegatingTessellator extends Tessellator {
    private final Tessellator tessellator;

    public DelegatingTessellator(Tessellator tessellator) {
        this.tessellator = tessellator;
    }

    @Override
    public int draw() {
        return tessellator.draw();
    }

    @Override
    public TesselatorVertexState getVertexState(float p_147564_1_, float p_147564_2_, float p_147564_3_) {
        return tessellator.getVertexState(p_147564_1_, p_147564_2_, p_147564_3_);
    }

    @Override
    public void setVertexState(TesselatorVertexState p_147565_1_) {
        tessellator.setVertexState(p_147565_1_);
    }

    @Override
    public void startDrawingQuads() {
        tessellator.startDrawingQuads();
    }

    @Override
    public void startDrawing(int p_78371_1_) {
        tessellator.startDrawing(p_78371_1_);
    }

    @Override
    public void setTextureUV(double p_78385_1_, double p_78385_3_) {
        tessellator.setTextureUV(p_78385_1_, p_78385_3_);
    }

    @Override
    public void setBrightness(int p_78380_1_) {
        tessellator.setBrightness(p_78380_1_);
    }

    @Override
    public void setColorOpaque_F(float p_78386_1_, float p_78386_2_, float p_78386_3_) {
        tessellator.setColorOpaque_F(p_78386_1_, p_78386_2_, p_78386_3_);
    }

    @Override
    public void setColorRGBA_F(float p_78369_1_, float p_78369_2_, float p_78369_3_, float p_78369_4_) {
        tessellator.setColorRGBA_F(p_78369_1_, p_78369_2_, p_78369_3_, p_78369_4_);
    }

    @Override
    public void setColorOpaque(int p_78376_1_, int p_78376_2_, int p_78376_3_) {
        tessellator.setColorOpaque(p_78376_1_, p_78376_2_, p_78376_3_);
    }

    @Override
    public void setColorRGBA(int p_78370_1_, int p_78370_2_, int p_78370_3_, int p_78370_4_) {
        tessellator.setColorRGBA(p_78370_1_, p_78370_2_, p_78370_3_, p_78370_4_);
    }

    @Override
    public void func_154352_a(byte p_154352_1_, byte p_154352_2_, byte p_154352_3_) {
        tessellator.func_154352_a(p_154352_1_, p_154352_2_, p_154352_3_);
    }

    @Override
    public void addVertexWithUV(double p_78374_1_, double p_78374_3_, double p_78374_5_, double p_78374_7_, double p_78374_9_) {
        tessellator.addVertexWithUV(p_78374_1_, p_78374_3_, p_78374_5_, p_78374_7_, p_78374_9_);
    }

    @Override
    public void addVertex(double p_78377_1_, double p_78377_3_, double p_78377_5_) {
        tessellator.addVertex(p_78377_1_, p_78377_3_, p_78377_5_);
    }

    @Override
    public void setColorOpaque_I(int p_78378_1_) {
        tessellator.setColorOpaque_I(p_78378_1_);
    }

    @Override
    public void setColorRGBA_I(int p_78384_1_, int p_78384_2_) {
        tessellator.setColorRGBA_I(p_78384_1_, p_78384_2_);
    }

    @Override
    public void disableColor() {
        tessellator.disableColor();
    }

    @Override
    public void setNormal(float p_78375_1_, float p_78375_2_, float p_78375_3_) {
        tessellator.setNormal(p_78375_1_, p_78375_2_, p_78375_3_);
    }

    @Override
    public void setTranslation(double p_78373_1_, double p_78373_3_, double p_78373_5_) {
        tessellator.setTranslation(p_78373_1_, p_78373_3_, p_78373_5_);
    }

    @Override
    public void addTranslation(float p_78372_1_, float p_78372_2_, float p_78372_3_) {
        tessellator.addTranslation(p_78372_1_, p_78372_2_, p_78372_3_);
    }
}
