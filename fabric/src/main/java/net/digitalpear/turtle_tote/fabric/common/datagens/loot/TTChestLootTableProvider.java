package net.digitalpear.turtle_tote.fabric.common.datagens.loot;

import net.digitalpear.turtle_tote.init.TTItemTags;
import net.digitalpear.turtle_tote.init.TTLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class TTChestLootTableProvider extends SimpleFabricLootTableProvider {
    public TTChestLootTableProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(TTLootTables.TURTLE_TOTE_OCEAN_RUINS, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(3, 4))
                        .add(LootItem.lootTableItem(Items.BREAD).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
                        .add(LootItem.lootTableItem(Items.SCUTE).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 5))))
                        .add(LootItem.lootTableItem(Items.COAL).setWeight(12).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
                        .add(TagEntry.expandTag(ItemTags.FISHES).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 6))))
                )
                .withPool(LootPool.lootPool()
                        .add(TagEntry.expandTag(ItemTags.BOATS).setWeight(8))
                        .add(TagEntry.expandTag(ItemTags.CHEST_BOATS).setWeight(7))
                        .add(LootItem.lootTableItem(Items.FISHING_ROD).setWeight(2).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1F, 0.8F))).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
                        .add(TagEntry.expandTag(TTItemTags.TURTLE_TOTE_MUSIC_DISCS).setWeight(1))
                )
        );
    }
}
