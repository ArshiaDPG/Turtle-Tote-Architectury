package net.digitalpear.turtle_tote.init;

import net.digitalpear.turtle_tote.TurtleTote;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TTItemTags {
    public static final TagKey<Item> TURTLE_TOTE_MUSIC_DISCS = bind("turtle_tote_music_discs");
    private static TagKey<Item> bind(String string) {
        return TagKey.create(Registries.ITEM, TurtleTote.id(string));
    }
}
