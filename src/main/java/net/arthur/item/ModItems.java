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

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("mod", name), item);
    }

    public static void registerItems() {
        // Não precisa registrar novamente aqui, já que o registerItem já faz isso
    }
}