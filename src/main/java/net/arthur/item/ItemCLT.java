//package net.arthur.item;
//
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.mob.MobEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//
//import java.util.List;
//
//public class ItemCLT extends Item {
//    public ItemCLT(Settings settings) {
//        super(settings);
//    }
//
//    @Override
//    public void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
//        if (!world.isClient && entity instanceof PlayerEntity player && selected) {
//            List<MobEntity> mobs = world.getEntitiesByClass(MobEntity.class, player.getBoundingBox().expand(5.0), mob -> true);
//
//            for (MobEntity mob : mobs) {
//                // Calcula a direção do jogador para o mob
//                double dx = mob.getX() - player.getX();
//                double dz = mob.getZ() - player.getZ();
//                double mag = Math.sqrt(dx * dx + dz * dz);
//
//                if (mag > 0) {
//                    dx /= mag; // Normaliza
//                    dz /= mag;
//
//                    // Move o mob para longe do jogador (sinais positivos para direção oposta)
//                    mob.setVelocity(dx * 0.4, mob.getVelocity().y, dz * 0.4); // Ajuste a velocidade aqui
//                    mob.velocityDirty = true; // Garante que a velocidade seja aplicada
//
//                }
//            }
//        }
//    }
//}

package net.arthur.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemCLT extends Item {
    public ItemCLT(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player && selected) {
            List<MobEntity> mobs = world.getEntitiesByClass(MobEntity.class, player.getBoundingBox().expand(5.0), mob -> true);

            for (MobEntity mob : mobs) {
                // Calcula a direção do jogador para o mob
                double dx = mob.getX() - player.getX();
                double dz = mob.getZ() - player.getZ();
                double mag = Math.sqrt(dx * dx + dz * dz);

                if (mag > 0) {
                    dx /= mag; // Normaliza
                    dz /= mag;

                    // Verifica se o mob está muito próximo (encurralado)
                    if (mag < 1.5) { // Distância limite ajustável (1.5 blocos)
                        // Lança o mob para cima
                        mob.setVelocity(dx * 0.2, 1.0, dz * 0.2); // 1.0 no eixo Y para lançamento vertical
                        mob.velocityDirty = true;
                    } else {
                        // Move o mob para longe do jogador normalmente
                        mob.setVelocity(dx * 0.4, mob.getVelocity().y, dz * 0.4); // Fuga normal
                        mob.velocityDirty = true;
                    }
                }
            }
        }
    }
}