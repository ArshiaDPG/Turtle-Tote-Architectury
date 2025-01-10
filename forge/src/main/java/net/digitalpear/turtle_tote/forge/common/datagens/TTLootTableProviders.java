package net.digitalpear.turtle_tote.forge.common.datagens;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Set;

public class TTLootTableProviders extends LootTableProvider {
    public TTLootTableProviders(PackOutput arg, Set<ResourceLocation> set, List<SubProviderEntry> list) {
        super(arg, set, list);
    }
}
