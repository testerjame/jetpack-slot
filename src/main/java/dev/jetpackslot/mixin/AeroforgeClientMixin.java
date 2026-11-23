package dev.jetpackslot.mixin;

import dev.jetpackslot.JetpackSlot;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "dev.aeroforge.AeroforgeClient")
public class AeroforgeClientMixin {
    @Redirect(method = { "lambda$onInitializeClient$1", "sendControl", "setGlide", "onJumpPressed", "onSprintPressed" }, at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack jetpackslot$chest(LocalPlayer self, EquipmentSlot slot) {
        return JetpackSlot.lookup(self, slot);
    }
}
