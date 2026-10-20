package com.reinitd.tobacconistpacks.capability;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import com.reinitd.tobacconistpacks.component.ModDataComponents;
import com.reinitd.tobacconistpacks.item.CigarBoxItem;
import com.reinitd.tobacconistpacks.item.CigarBoxItemHandler;
import com.reinitd.tobacconistpacks.item.ModItems;
import com.reinitd.tobacconistpacks.item.PackItemHandler;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = TobacconistPacks.MODID)
public class ModCapabilities {
    private static final int CIGARETTE_COUNT = 20;
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Cigarette packs
        event.registerItem(Capabilities.ItemHandler.ITEM,
                (stack, ctx) -> new PackItemHandler(stack, ModDataComponents.PACK_CONTENTS.get(), CIGARETTE_COUNT),
                ModItems.CIGARETTE_PACKS.values().toArray(new ItemLike[0])
        );

        // Cigar boxes
        event.registerItem(Capabilities.ItemHandler.ITEM,
                (stack, ctx) -> new CigarBoxItemHandler(stack, ModDataComponents.PACK_CONTENTS.get(), CigarBoxItem.SLOTS),
                ModItems.CIGAR_BOXES.values().toArray(new ItemLike[0])
        );
    }
}
