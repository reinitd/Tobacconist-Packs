package com.reinitd.tobacconistpacks;

import com.reinitd.tobacconistpacks.menu.ModMenus;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import com.reinitd.tobacconistpacks.client.CigarettePackScreen;
import com.reinitd.tobacconistpacks.client.CigarBoxScreen;
import com.reinitd.tobacconistpacks.item.CigarBoxItem;
import com.reinitd.tobacconistpacks.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.neoforge.registries.DeferredItem;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = TobacconistPacks.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = TobacconistPacks.MODID, value = Dist.CLIENT)
public class TobacconistPacksClient {
    public TobacconistPacksClient(ModContainer container) {
        // NeoForge mod config screen
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        TobacconistPacks.LOGGER.info("Yes sir yes sir nahm mean nahm mean without further ado");

        // Cigar box fill textures: the model's overrides pick a texture from this value.
        // ItemProperties.register is not thread safe, so it runs on the main thread
        event.enqueueWork(() -> {
            for (DeferredItem<CigarBoxItem> box : ModItems.CIGAR_BOXES.values()) {
                ItemProperties.register(box.get(), CigarBoxItem.FILL_PROPERTY,
                        (stack, level, entity, seed) -> CigarBoxItem.getFillLevel(stack));
            }
        });
    }

    @SubscribeEvent
    static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.CIGARETTE_PACK.get(), CigarettePackScreen::new);
        event.register(ModMenus.CIGAR_BOX.get(), CigarBoxScreen::new);
    }
}
