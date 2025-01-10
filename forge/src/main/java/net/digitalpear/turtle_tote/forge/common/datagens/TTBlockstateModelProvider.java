package net.digitalpear.turtle_tote.forge.common.datagens;


import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.common.TurtleToteBlock;
import net.digitalpear.turtle_tote.init.TTBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TTBlockstateModelProvider extends BlockStateProvider {

    public ModelBuilder<BlockModelBuilder> TOTE_MODEL = sideBottomTop(TTBlocks.TURTLE_TOTE.get());
    public ModelBuilder<BlockModelBuilder> TOTE_OPEN_MODEL = sideBottomTop(TTBlocks.TURTLE_TOTE.get(), "_open");

    public TTBlockstateModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TurtleTote.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    public void createToteStates(Block block){
        this.simpleBlockItem(block, TOTE_MODEL);
        this.getVariantBuilder(block).forAllStates(blockState -> {
            ModelBuilder<BlockModelBuilder> model = TOTE_MODEL;
            if (blockState.getValue(TurtleToteBlock.OPEN)){
                model = TOTE_OPEN_MODEL;
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
        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);
        return models().withExistingParent(name.getPath(), name.withPrefix("base_"))
                .texture("side", name.withSuffix("_side"))
                .texture("bottom", name.withSuffix("_bottom"))
                .texture("top", name.withSuffix("_top"));
    }
    private ModelBuilder<BlockModelBuilder> sideBottomTop(Block block, String suffix) {
        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);
        return models().withExistingParent(name.getPath(), name.withPrefix("base_"))
                .texture("side", name.withSuffix("_side"))
                .texture("bottom", name.withSuffix("_bottom"))
                .texture("top", name.withSuffix("_top" + suffix));
    }

}
