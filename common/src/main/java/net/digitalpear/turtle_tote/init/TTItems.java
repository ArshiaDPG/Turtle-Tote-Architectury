package net.digitalpear.turtle_tote.init;

import dev.architectury.registry.registries.DeferredRegister;
import net.digitalpear.turtle_tote.TurtleTote;
import net.minecraft.client.gui.screens.inventory.CreativeInventoryListener;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TTItems {
    protected static final DeferredRegister<Item> ITEMS = DeferredRegister.create(TurtleTote.MOD_ID, Registries.ITEM);

    public static void init() {
        ITEMS.register();
    }
}
