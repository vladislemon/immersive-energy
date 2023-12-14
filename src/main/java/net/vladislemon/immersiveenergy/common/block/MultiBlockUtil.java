package net.vladislemon.immersiveenergy.common.block;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class MultiBlockUtil {
    private static final int SEND_UPDATE_TO_CLIENTS_FLAG = 2;

    private MultiBlockUtil() {
    }

    public static void setMultiBlockMeta(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        setMultiBlockMeta(world, x, y, z, block);
    }

    private static void setMultiBlockMeta(World world, int x, int y, int z, Block block) {
        Set<BlockPosition> passedPositions = new HashSet<>();
        List<BlockPosition> currentPositions;
        List<BlockPosition> newPositions = new ArrayList<>();
        newPositions.add(new BlockPosition(world, x, y, z));
        int distance = 0;
        while (distance < 16 && !newPositions.isEmpty()) {
            for (BlockPosition position : newPositions) {
                world.setBlockMetadataWithNotify(position.getX(), position.getY(), position.getZ(), distance, SEND_UPDATE_TO_CLIENTS_FLAG);
                passedPositions.add(position);
            }
            currentPositions = newPositions;
            newPositions = new ArrayList<>();
            for (BlockPosition position : currentPositions) {
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    int sideX = position.getX() + direction.offsetX;
                    int sideY = position.getY() + direction.offsetY;
                    int sideZ = position.getZ() + direction.offsetZ;
                    BlockPosition sidePosition = new BlockPosition(world, sideX, sideY, sideZ);
                    if (passedPositions.contains(sidePosition)) {
                        continue;
                    }
                    Block sideBlock = world.getBlock(sideX, sideY, sideZ);
                    if (sideBlock != block) {
                        continue;
                    }
                    newPositions.add(sidePosition);
                }
            }
            distance++;
        }
    }

    public static void breakMultiBlock(World world, int x, int y, int z, Block block, int metadata) {
        BlockPosition position = getMainBlockPosition(world, x, y, z, block, metadata);
        if (position == null) {
            return;
        }
        breakMultiBlock(world, position.getX(), position.getY(), position.getZ());
    }

    private static void breakMultiBlock(World world, int x, int y, int z) {
        world.setBlock(x-1, y-1, z-1, Blocks.cobblestone);
        world.setBlock(x-1, y-1, z, Blocks.cobblestone);
        world.setBlock(x-1, y-1, z+1, Blocks.cobblestone);
        world.setBlock(x-1, y, z-1, Blocks.cobblestone);
        world.setBlock(x-1, y, z, Blocks.cobblestone);
        world.setBlock(x-1, y, z+1, Blocks.cobblestone);
        world.setBlock(x-1, y+1, z-1, Blocks.cobblestone);
        world.setBlock(x-1, y+1, z, Blocks.cobblestone);
        world.setBlock(x-1, y+1, z+1, Blocks.cobblestone);

        world.setBlock(x, y-1, z-1, Blocks.cobblestone);
        world.setBlock(x, y-1, z, Blocks.cobblestone);
        world.setBlock(x, y-1, z+1, Blocks.cobblestone);
        world.setBlock(x, y, z-1, Blocks.cobblestone);
        world.setBlock(x, y, z, Blocks.cobblestone);
        world.setBlock(x, y, z+1, Blocks.cobblestone);
        world.setBlock(x, y+1, z-1, Blocks.cobblestone);
        world.setBlock(x, y+1, z, Blocks.cobblestone);
        world.setBlock(x, y+1, z+1, Blocks.cobblestone);

        world.setBlock(x+1, y-1, z-1, Blocks.cobblestone);
        world.setBlock(x+1, y-1, z, Blocks.cobblestone);
        world.setBlock(x+1, y-1, z+1, Blocks.cobblestone);
        world.setBlock(x+1, y, z-1, Blocks.cobblestone);
        world.setBlock(x+1, y, z, Blocks.cobblestone);
        world.setBlock(x+1, y, z+1, Blocks.cobblestone);
        world.setBlock(x+1, y+1, z-1, Blocks.cobblestone);
        world.setBlock(x+1, y+1, z, Blocks.cobblestone);
        world.setBlock(x+1, y+1, z+1, Blocks.cobblestone);
    }

    @SuppressWarnings("unchecked")
    public static <T extends TileEntity> T getTileEntity(World world, int x, int y, int z, BlockMulti<T> block) {
        BlockPosition mainBlockPosition = MultiBlockUtil.getMainBlockPosition(world, x, y, z, block);
        if (mainBlockPosition == null) {
            return null;
        }
        return (T) world.getTileEntity(x, y, z);
    }

    public static BlockPosition getMainBlockPosition(BlockPosition blockPosition) {
        World world = blockPosition.getWorld();
        int x = blockPosition.getX();
        int y = blockPosition.getY();
        int z = blockPosition.getZ();
        return getMainBlockPosition(world, x, y, z);
    }

    public static BlockPosition getMainBlockPosition(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return getMainBlockPosition(world, x, y, z, block);
    }

    public static BlockPosition getMainBlockPosition(World world, int x, int y, int z, Block block) {
        int meta = world.getBlockMetadata(x, y, z);
        return getMainBlockPosition(world, x, y, z, block, meta);
    }

    private static BlockPosition getMainBlockPosition(World world, int x, int y, int z, Block block, int meta) {
        if (meta == 0) {
            return new BlockPosition(world, x, y, z);
        }
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            int sideX = x + direction.offsetX;
            int sideY = y + direction.offsetY;
            int sideZ = z + direction.offsetZ;
            Block sideBlock = world.getBlock(sideX, sideY, sideZ);
            if (sideBlock != block) {
                continue;
            }
            int sideBlockMeta = world.getBlockMetadata(sideX, sideY, sideZ);
            if (sideBlockMeta < meta) {
                return getMainBlockPosition(world, sideX, sideY, sideZ, sideBlock, sideBlockMeta);
            }
        }
        return null;
    }
}
