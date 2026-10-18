package com.reinitd.tobacconistpacks.menu;

import com.reinitd.tobacconistpacks.item.CigarBoxItem;
import com.reinitd.tobacconistpacks.item.CigarBoxItemHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerCopySlot;
import net.neoforged.neoforge.items.ItemStackHandler;

public class CigarBoxMenu extends AbstractContainerMenu {
    public static final int SLOT_SIZE = 18;
    public static final int INVENTORY_LEFT = 8;
    public static final int BOX_TOP = 18;
    public static final int PLAYER_INVENTORY_TOP = 49;
    public static final int HOTBAR_TOP = 107;
    public static final int BOX_SLOTS = CigarBoxItem.SLOTS;

    // exclusive
    private static final int INV_START = BOX_SLOTS;
    private static final int HOTBAR_START = INV_START + 27; // 36
    private static final int HOTBAR_END = HOTBAR_START + 9; // 45

    private final InteractionHand hand;
    private final ItemStack boxStack;

    // Client constructor
    public CigarBoxMenu(int containerId, Inventory playerInv, RegistryFriendlyByteBuf extraData) {
        this(containerId, playerInv, new ItemStackHandler(BOX_SLOTS) {
            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return CigarBoxItemHandler.canHold(stack);
            }
        }, extraData.readEnum(InteractionHand.class), ItemStack.EMPTY);
    }

    // Server constructor
    public CigarBoxMenu(int containerId, Inventory playerInv, IItemHandler handler, InteractionHand hand, ItemStack boxStack) {
        super(ModMenus.CIGAR_BOX.get(), containerId);
        this.hand = hand;
        this.boxStack = boxStack;

        addBoxSlots(handler);
        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);
    }

    // Menu slots 0 to 8, one full row
    private void addBoxSlots(IItemHandler handler) {
        for (int col = 0; col < BOX_SLOTS; col++) {
            this.addSlot(new ItemHandlerCopySlot(handler, col, INVENTORY_LEFT + col * SLOT_SIZE, BOX_TOP));
        }
    }

    // Menu slots 9 to 35, Inventory indexes 9 to 35. Same position as a 1 row chest
    private void addPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, INVENTORY_LEFT + col * SLOT_SIZE, PLAYER_INVENTORY_TOP + row * SLOT_SIZE));
            }
        }
    }

    // Menu slots 36 to 44, Inventory indexes 0 to 8
    private void addPlayerHotbar(Inventory playerInv) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, INVENTORY_LEFT + col * SLOT_SIZE, HOTBAR_TOP));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // The quick moved slot stack
        ItemStack stack = ItemStack.EMPTY;

        // The quick moved slot
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack rawStack = slot.getItem();
            stack = rawStack.copy();

            // QM performed on the player inv or hotbar
            if (index >= INV_START) {
                // Try to move the inv/hotbar slot into the box's inv
                if (!this.moveItemStackTo(rawStack, 0, BOX_SLOTS, false)) {
                    // Box is full or refused it: try to move main inv into hotbar
                    if (index < HOTBAR_START) {
                        if (!this.moveItemStackTo(rawStack, HOTBAR_START, HOTBAR_END, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                    // Try to move hotbar into player inv
                    else if (!this.moveItemStackTo(rawStack, INV_START, HOTBAR_START, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            // QM performed on the box's inv, try to move to player inv/hotbar
            else if (!this.moveItemStackTo(rawStack, INV_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            }

            if (rawStack.isEmpty()) {
                // If the raw stack has completely moved out of the slot, set the slot to the empty stack
                slot.set(ItemStack.EMPTY);
            } else {
                // Otherwise, write the remainder back
                slot.set(rawStack);
            }

            slot.onTake(player, rawStack);
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        if (player.level().isClientSide()) {
            return true;
        }

        return player.getItemInHand(hand) == boxStack && !boxStack.isEmpty();
    }
}
