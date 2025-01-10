package net.digitalpear.turtle_tote.forge.common;


import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.forge.common.datagens.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = TurtleTote.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TTDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();
        PackOutput output = gen.getPackOutput();

        event.getGenerator().addProvider(
                // Tell generator to run only when server data are generating
                event.includeServer(),
                new TTLootTableProviders(
                        output,
                        // Specify registry names of tables that are required to generate, or can leave empty
                        Collections.emptySet(),
                        // Sub providers which generate the loot
                        List.of(new LootTableProvider.SubProviderEntry(
                                TTBlockLootTableProvider::new,
                                // Loot table generator for the 'empty' param set
                                LootContextParamSets.BLOCK
                        ))
                )
        );
        gen.addProvider(
                event.includeClient(),
                new TTBlockstateModelProvider(output, efh)
        );
        gen.addProvider(
                // Tell generator to run only when client assets are generating
                event.includeClient(),
                // Localizations for American English
                new TTLanguageProvider(output, "en_us")
        );
        gen.addProvider(
                // Tell generator to run only when server data are generating
                event.includeServer(),
                // Extends net.minecraftforge.common.data.BlockTagsProvider
                new TTBlockTagProvider(
                        output,
                        event.getLookupProvider(),
                        event.getExistingFileHelper()
                )
        );
    }
}
