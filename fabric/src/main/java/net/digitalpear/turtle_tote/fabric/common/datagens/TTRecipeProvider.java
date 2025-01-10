package net.digitalpear.turtle_tote.fabric.common.datagens;


import net.digitalpear.turtle_tote.init.TTBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class TTRecipeProvider extends FabricRecipeProvider {
    public TTRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, TTBlocks.TURTLE_TOTE.get())
                .define('b', Items.BARREL)
                .define('h', Items.TURTLE_HELMET)
                .pattern(" h ")
                .pattern(" b ")
                .pattern(" h ")
                .unlockedBy(getHasName(Items.SCUTE), has(Items.SCUTE))
                .unlockedBy(getHasName(Items.TURTLE_HELMET), has(Items.TURTLE_HELMET))
                .save(consumer);
    }
}
