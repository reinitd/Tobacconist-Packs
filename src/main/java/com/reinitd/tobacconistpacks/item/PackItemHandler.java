package com.reinitd.tobacconistpacks.item;

import com.reinitd.tobacconistpacks.tag.ModTags;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.neoforged.neoforge.items.ComponentItemHandler;

public class PackItemHandler extends ComponentItemHandler {
    public PackItemHandler(MutableDataComponentHolder parent, DataComponentType<ItemContainerContents> component, int size) {
        super(parent, component, size);
    }

    // Lets you actually take the cigs out
    public static boolean canHold(ItemStack stack) {
        return stack.is(ModTags.CIGARETTES) && stack.canFitInsideContainerItems();
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return stack.isEmpty() || canHold(stack);
    }
}
