package net.digitalpear.turtle_tote.fabric.common.datagens;

import net.digitalpear.turtle_tote.init.TTItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class TTItemTagProvider extends FabricTagProvider<Item> {
    public TTItemTagProvider(FabricDataOutput output, CompletableFuture registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        getOrCreateTagBuilder(TTItemTags.TURTLE_TOTE_MUSIC_DISCS)
                .add(
                        Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_CHIRP
                );
    }
}
