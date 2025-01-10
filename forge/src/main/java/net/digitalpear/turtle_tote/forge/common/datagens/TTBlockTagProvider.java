package net.digitalpear.turtle_tote.forge.common.datagens;

import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TTBlockTagProvider extends BlockTagsProvider {
    public TTBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TurtleTote.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TTBlocks.TURTLE_TOTE.get());
    }
}
