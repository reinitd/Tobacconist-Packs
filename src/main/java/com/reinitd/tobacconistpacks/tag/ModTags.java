package com.reinitd.tobacconistpacks.tag;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<Item> CIGARETTES = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(TobacconistPacks.MODID, "cigarettes")
    );
    public static final TagKey<Item> CIGARS = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(TobacconistPacks.MODID, "cigars")
    );
}
