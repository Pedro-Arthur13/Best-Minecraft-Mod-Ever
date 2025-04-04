package net.arthur.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CustomPlayerEntity extends PathAwareEntity {
    private CustomPlayerType playerType;

    public CustomPlayerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.playerType = CustomPlayerType.VNCCS; // Padrão
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("CustomPlayerType", this.playerType.name());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("CustomPlayerType")) {
            this.playerType = CustomPlayerType.valueOf(nbt.getString("CustomPlayerType"));
        }
    }

    public void setPlayerType(CustomPlayerType type) {
        this.playerType = type;
    }

    public CustomPlayerType getPlayerType() {
        return this.playerType;
    }

    @Override
    public Text getName() {
        return Text.of(playerType.name()); // Mostra o nome correto
    }

    public Identifier getTexture() {
        return playerType.getTexture();
    }


    public static DefaultAttributeContainer.Builder createCustomPlayerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1);
    }



}
