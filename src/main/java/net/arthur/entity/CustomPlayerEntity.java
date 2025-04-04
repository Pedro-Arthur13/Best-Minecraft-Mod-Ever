package net.arthur.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.HostileEntity; // Se for uma entidade hostil
import net.minecraft.world.World;

public class CustomPlayerEntity extends MobEntity {
    public CustomPlayerEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    // Método para registrar atributos corretamente
    public static DefaultAttributeContainer.Builder createCustomPlayerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)  // Vida padrão do jogador
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)  // Velocidade de movimento
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);  // Dano de ataque
    }
}
