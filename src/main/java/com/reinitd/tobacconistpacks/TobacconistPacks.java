package com.reinitd.tobacconistpacks;


import com.reinitd.tobacconistpacks.component.ModDataComponents;
import com.reinitd.tobacconistpacks.item.ModItems;
import com.reinitd.tobacconistpacks.menu.ModMenus;
import com.reinitd.tobacconistpacks.recipe.ModRecipes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(TobacconistPacks.MODID)
public class TobacconistPacks {
    public static final String MODID = "tobacconistpacks";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TobacconistPacks(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::addCreative);

        ModItems.ITEMS.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
    }

    private static final ResourceKey<CreativeModeTab> TOBACCONIST_TAB = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath("tobacconistmod", "tobacconistmod")
    );

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == TOBACCONIST_TAB) {
            event.accept(ModItems.CIGARETTE_PACK_BLANK.get());
            event.accept(ModItems.CIGARETTE_PACK_AMERICAN_SPIRIT_BLACK.get());
            event.accept(ModItems.CIGARETTE_PACK_AMERICAN_SPIRIT_LIGHT_BLUE.get());
            event.accept(ModItems.CIGARETTE_PACK_CAMEL_BLUE.get());
            event.accept(ModItems.CIGARETTE_PACK_CAMEL_GOLD.get());
            event.accept(ModItems.CIGARETTE_PACK_CAMEL_ROYAL.get());
            event.accept(ModItems.CIGARETTE_PACK_CHESTERFIELD_FILTERS.get());
            event.accept(ModItems.CIGARETTE_PACK_LM_BLUE.get());
            event.accept(ModItems.CIGARETTE_PACK_MARLBORO_GOLD.get());
            event.accept(ModItems.CIGARETTE_PACK_MARLBORO_RED.get());
            event.accept(ModItems.CIGARETTE_PACK_NEWPORT.get());
            event.accept(ModItems.CIGAR_BOX_SPRUCE.get());
        }
    }
}
