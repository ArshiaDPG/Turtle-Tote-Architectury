package net.digitalpear.turtle_tote.fabric;

import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public final class TurtleToteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        TurtleTote.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.addBefore(Items.SHULKER_BOX, TTBlocks.TURTLE_TOTE.get());
        });


    }
}
