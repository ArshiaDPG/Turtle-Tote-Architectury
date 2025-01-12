package net.digitalpear.turtle_tote.forge.common.datagens;

import net.digitalpear.turtle_tote.init.TTItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class TTItemTagProvider extends ItemTagsProvider {
    public TTItemTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> completableFuture2) {
        super(arg, completableFuture, completableFuture2);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(TTItemTags.TURTLE_TOTE_MUSIC_DISCS).add(Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_CHIRP);
    }
}
