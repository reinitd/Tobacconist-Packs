package com.reinitd.tobacconistpacks.item;

import com.reinitd.tobacconistpacks.TobacconistPacks;
import com.reinitd.tobacconistpacks.component.ModDataComponents;
import com.reinitd.tobacconistpacks.menu.CigarBoxMenu;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

public class CigarBoxItem extends Item {
    public static final int SLOTS = 9;
    public static final ResourceLocation FILL_PROPERTY = ResourceLocation.fromNamespaceAndPath(TobacconistPacks.MODID, "fill");
    public CigarBoxItem(Properties properties) {
        super(properties);
    }

    public static float getFillLevel(ItemStack stack) {
        ItemContainerContents contents = stack.get(ModDataComponents.PACK_CONTENTS.get());
        if (contents == null) {
            return 0.0f;
        }

        long occupied = contents.nonEmptyStream().count();
        if (occupied == 0) return 0.0f;
        else if (occupied <= 2) return 0.25f;
        else if (occupied <= 5) return 0.5f;
        else if (occupied <= 8) return 0.75f;
        else return 1.0f;

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        // Only the server opens menus
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            IItemHandler handler = stack.getCapability(Capabilities.ItemHandler.ITEM);
            MenuProvider provider = new SimpleMenuProvider(
                    (containerId, playerInventory, player_) -> new CigarBoxMenu(
                            containerId, playerInventory, handler, usedHand, stack),
                    stack.getHoverName());
            serverPlayer.openMenu(provider, buf -> buf.writeEnum(usedHand));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
