package net.digitalpear.turtle_tote.fabric.common.datagens;


import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;
public class TTModelProvider extends FabricModelProvider {
    public static final ModelTemplate TURTLE_TOTE = block("base_turtle_tote", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(TurtleTote.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public TTModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        registerTurtleTote(blockModelGenerators, TTBlocks.TURTLE_TOTE.get());
        registerTurtleTote(blockModelGenerators, TTBlocks.NETHERITE_TURTLE_TOTE.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

    }

    public void registerTurtleTote(BlockModelGenerators blockStateModelGenerator, Block tote){
        ResourceLocation toteModel = TURTLE_TOTE.create(tote, TextureMapping.cubeBottomTop(tote), blockStateModelGenerator.modelOutput);
        ResourceLocation toteModelOpen = TURTLE_TOTE.createWithSuffix(tote, "_open", TextureMapping.cubeBottomTop(tote).put(TextureSlot.TOP, TextureMapping.getBlockTexture(tote, "_top_open")), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(tote).with(blockStateModelGenerator.createBooleanModelDispatch(BlockStateProperties.OPEN, toteModelOpen, toteModel)).with(createDownDefaultRotationStates()));
    }

    public static PropertyDispatch createDownDefaultRotationStates() {
        return PropertyDispatch.property(BlockStateProperties.FACING)
                .select(Direction.DOWN, Variant.variant())
                .select(Direction.UP, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                .select(Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))
                .select(Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                .select(Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                .select(Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
    }
}
