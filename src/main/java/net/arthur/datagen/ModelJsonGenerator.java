package net.arthur.datagen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ModelJsonGenerator {

    // Nome do seu mod
    private static final String MOD_ID = "mod";

    // Caminho de destino
    private static final String OUTPUT_PATH = "src/main/resources/assets/" + MOD_ID + "/models/item/";

    public static void main(String[] args) {
        // Lista de nomes dos itens
        List<String> itemNames = List.of(
                "clt",
                "escala_6_1",
                "alma_de_estagiario",
                "seloco_nao_compensa",
                "glass_leggings",
                "glass_boots",
                "glass_chestplate",
                "glass_helmet"
        );

        generateItemModels(itemNames);
    }

    private static void generateItemModels(List<String> items) {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs(); // Cria o diretório se não existir
        }

        for (String item : items) {
            File file = new File(OUTPUT_PATH + item + ".json");

            String jsonContent = """
                {
                  "parent": "item/generated",
                  "textures": {
                    "layer0": "%s:item/%s"
                  }
                }
                """.formatted(MOD_ID, item);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(jsonContent);
                System.out.println("Gerado: " + file.getPath());
            } catch (IOException e) {
                System.err.println("Erro ao gerar " + item + ": " + e.getMessage());
            }
        }
    }
}
