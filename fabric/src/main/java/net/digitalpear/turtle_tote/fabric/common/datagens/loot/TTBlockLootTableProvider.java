package net.digitalpear.turtle_tote.fabric.common.datagens.loot;


import net.digitalpear.turtle_tote.common.TurtleToteBlock;
import net.digitalpear.turtle_tote.init.TTBlockEntityType;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
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

public class TTBlockLootTableProvider extends FabricBlockLootTableProvider {
    public TTBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        add(TTBlocks.TURTLE_TOTE.get(), turtleToteDrops(TTBlocks.TURTLE_TOTE.get()));
    }
    protected LootTable.Builder turtleToteDrops(Block arg) {
        return LootTable.lootTable().withPool(this.applyExplosionCondition(arg, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(arg)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy("Lock", "BlockEntityTag.Lock")
                                .copy("LootTable", "BlockEntityTag.LootTable")
                                .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                        .apply(SetContainerContents.setContents(TTBlockEntityType.TURTLE_TOTE.get()).withEntry(DynamicLoot.dynamicEntry(TurtleToteBlock.CONTENTS))))));
    }
}
