package net.digitalpear.turtle_tote.forge;

import dev.architectury.platform.forge.EventBuses;
import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.forge.common.TTDataGenerators;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Supplier;

@Mod(TurtleTote.MOD_ID)
public final class TurtleToteForge {
    public TurtleToteForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(TurtleTote.MOD_ID, bus);
        bus.addListener(this::buildContents);
        bus.addListener(TTDataGenerators::gatherData);
//        bus.addListener(this::createLootTables);

        // Run our common setup.
        TurtleTote.init();
    }



    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Add to ingredients tab
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            addBefore(Items.SHULKER_BOX, TTBlocks.TURTLE_TOTE, event);
        }
    }


    private static void addBefore(Item before, Supplier<? extends ItemLike> inputItem, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putBefore(before.getDefaultInstance(), inputItem.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void addAfter(Item after, Supplier<? extends ItemLike> inputItem, BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(after.getDefaultInstance(), inputItem.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
