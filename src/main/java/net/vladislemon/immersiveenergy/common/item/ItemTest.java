package net.vladislemon.immersiveenergy.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.vladislemon.immersiveenergy.ImmersiveEnergy;
import net.vladislemon.immersiveenergy.common.Content;
import net.vladislemon.immersiveenergy.common.block.MultiBlockUtil;
import org.lwjgl.input.Keyboard;

public class ItemTest extends Item {
    protected final String name;

    public ItemTest(String name) {
        this.name = name;
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(name);
        setTextureName(ImmersiveEnergy.MOD_ID + ":" + name);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        Block block = world.getBlock(x, y, z);
        if (block == Blocks.cobblestone) {
            ForgeDirection opposite = ForgeDirection.VALID_DIRECTIONS[side].getOpposite();
            
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY-1, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY-1, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY-1, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY+1, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY+1, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX-1, y+opposite.offsetY+1, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);

            world.setBlock(x+opposite.offsetX, y+opposite.offsetY-1, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY-1, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY-1, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY+1, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY+1, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX, y+opposite.offsetY+1, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);

            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY-1, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY-1, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY-1, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY+1, z+opposite.offsetZ-1, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY+1, z+opposite.offsetZ, Content.Blocks.blockMultiTest);
            world.setBlock(x+opposite.offsetX+1, y+opposite.offsetY+1, z+opposite.offsetZ+1, Content.Blocks.blockMultiTest);

            MultiBlockUtil.setMultiBlockMeta(world, x, y, z);
            return true;
        } else if (block == Content.Blocks.blockMultiTest) {
            String m = String.format("meta=%d", world.getBlockMetadata(x, y, z));
            player.addChatMessage(new ChatComponentText(m));
            return true;
        }
        return false;
    }
}
