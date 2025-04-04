package net.arthur.entity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;

public enum CustomPlayerType {
    VNCCS("vnccs13"),
    SPOCK("spock");

    private final String textureName;

    CustomPlayerType(String textureName) {
        this.textureName = textureName;
    }

    public Identifier getTexture() {
        return new Identifier("modid", "textures/entity/" + textureName + ".png");
    }
    public static DefaultAttributeContainer.Builder createCustomPlayerAttributes() {
        return DefaultAttributeContainer.builder()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)   // Vida padrão
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1) // Velocidade padrão
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0); // Dano padrão
    }
}



