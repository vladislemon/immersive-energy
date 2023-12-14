package net.vladislemon.immersiveenergy.common.block.test;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.vladislemon.immersiveenergy.common.block.BlockMultiCustomRendered;

public class BlockMultiTest extends BlockMultiCustomRendered<TileEntityTest> {
    public BlockMultiTest() {
        super(Material.iron, "test", TileEntityTest.class);
    }

    @Override
    public TileEntityTest createNewTileEntity(World world, int metadata) {
        return new TileEntityTest();
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        String m = String.format("meta=%d", world.getBlockMetadata(x, y, z));
        player.addChatMessage(new ChatComponentText(m));
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        if (!world.isRemote) {
            EntityPlayer closestPlayer = world.getClosestPlayer(x, y, z, 10);
            if (closestPlayer != null) {
                String m = String.format("side=%d", side);
                closestPlayer.addChatMessage(new ChatComponentText(m));
            }
        }
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }
}
