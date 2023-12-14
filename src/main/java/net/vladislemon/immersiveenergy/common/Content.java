package net.vladislemon.immersiveenergy.common;

import net.vladislemon.immersiveenergy.client.render.block.WavefrontModelBlockRenderingHandler;
import net.vladislemon.immersiveenergy.common.block.test.BlockMultiTest;
import net.vladislemon.immersiveenergy.common.item.ItemTest;

import java.util.ArrayList;
import java.util.List;

public final class Content {

    public static final class Blocks {
        public static BlockMultiTest blockMultiTest;

        public static void init() {
            blockMultiTest = new BlockMultiTest();
        }
    }

    public static final class Items {
        public static ItemTest itemTest;

        public static void init() {
            itemTest = new ItemTest("multiblocktool");
        }
    }

    public static final class BlockRenderingHandlers {
        public static final List<WavefrontModelBlockRenderingHandler<?>> renderingHandlers = new ArrayList<>();

        public static void init() {

        }
    }

    public static void init() {
        Blocks.init();
        Items.init();
    }
}
