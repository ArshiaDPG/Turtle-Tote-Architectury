package net.digitalpear.turtle_tote.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.stream.Stream;

public class TurtleToteBlockItem extends BlockItem {
    public TurtleToteBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        if (this.getBlock() instanceof TurtleToteBlock) {
            ItemStack itemStack = itemEntity.getItem();
            CompoundTag compoundTag = getBlockEntityData(itemStack);
            if (compoundTag != null && compoundTag.contains("Items", 9)) {
                ListTag listTag = compoundTag.getList("Items", 10);
                Stream<Tag> var10001 = listTag.stream();
                Objects.requireNonNull(CompoundTag.class);
                ItemUtils.onContainerDestroyed(itemEntity, var10001.map(CompoundTag.class::cast).map(ItemStack::of));
            }
        }
    }
}
