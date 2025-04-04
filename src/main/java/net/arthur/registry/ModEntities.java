package net.arthur.registry;

import net.arthur.entity.CustomPlayerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class ModEntities {
    public static final EntityType<CustomPlayerEntity> CUSTOM_PLAYER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("modid", "custom_player"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CustomPlayerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.8f)) // Tamanho padrão de um jogador
                    .build()
    );

    public static void register() {
        System.out.println("Registrando entidades...");

        // REGISTRANDO ATRIBUTOS PARA EVITAR O ERRO
        FabricDefaultAttributeRegistry.register(CUSTOM_PLAYER, CustomPlayerEntity.createCustomPlayerAttributes());
    }
}
