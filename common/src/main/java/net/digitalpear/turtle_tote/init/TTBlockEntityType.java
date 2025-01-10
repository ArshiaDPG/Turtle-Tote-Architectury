package net.digitalpear.turtle_tote.init;

import com.mojang.datafixers.types.Type;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.digitalpear.turtle_tote.TurtleTote;
import net.digitalpear.turtle_tote.common.TurtleToteBlockEntity;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TTBlockEntityType {
    protected static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(TurtleTote.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<TurtleToteBlockEntity>> TURTLE_TOTE = BLOCK_ENTITY_TYPE.register("turtle_tote",
            () -> BlockEntityType.Builder.of(TurtleToteBlockEntity::new, TTBlocks.TURTLE_TOTE.get()).build(type("turtle_tote"))
            );

    private static Type<?> type(String name){
        return Util.fetchChoiceType(References.BLOCK_ENTITY, name);
    }

    public static void init() {
        BLOCK_ENTITY_TYPE.register();
    }
}
