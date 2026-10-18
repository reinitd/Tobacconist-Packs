package com.reinitd.tobacconistpacks.menu;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TobacconistPacks.MODID);
    public static final DeferredHolder<MenuType<?>, MenuType<CigarettePackMenu>> CIGARETTE_PACK =
            MENUS.register("cigarette_pack", () -> IMenuTypeExtension.create(CigarettePackMenu::new));
    public static final DeferredHolder<MenuType<?>, MenuType<CigarBoxMenu>> CIGAR_BOX =
            MENUS.register("cigar_box", () -> IMenuTypeExtension.create(CigarBoxMenu::new));
}
