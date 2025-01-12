package net.digitalpear.turtle_tote.forge.common.datagens;


import net.digitalpear.turtle_tote.common.TurtleToteBlock;
import net.digitalpear.turtle_tote.init.TTBlockEntityType;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class TTBlockLootTableProvider extends BlockLootSubProvider {
    public TTBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(TTBlocks.TURTLE_TOTE.get(), this::createToteDrop);
    }
    protected LootTable.Builder createToteDrop(Block arg) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(arg, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(arg).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Lock", "BlockEntityTag.Lock").copy("LootTable", "BlockEntityTag.LootTable").copy("LootTableSeed", "BlockEntityTag.LootTableSeed")).apply(SetContainerContents.setContents(TTBlockEntityType.TURTLE_TOTE.get()).withEntry(DynamicLoot.dynamicEntry(TurtleToteBlock.CONTENTS))))));
    }
}
