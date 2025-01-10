package net.digitalpear.turtle_tote.fabric;

import net.digitalpear.turtle_tote.fabric.common.datagens.*;
import net.digitalpear.turtle_tote.fabric.common.datagens.loot.TTBlockLootTableProvider;
import net.digitalpear.turtle_tote.fabric.common.datagens.loot.TTChestLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TurtleToteFabricDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(TTBlockLootTableProvider::new);
        pack.addProvider(TTChestLootTableProvider::new);

        pack.addProvider(TTLanguageProvider::new);
        pack.addProvider(TTModelProvider::new);
        pack.addProvider(TTRecipeProvider::new);

        pack.addProvider(TTBlockTagProvider::new);
        pack.addProvider(TTItemTagProvider::new);
    }
}
