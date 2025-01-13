package net.digitalpear.turtle_tote.forge.common.datagens;


import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.common.TurtleToteBlock;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Optional;

public class TTBlockstateModelProvider extends BlockStateProvider {

//    public ModelBuilder<BlockModelBuilder> TOTE_MODEL = sideBottomTop(TTBlocks.TURTLE_TOTE.get());
//    public ModelBuilder<BlockModelBuilder> TOTE_OPEN_MODEL = sideBottomTop(TTBlocks.TURTLE_TOTE.get(), "_open");
public static final ModelTemplate TURTLE_TOTE = block("base_turtle_tote", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE);

    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(TurtleTote.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    public TTBlockstateModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TurtleTote.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createToteStates(TTBlocks.TURTLE_TOTE.get());
        createToteStates(TTBlocks.NETHERITE_TURTLE_TOTE.get());
    }

    public void createToteStates(Block block){
        ModelBuilder<BlockModelBuilder> TOTE_MODEL = sideBottomTop(block);
        ModelBuilder<BlockModelBuilder> TOTE_MODEL_OPEN = sideBottomTop(block, "open");

        this.simpleBlockItem(block, TOTE_MODEL);
        this.getVariantBuilder(block).forAllStates(blockState -> {
            ModelBuilder<BlockModelBuilder> model = TOTE_MODEL;
            if (blockState.getValue(TurtleToteBlock.OPEN)){
                model = TOTE_MODEL_OPEN;
            }
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder()
                    .rotationX(getRotationFromDirection(blockState.getValue(TurtleToteBlock.FACING)))
                    .rotationY((int) blockState.getValue(TurtleToteBlock.FACING).toYRot())
                    .modelFile(model);
            return builder.build();
        });
    }
    private int getRotationFromDirection(Direction direction){
        switch (direction){
            case NORTH, EAST, WEST -> {
                return 90;
            }
            case UP -> {
                return 180;
            }
            case SOUTH -> {
                return -90;
            }
            default -> {
                return 0;
            }
        }
    }
    private ModelBuilder<BlockModelBuilder> sideBottomTop(Block block) {
        return sideBottomTop(block, "");
    }
    private ModelBuilder<BlockModelBuilder> sideBottomTop(Block block, String suffix) {
        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);

        return models().withExistingParent(name.getPath() + suffix, modLoc("base_turtle_tote"))
                .texture("side", name.withSuffix("_side"))
                .texture("bottom", name.withSuffix("_bottom"))
                .texture("top", name.withSuffix("_top" + suffix));
    }
}
