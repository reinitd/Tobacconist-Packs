package com.reinitd.tobacconistpacks.menu;

import com.reinitd.tobacconistpacks.item.PackItemHandler;
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

public class CigarettePackMenu extends AbstractContainerMenu {
    // Layout in pixels from the GUI's top left corner, matching vanilla's 3 row chest (ChestMenu)
    public static final int SLOT_SIZE = 18;
    public static final int INVENTORY_LEFT = 8;
    public static final int INVENTORY_WIDTH = 9 * SLOT_SIZE;
    public static final int PACK_TOP = 18;
    public static final int PLAYER_INVENTORY_TOP = 85;
    public static final int HOTBAR_TOP = 143;
    public static final int[] PACK_ROW_SIZES = {7, 6, 7};
    public static final int PACK_SLOTS = 20;

    // exclusive
    private static final int INV_START = PACK_SLOTS;
    private static final int HOTBAR_START = INV_START + 27; // 47
    private static final int HOTBAR_END = HOTBAR_START + 9; // 56

    private final InteractionHand hand;
    private final ItemStack packStack;

    // Client constructor. The stand-in handler uses the same filter as the server's, so the client never predicts a placement the server refuses
    public CigarettePackMenu(int containerId, Inventory playerInv, RegistryFriendlyByteBuf extraData) {
        this(containerId, playerInv, new ItemStackHandler(PACK_SLOTS) {
            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return PackItemHandler.canHold(stack);
            }
        }, extraData.readEnum(InteractionHand.class), ItemStack.EMPTY);
    }

    // Server constructor
    public CigarettePackMenu(int containerId, Inventory playerInv, IItemHandler handler, InteractionHand hand, ItemStack packStack) {
        super(ModMenus.CIGARETTE_PACK.get(), containerId);
        this.hand = hand;
        this.packStack = packStack;

        addPackSlots(handler);
        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);
    }

    // Utilized Opus 5.5 since I HATE GUI AND MATH: addPackSlots, addPlayerInventory, addPlayerHotbar.
    // Menu slots 0 to 19, staggered 7-6-7. Every row is centered, so the 6 row sits half a slot in, between the gaps of the 7 rows
    private void addPackSlots(IItemHandler handler) {
        int index = 0;
        for (int row = 0; row < PACK_ROW_SIZES.length; row++) {
            int rowWidth = PACK_ROW_SIZES[row] * SLOT_SIZE;
            int startX = INVENTORY_LEFT + (INVENTORY_WIDTH - rowWidth) / 2; // 26 for 7 slots, 35 for 6
            int y = PACK_TOP + row * SLOT_SIZE;
            for (int col = 0; col < PACK_ROW_SIZES[row]; col++) {
                this.addSlot(new ItemHandlerCopySlot(handler, index++, startX + col * SLOT_SIZE, y));
            }
        }
    }

    // Menu slots 20 to 46, Inventory indexes 9 to 35. Same position as a 3 row chest
    private void addPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, INVENTORY_LEFT + col * SLOT_SIZE, PLAYER_INVENTORY_TOP + row * SLOT_SIZE));
            }
        }
    }

    // Menu slots 47 to 55, Inventory indexes 0 to 8
    private void addPlayerHotbar(Inventory playerInv) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, INVENTORY_LEFT + col * SLOT_SIZE, HOTBAR_TOP));
        }
    }

    // Human-written below
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
                // Try to move the inv/hotbar slot into the pack's inv
                if (!this.moveItemStackTo(rawStack, 0, PACK_SLOTS, false)) {
                    // Pack is full or refused it: try to move main inv into hotbar
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

            // QM performed on the pack's inv, try to move to player inv/hotbar
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

        return player.getItemInHand(hand) == packStack && !packStack.isEmpty();
    }
}
