package com.reinitd.tobacconistpacks.item;

import com.reinitd.tobacconistpacks.menu.CigarettePackMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

public class CigarettePackItem extends Item {
    public CigarettePackItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        // Only the server opens menus
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            IItemHandler handler = stack.getCapability(Capabilities.ItemHandler.ITEM);
            MenuProvider provider = new SimpleMenuProvider(
                    (containerId, playerInventory, player_) -> new CigarettePackMenu(
                            containerId, playerInventory, handler, usedHand, stack),
                    stack.getHoverName());
            // The hand is read back by CigarettePackMenu client constructor
            serverPlayer.openMenu(provider, buf -> buf.writeEnum(usedHand));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
