package net.digitalpear.turtle_tote.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.common.TurtleToteBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class TTBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(TurtleTote.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> TURTLE_TOTE = registerBlock("turtle_tote", () ->
            new TurtleToteBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)
                    .explosionResistance(10).noOcclusion().forceSolidOn().pushReaction(PushReaction.DESTROY)
                    .mapColor(MapColor.EMERALD).isSuffocating((blockState, blockGetter, blockPos) -> false)));

    public static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        TTItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().stacksTo(1)));
        return toReturn;
    }

    public static void init() {
        BLOCKS.register();
    }
}
