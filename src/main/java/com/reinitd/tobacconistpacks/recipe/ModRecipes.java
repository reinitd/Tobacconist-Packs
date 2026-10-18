package com.reinitd.tobacconistpacks.recipe;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, TobacconistPacks.MODID);

    // Recipe JSON uses this as "type": "tobacconistpacks:pack_branding"
    public static final DeferredHolder<RecipeSerializer<?>, PackBrandingRecipe.Serializer> PACK_BRANDING =
            RECIPE_SERIALIZERS.register("pack_branding", PackBrandingRecipe.Serializer::new);
}
