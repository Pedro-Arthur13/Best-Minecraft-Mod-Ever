package net.arthur.registry;

import net.arthur.entity.CustomPlayerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<CustomPlayerEntity> CUSTOM_PLAYER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("mod", "custom_player"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CustomPlayerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.8f))
                    .build()
    );

    public static void register() {
        System.out.println("Registrando entidades...");
        FabricDefaultAttributeRegistry.register(CUSTOM_PLAYER, CustomPlayerEntity.createCustomPlayerAttributes());
    }
}
