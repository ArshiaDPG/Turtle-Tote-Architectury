package net.digitalpear.turtle_tote.fabric.common.datagens;

import net.digitalpear.turtle_tote.common.TurtleToteBlockEntity;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class TTLanguageProvider extends FabricLanguageProvider {
    public TTLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(TTBlocks.TURTLE_TOTE.get(), "Turtle Tote");
        translationBuilder.add(TurtleToteBlockEntity.TURTLE_TOTE_CONTAINER_NAME, "Turtle Tote");
    }
}
