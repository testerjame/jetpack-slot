package dev.jetpackslot.mixin;

import dev.jetpackslot.JetpackSlot;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/** Right-clicking the pack now equips it into the jetpack slot instead of the chest slot. */
@Mixin(targets = "dev.aeroforge.Aeroforge$1")
public class PackItemUseMixin {
    @Redirect(method = "use", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/world/entity/player/Player;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack jetpackslot$slotContents(Player p, EquipmentSlot slot) {
        return p.getItemBySlot(JetpackSlot.STORAGE);   // empty => the original code goes on to equip
    }

    @Redirect(method = "use", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/world/entity/player/Player;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    private void jetpackslot$equip(Player p, EquipmentSlot slot, ItemStack stack) {
        p.setItemSlot(JetpackSlot.STORAGE, stack);
    }
}
