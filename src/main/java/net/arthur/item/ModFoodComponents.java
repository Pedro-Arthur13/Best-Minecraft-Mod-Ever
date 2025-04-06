package net.arthur.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent SELOCO_NAO_COMPENSA = new FoodComponent.Builder()
            .hunger(3)
            .saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1), 1.0F)
            .alwaysEdible()
            .build();
}
