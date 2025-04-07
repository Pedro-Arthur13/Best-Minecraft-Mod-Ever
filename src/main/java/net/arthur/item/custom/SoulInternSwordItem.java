package net.arthur.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class SoulInternSwordItem extends SwordItem {

    public SoulInternSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            Vec3d start = user.getCameraPosVec(1.0F);
            Vec3d rotation = user.getRotationVec(1.0F);
            Vec3d end = start.add(rotation.multiply(30)); // 30 blocos de alcance

            BlockHitResult hitResult = world.raycast(new RaycastContext(
                    start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, user));

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                Vec3d pos = hitResult.getPos();

                world.createExplosion(null, pos.x, pos.y, pos.z, 3.0f, false, Explosion.DestructionType.NONE);

                // Efeitos visuais (pode customizar com partículas diferentes ou criar próprias via packet)
                for (int i = 0; i < 30; i++) {
                    double offsetX = (Math.random() - 0.5) * 2;
                    double offsetY = Math.random() * 2;
                    double offsetZ = (Math.random() - 0.5) * 2;
                    ((ServerWorld) world).spawnParticles(ParticleTypes.PORTAL,
                            pos.x + offsetX, pos.y + offsetY, pos.z + offsetZ,
                            1, 0, 0, 0, 0.1);
                }

                user.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
            }
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }
}
