package net.digitalpear.turtle_tote.fabric.common.datagens;

import net.digitalpear.turtle_tote.init.TTBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class TTBlockTagProvider extends FabricTagProvider<Block> {
    public TTBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(TTBlocks.TURTLE_TOTE.get());
        getOrCreateTagBuilder(BlockTags.PREVENT_MOB_SPAWNING_INSIDE).add(TTBlocks.TURTLE_TOTE.get());
    }
}
