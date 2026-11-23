package dev.jetpackslot.mixin;

import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AvatarRenderState.class)
public class AvatarRenderStateMixin implements RenderPackHolder {
    @Unique private ItemStack jetpackslot$pack = ItemStack.EMPTY;
    @Override public ItemStack jetpackslot$getPack() { return jetpackslot$pack; }
    @Override public void jetpackslot$setPack(ItemStack s) { jetpackslot$pack = s; }
}
