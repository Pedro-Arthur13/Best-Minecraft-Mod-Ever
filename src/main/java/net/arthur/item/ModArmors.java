package net.arthur.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.arthur.Mod;

public class ModArmors {

    // Exemplo de material de armadura customizado (você pode criar mais depois)
    public static final ArmorMaterial GLASS_MATERIAL = ModArmorMaterial.GLASS;

    // Exemplo de armadura (você pode adicionar quantas quiser aqui)
    public static final Item GLASS_HELMET = register("glass_helmet",
            new ArmorItem(GLASS_MATERIAL, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item GLASS_CHESTPLATE = register("glass_chestplate",
            new ArmorItem(GLASS_MATERIAL, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item GLASS_LEGGINGS = register("glass_leggings",
            new ArmorItem(GLASS_MATERIAL, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item GLASS_BOOTS = register("glass_boots",
            new ArmorItem(GLASS_MATERIAL, EquipmentSlot.FEET, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static void registerArmors() {
        // Esse método só serve para garantir que a classe seja carregada
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("mod", name), item);
    }
}
