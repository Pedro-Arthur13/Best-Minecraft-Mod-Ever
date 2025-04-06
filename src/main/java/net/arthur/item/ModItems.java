package net.arthur.item;

import net.arthur.item.ItemCLT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class ModItems {
    public static final Item CLT = registerItem("clt",
            new ItemCLT(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC)));

    public static final Item ESCALA_6_1 = registerItem("escala_6_1",
            new Item(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC)));

    public static final Item ALMA_DE_ESTAGIARIO = registerItem("alma_de_estagiario",
            new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));

    public static final Item SELOCO_NAO_COMPENSA = registerItem("seloco_nao_compensa",
            new Item(new FabricItemSettings().food(ModFoodComponents.SELOCO_NAO_COMPENSA).group(ItemGroup.FOOD)));

    public static final Item CONTRATO = registerItem("contrato",
            new Item(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("mod", name), item);
    }

    public static void registerItems() {
        // Já estão sendo registrados em registerItem()
    }
}
