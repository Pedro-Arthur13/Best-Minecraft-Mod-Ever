package net.arthur.mixin;

import net.arthur.item.ModArmors;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityTickMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void applyGlassArmorInvisibility(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity.world.isClient()) return;

        boolean isDay = entity.world instanceof ServerWorld && entity.world.isDay();
        boolean hasFullGlassArmor =
                isGlass(entity.getEquippedStack(EquipmentSlot.HEAD)) &&
                        isGlass(entity.getEquippedStack(EquipmentSlot.CHEST)) &&
                        isGlass(entity.getEquippedStack(EquipmentSlot.LEGS)) &&
                        isGlass(entity.getEquippedStack(EquipmentSlot.FEET));

        if (isDay && hasFullGlassArmor) {
            // Aplica invisibilidade infinita (renova a cada tick para nunca acabar)
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.INVISIBILITY,
                    200, // mais que suficiente para evitar flicker
                    0,
                    false,
                    false,
                    false
            ));
        } else {
            // Remove o efeito se for noite ou se a armadura estiver incompleta
            if (entity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
                entity.removeStatusEffect(StatusEffects.INVISIBILITY);
            }
        }
    }

    private boolean isGlass(ItemStack stack) {
        return stack.getItem() == ModArmors.GLASS_HELMET ||
                stack.getItem() == ModArmors.GLASS_CHESTPLATE ||
                stack.getItem() == ModArmors.GLASS_LEGGINGS ||
                stack.getItem() == ModArmors.GLASS_BOOTS;
    }
}
