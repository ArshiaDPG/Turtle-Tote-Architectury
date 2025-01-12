package net.digitalpear.turtle_tote.forge.common.datagens;

import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.common.TurtleToteBlockEntity;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class TTLanguageProvider extends LanguageProvider {
    public TTLanguageProvider(PackOutput output, String locale) {
        super(output, TurtleTote.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(TTBlocks.TURTLE_TOTE.get(), "Turtle Tote");
        add(TTBlocks.NETHERITE_TURTLE_TOTE.get(), "Netherite Turtle Tote");
        add(TurtleToteBlockEntity.TURTLE_TOTE_CONTAINER_NAME, "Turtle Tote");
    }
}
