package com.reinitd.tobacconistpacks.recipe;

import com.mojang.serialization.MapCodec;
import com.reinitd.tobacconistpacks.item.CigarettePackItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class PackBrandingRecipe extends ShapelessRecipe {
    public PackBrandingRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
    }

    private static PackBrandingRecipe fromShapeless(ShapelessRecipe recipe) {
        return new PackBrandingRecipe(recipe.getGroup(), recipe.category(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.getIngredients());
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack result = getResultItem(registries);
        for (ItemStack stack : input.items()) {
            if (stack.getItem() instanceof CigarettePackItem) {
                return stack.transmuteCopy(result.getItem(), result.getCount());
            }
        }
        return result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PACK_BRANDING.get();
    }
    public static class Serializer implements RecipeSerializer<PackBrandingRecipe> {
        private static final MapCodec<PackBrandingRecipe> CODEC =
                RecipeSerializer.SHAPELESS_RECIPE.codec().xmap(PackBrandingRecipe::fromShapeless, recipe -> recipe);
        private static final StreamCodec<RegistryFriendlyByteBuf, PackBrandingRecipe> STREAM_CODEC =
                RecipeSerializer.SHAPELESS_RECIPE.streamCodec().map(PackBrandingRecipe::fromShapeless, recipe -> recipe);

        @Override
        public MapCodec<PackBrandingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PackBrandingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
