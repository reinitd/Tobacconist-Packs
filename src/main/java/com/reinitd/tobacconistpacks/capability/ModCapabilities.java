package com.reinitd.tobacconistpacks.capability;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import com.reinitd.tobacconistpacks.component.ModDataComponents;
import com.reinitd.tobacconistpacks.item.CigarBoxItem;
import com.reinitd.tobacconistpacks.item.CigarBoxItemHandler;
import com.reinitd.tobacconistpacks.item.ModItems;
import com.reinitd.tobacconistpacks.item.PackItemHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = TobacconistPacks.MODID)
public class ModCapabilities {
    private static final int CIGARETTE_COUNT = 20;
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.ItemHandler.ITEM,
                (stack, ctx) -> new PackItemHandler(stack, ModDataComponents.PACK_CONTENTS.get(), CIGARETTE_COUNT),
                ModItems.CIGARETTE_PACK_BLANK,
                ModItems.CIGARETTE_PACK_AMERICAN_SPIRIT_BLACK,
                ModItems.CIGARETTE_PACK_AMERICAN_SPIRIT_LIGHT_BLUE,
                ModItems.CIGARETTE_PACK_CAMEL_BLUE,
                ModItems.CIGARETTE_PACK_CAMEL_GOLD,
                ModItems.CIGARETTE_PACK_CAMEL_ROYAL,
                ModItems.CIGARETTE_PACK_CHESTERFIELD_FILTERS,
                ModItems.CIGARETTE_PACK_LM_BLUE,
                ModItems.CIGARETTE_PACK_MARLBORO_GOLD,
                ModItems.CIGARETTE_PACK_MARLBORO_RED,
                ModItems.CIGARETTE_PACK_NEWPORT
        );
        // Boxes store their contents in the same component type as packs; each handler filters what goes in
        event.registerItem(Capabilities.ItemHandler.ITEM,
                (stack, ctx) -> new CigarBoxItemHandler(stack, ModDataComponents.PACK_CONTENTS.get(), CigarBoxItem.SLOTS),
                ModItems.CIGAR_BOX_SPRUCE
        );
    }
}
