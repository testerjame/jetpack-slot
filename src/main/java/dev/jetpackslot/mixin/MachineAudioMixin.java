package dev.jetpackslot.mixin;

import dev.jetpackslot.JetpackSlot;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "dev.aeroforge.MachineAudio")
public class MachineAudioMixin {
    @Redirect(method = { "tick" }, at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/player/AbstractClientPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack jetpackslot$chest(AbstractClientPlayer self, EquipmentSlot slot) {
        return JetpackSlot.lookup(self, slot);
    }
}
