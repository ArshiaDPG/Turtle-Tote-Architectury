package net.digitalpear.turtle_tote.common;

import net.digitalpear.turtle_tote.init.TTBlockEntityType;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class TurtleToteBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    public static final int SLOT_COUNT = 9;
    private static final int[] SLOTS = IntStream.range(0, SLOT_COUNT).toArray();
    private NonNullList<ItemStack> itemStacks;
    private final ContainerOpenersCounter openersCounter;
    public static final String TURTLE_TOTE_CONTAINER_NAME = "container.turtle_tote";

    public TurtleToteBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TTBlockEntityType.TURTLE_TOTE.get(), blockPos, blockState);
        this.itemStacks = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
                TurtleToteBlockEntity.this.playSound(blockState, SoundEvents.ARMOR_EQUIP_TURTLE);
                TurtleToteBlockEntity.this.updateBlockState(blockState, true);
            }

            protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
                TurtleToteBlockEntity.this.playSound(blockState, SoundEvents.ARMOR_EQUIP_TURTLE);
                TurtleToteBlockEntity.this.updateBlockState(blockState, false);
            }

            protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int j) {
            }

            protected boolean isOwnContainer(Player player) {
                if (player.containerMenu instanceof ChestMenu) {
                    Container container = ((ChestMenu)player.containerMenu).getContainer();
                    return container == TurtleToteBlockEntity.this;
                } else {
                    return false;
                }
            }
        };
    }
    private static void doNeighborUpdates(Level level, BlockPos blockPos, BlockState blockState) {
        blockState.updateNeighbourShapes(level, blockPos, 3);
    }
    @Override
    public int[] getSlotsForFace(Direction direction) {
        return SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return !(Block.byItem(itemStack.getItem()) instanceof TurtleToteBlock);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.loadFromTag(compoundTag);
    }
    public void loadFromTag(CompoundTag compoundTag) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag) && compoundTag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(compoundTag, this.itemStacks);
        }

    }

    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.itemStacks, false);
        }

    }
    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.itemStacks = nonNullList;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable(TURTLE_TOTE_CONTAINER_NAME);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ChestMenu(MenuType.GENERIC_9x1, i, inventory, this, 1);
    }

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }
    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    void updateBlockState(BlockState blockState, boolean bl) {
        this.level.setBlock(this.getBlockPos(), blockState.setValue(BarrelBlock.OPEN, bl), 3);
    }

    void playSound(BlockState blockState, SoundEvent soundEvent) {
        Vec3i vec3i = blockState.getValue(BarrelBlock.FACING).getNormal();
        double d = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double e = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double f = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        this.level.playSound(null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 1.3F);
    }
}
