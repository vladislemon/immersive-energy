package net.vladislemon.immersiveenergy.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.WavefrontObject;
import net.vladislemon.immersiveenergy.client.render.block.WavefrontModelBlockRenderingHandler;

public class ClientEventHandler {

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Post event) {
        if (event.map.getTextureType() == 0) {
            WavefrontModelBlockRenderingHandler.REGISTRY.forEach(((block, renderingHandler) ->
                    rebindUVsToIcon(renderingHandler.getModel(), block.getIcon(0, 0))));
        }
    }

    private static void rebindUVsToIcon(WavefrontObject model, IIcon icon) {
        for (GroupObject groupObject : model.groupObjects) {
            if (icon == null)
                continue;
            float minU = icon.getInterpolatedU(0);
            float sizeU = icon.getInterpolatedU(16) - minU;
            float minV = icon.getInterpolatedV(0);
            float sizeV = icon.getInterpolatedV(16) - minV;
            float baseOffsetU = (16f / icon.getIconWidth()) * .0005F;
            float baseOffsetV = (16f / icon.getIconHeight()) * .0005F;
            for (Face face : groupObject.faces) {
                float averageU = 0F;
                float averageV = 0F;
                if (face.textureCoordinates != null && face.textureCoordinates.length > 0) {
                    for (int i = 0; i < face.textureCoordinates.length; ++i) {
                        averageU += face.textureCoordinates[i].u;
                        averageV += face.textureCoordinates[i].v;
                    }
                    averageU = averageU / face.textureCoordinates.length;
                    averageV = averageV / face.textureCoordinates.length;
                }

                for (int i = 0; i < face.vertices.length; ++i) {
                    float offsetU, offsetV;
                    assert face.textureCoordinates != null;
                    TextureCoordinate textureCoordinate = face.textureCoordinates[i];
                    offsetU = baseOffsetU;
                    offsetV = baseOffsetV;
                    if (face.textureCoordinates[i].u > averageU)
                        offsetU = -offsetU;
                    if (face.textureCoordinates[i].v > averageV)
                        offsetV = -offsetV;

                    face.textureCoordinates[i] = new TextureCoordinate(
                            minU + sizeU * (textureCoordinate.u + offsetU),
                            minV + sizeV * (textureCoordinate.v + offsetV)
                    );
                }
            }
        }
    }
}
