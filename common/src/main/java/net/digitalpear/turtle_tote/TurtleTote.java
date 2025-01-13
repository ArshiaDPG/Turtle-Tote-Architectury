package net.digitalpear.turtle_tote;

import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.client.ClientPlayerEvent;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.LootEvent;
import dev.architectury.event.events.common.TickEvent;
import net.digitalpear.turtle_tote.init.TTBlockEntityType;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.digitalpear.turtle_tote.init.TTItems;
import net.digitalpear.turtle_tote.init.TTLootTables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerLootTable;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.awt.event.ItemEvent;
import java.util.List;

public final class TurtleTote {
    public static final String MOD_ID = "turtletote";

    /*
        This mod is based on the Turtle Totes datapack by notblue.
        https://modrinth.com/datapack/turtle-totes
     */

    public static final List<ResourceLocation> TOTE_CHEST_IDS = List.of(
            BuiltInLootTables.UNDERWATER_RUIN_SMALL,
            BuiltInLootTables.UNDERWATER_RUIN_BIG
    );
    public static final List<ResourceLocation> TOTE_ENTITY_IDS = List.of(
            EntityType.DROWNED.getDefaultLootTable()
    );

    public static ResourceLocation id(String name){
        return new ResourceLocation(MOD_ID, name);
    }
    public static void init() {
        TTBlocks.init();
        TTItems.init();
        TTBlockEntityType.init();

        LootEvent.MODIFY_LOOT_TABLE.register((lootDataManager, resourceLocation, lootTableModificationContext, b) -> {
            LootPoolSingletonContainer.Builder<?> entry = LootItem.lootTableItem(TTBlocks.TURTLE_TOTE.get()).apply(SetContainerLootTable.withLootTable(TTBlockEntityType.TURTLE_TOTE.get(), TTLootTables.TURTLE_TOTE_OCEAN_RUINS));
            if (b){
                if (TOTE_CHEST_IDS.contains(resourceLocation)){
                    lootTableModificationContext.addPool(LootPool.lootPool()
                            .add(LootItem.lootTableItem(Items.TURTLE_HELMET).setWeight(2).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10 ,14))).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1F, 0.8F))))
                            .add(entry.setWeight(3))
                            .add(LootItem.lootTableItem(Items.SCUTE).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 6))))
                            .build());
                }
                else if (TOTE_ENTITY_IDS.contains(resourceLocation)) {
                    lootTableModificationContext.addPool(LootPool.lootPool().add(entry).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.02f, 2f)).build());
                }
            }
        });
    }
}
