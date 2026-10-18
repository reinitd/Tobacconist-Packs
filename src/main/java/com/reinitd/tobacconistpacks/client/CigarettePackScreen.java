package com.reinitd.tobacconistpacks.client;

import com.reinitd.tobacconistpacks.menu.CigarettePackMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

// Made by Opus 5.5 I HATE GUI WORK WITH A PASSION
public class CigarettePackScreen extends AbstractContainerScreen<CigarettePackMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/gui/container/generic_54.png");

    // Regions of generic_54.png, in texture pixels. Matches vanilla ContainerScreen for 3 rows
    private static final int TOP_HEIGHT = 3 * 18 + 17; // 71
    private static final int BOTTOM_V = 126;
    private static final int BOTTOM_HEIGHT = 96;
    // The 9x3 socket grid inside the top part
    private static final int GRID_LEFT = 7;
    private static final int GRID_TOP = 17;
    private static final int GRID_RIGHT = GRID_LEFT + 9 * 18; // 169
    private static final int GRID_BOTTOM = GRID_TOP + 3 * 18; // 71
    // The top left socket, reused for every pack slot
    private static final int SOCKET_U = 7;
    private static final int SOCKET_V = 17;
    private static final int SOCKET_SIZE = 18;
    // Vanilla GUI panel color, fully opaque (ARGB)
    private static final int PANEL_GRAY = 0xFFC6C6C6;

    public CigarettePackScreen(CigarettePackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        // Same size as vanilla's 3 row chest. The width stays at the default 176
        this.imageHeight = 114 + 3 * 18; // 168, vanilla ContainerScreen's formula for 3 rows
        // super already set this from the default height of 166, so recompute it for the new height
        this.inventoryLabelY = this.imageHeight - 94;
    }

    // Built from vanilla's chest texture (minecraft namespace), so nothing is copied into this mod and resource packs apply
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        // Top: title area plus vanilla's 9x3 socket grid
        guiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, TOP_HEIGHT);
        // Bottom: player inventory and hotbar, placed directly under the top part
        guiGraphics.blit(TEXTURE, leftPos, topPos + TOP_HEIGHT, 0, BOTTOM_V, imageWidth, BOTTOM_HEIGHT);
        // Paint over the 9x3 sockets, since the pack uses its own 7-6-7 layout
        guiGraphics.fill(leftPos + GRID_LEFT, topPos + GRID_TOP, leftPos + GRID_RIGHT, topPos + GRID_BOTTOM, PANEL_GRAY);
        // One socket per pack slot. Slot x/y is the 16x16 item position; the 18x18 socket border sits 1px outside it
        for (int i = 0; i < CigarettePackMenu.PACK_SLOTS; i++) {
            Slot slot = menu.slots.get(i);
            guiGraphics.blit(TEXTURE, leftPos + slot.x - 1, topPos + slot.y - 1, SOCKET_U, SOCKET_V, SOCKET_SIZE, SOCKET_SIZE);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
