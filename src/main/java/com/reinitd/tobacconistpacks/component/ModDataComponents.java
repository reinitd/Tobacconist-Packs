package com.reinitd.tobacconistpacks.component;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TobacconistPacks.MODID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemContainerContents>> PACK_CONTENTS = DATA_COMPONENTS.registerComponentType(
            "basic",
            builder -> builder
                    .persistent(ItemContainerContents.CODEC)
                    .networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );
}
