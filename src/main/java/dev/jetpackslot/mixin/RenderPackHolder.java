package dev.jetpackslot.mixin;

import net.minecraft.world.item.ItemStack;

public interface RenderPackHolder {
    ItemStack jetpackslot$getPack();
    void jetpackslot$setPack(ItemStack stack);
}
