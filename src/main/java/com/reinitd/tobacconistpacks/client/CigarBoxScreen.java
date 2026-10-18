package com.reinitd.tobacconistpacks.client;

import com.reinitd.tobacconistpacks.menu.CigarBoxMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CigarBoxScreen extends AbstractContainerScreen<CigarBoxMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/gui/container/generic_54.png");

    // generic_54.png
    private static final int TOP_HEIGHT = 18 + 17; // 35; title plus one socket row
    private static final int BOTTOM_V = 126;
    private static final int BOTTOM_HEIGHT = 96;

    public CigarBoxScreen(CigarBoxMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageHeight = 114 + 18; // 132
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        // Top
        guiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, TOP_HEIGHT);
        // Bottom
        guiGraphics.blit(TEXTURE, leftPos, topPos + TOP_HEIGHT, 0, BOTTOM_V, imageWidth, BOTTOM_HEIGHT);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
