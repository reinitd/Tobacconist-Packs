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
            ModItems.CIGARETTE_PACKS.values().forEach(box -> event.accept(box.get()));
            ModItems.CIGAR_BOXES.values().forEach(box -> event.accept(box.get()));
        }
    }
}
