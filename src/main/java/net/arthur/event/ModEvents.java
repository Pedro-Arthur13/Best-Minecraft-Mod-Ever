package net.arthur.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.arthur.item.ModItems;

import java.util.Random;

public class ModEvents {
    public static void registerEvents() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
            if (killedEntity instanceof VillagerEntity villager && villager.isBaby()) {
                Random random = new Random();
                if (random.nextFloat() < 0.15f) { // 15% chance
                    villager.dropStack(new ItemStack(ModItems.ALMA_DE_ESTAGIARIO));
                }
            }
        });
    }
}
