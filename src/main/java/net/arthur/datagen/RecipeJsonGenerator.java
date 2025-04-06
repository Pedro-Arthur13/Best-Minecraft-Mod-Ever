package net.arthur.datagen;//package net.arthur.datagen;
//
//import net.minecraft.client.input.Input;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//import static net.arthur.Mod.MOD_ID;
//import static net.arthur.datagen.ModelJsonGenerator.OUTPUT_PATH;
//
//public class RecipeJsonGenerator {
//
//    private static final String MOD_ID = "mod";
//    private static final String OUTPUT_PATH = "src/main/resources/data/" + MOD_ID + "/recipes/";
//
//    public static void main(String[] args) {
//        generateShapedRecipe("glass_boots", List.of("SSS", "PSP", "PSP"), List.of(
//                input('S', "minecraft:air"),
//                input('P',  "minecraft:glass")
//        ));
//
//        generateShapedRecipe("glass_chestplate", List.of("P P", "PPP", "PPP"), List.of(
//                input('P', "minecraft:glass")
//        ));
//
//        generateShapedRecipe("glass_helmet", List.of("PPP", "P P", "   "), List.of(
//                input('P', "minecraft:glass")
//        ));
//    }
//
//    private static Input input(char key, String item) {
//        return new Input(key, item);
//    }
//
//    private static void generateShapedRecipe(String name, List<String> pattern, List<Input> inputs) {
//        File dir = new File(OUTPUT_PATH);
//        if (!dir.exists()) dir.mkdirs();
//
//        File file = new File(OUTPUT_PATH + name + ".json");
//
//        StringBuilder keyBuilder = new StringBuilder();
//        for (Input input : inputs) {
//            keyBuilder.append("""
//              "%c": {
//                "item": "%s"
//              },
//            """.formatted(input.key, input.item));
//        }
//
//        StringBuilder patternBuilder = new StringBuilder();
//        for (String line : pattern) {
//            patternBuilder.append("    \"").append(line).append("\",\n");
//        }
//
//        String jsonContent = """
//            {
//              "type": "minecraft:crafting_shaped",
//              "pattern": [
//%s
//              ],
//              "key": {
//%s
//              },
//              "result": {
//                "item": "%s:%s",
//                "count": 1
//              }
//            }
//            """.formatted(
//                patternBuilder.toString().trim().replaceAll(",$", ""),
//                keyBuilder.toString().trim().replaceAll(",$", ""),
//                MOD_ID, name
//        );
//
//        try (FileWriter writer = new FileWriter(file)) {
//            writer.write(jsonContent);
//            System.out.println("Receita gerada: " + file.getPath());
//        } catch (IOException e) {
//            System.err.println("Erro ao gerar " + name + ": " + e.getMessage());
//        }
//    }
//
//    private static class Input {
//        char key;
//        String item;
//
//        Input(char key, String item) {
//            this.key = key;
//            this.item = item;
//        }
//    }
//}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static net.arthur.Mod.MOD_ID;

public class RecipeJsonGenerator {
    private static final String RECIPES_PATH = "src/main/resources/data/" + MOD_ID + "/recipes/";
    private static final String ADVANCEMENTS_PATH = "src/main/resources/data/" + MOD_ID + "/advancements/recipes/" + MOD_ID + "/";

    public static void main(String[] args) {
        generateShapedRecipe("glass_boots", List.of("SSS", "PSP", "PSP"), List.of(
                new Input('S', "minecraft:air"),
                new Input('P', "minecraft:glass")
        ));
        generateShapedRecipe("glass_chestplate", List.of("G G", "GGG", "GGG"), List.of(
                new Input('G', "minecraft:glass")
        ));
        generateShapedRecipe("glass_leggings", List.of("GGG", "G G", "G G"), List.of(
                new Input('G', "minecraft:glass")
        ));
        generateShapedRecipe("glass_helmet", List.of("GGG", "G G", "   "), List.of(
                new Input('G', "minecraft:glass")
        ));
    }

    private static void generateShapedRecipe(String name, List<String> pattern, List<Input> inputs) {
        File recipesDir = new File(RECIPES_PATH);
        if (!recipesDir.exists()) recipesDir.mkdirs();

        File recipeFile = new File(RECIPES_PATH + name + ".json");

        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder criteriaBuilder = new StringBuilder();
        StringBuilder requirementBuilder = new StringBuilder("[[");

        boolean first = true;
        for (Input input : inputs) {
            keyBuilder.append("""
          "%c": {
            "item": "%s"
          },
        """.formatted(input.key, input.item));

            String criterionName = "has_" + input.item.replace(':', '_');
            criteriaBuilder.append("""
          "%s": {
            "trigger": "minecraft:inventory_changed",
            "conditions": {
              "items": [
                { "item": "%s" }
              ]
            }
          },
        """.formatted(criterionName, input.item));

            if (!first) requirementBuilder.append(", ");
            requirementBuilder.append("\"").append(criterionName).append("\"");
            first = false;
        }
        requirementBuilder.append("]]");

        StringBuilder patternBuilder = new StringBuilder();
        for (String line : pattern) {
            patternBuilder.append("    \"").append(line).append("\",\n");
        }

        String recipeJson = """
        {
          "type": "minecraft:crafting_shaped",
          "pattern": [
%s
          ],
          "key": {
%s
          },
          "result": {
            "item": "%s:%s",
            "count": 1
          },
          "criteria": {
%s
          },
          "requirements": %s
        }
        """.formatted(
                patternBuilder.toString().trim().replaceAll(",$", ""),
                keyBuilder.toString().trim().replaceAll(",$", ""),
                MOD_ID, name,
                criteriaBuilder.toString().trim().replaceAll(",$", ""),
                requirementBuilder.toString()
        );

        try (FileWriter writer = new FileWriter(recipeFile)) {
            writer.write(recipeJson);
            System.out.println("Receita gerada: " + recipeFile.getPath());
        } catch (IOException e) {
            System.err.println("Erro ao gerar receita " + name + ": " + e.getMessage());
        }

        // ✅ Corrigido aqui
        generateAdvancementForRecipe(name, inputs);
    }

    private static void generateAdvancementForRecipe(String name, List<Input> inputs) {
        File advDir = new File(ADVANCEMENTS_PATH);
        if (!advDir.exists()) advDir.mkdirs();

        File advFile = new File(ADVANCEMENTS_PATH + name + ".json");

        StringBuilder criteriaBuilder = new StringBuilder();
        StringBuilder requirementsBuilder = new StringBuilder("[[");

        boolean first = true;
        for (Input input : inputs) {
            String criterionName = "has_" + input.item.replace(':', '_');
            criteriaBuilder.append("""
            "%s": {
              "trigger": "minecraft:inventory_changed",
              "conditions": {
                "items": [
                  { "item": "%s" }
                ]
              }
            },
        """.formatted(criterionName, input.item));

            if (!first) requirementsBuilder.append(", ");
            requirementsBuilder.append("\"").append(criterionName).append("\"");
            first = false;
        }

        criteriaBuilder.append("""
        "has_the_recipe": {
          "trigger": "minecraft:recipe_unlocked",
          "conditions": {
            "recipe": "%s:%s"
          }
        }
    """.formatted(MOD_ID, name));

        requirementsBuilder.append(", \"has_the_recipe\"]]");

        String advancementJson = """
        {
          "parent": "minecraft:recipes/root",
          "rewards": {
            "recipes": [
              "%s:%s"
            ]
          },
          "criteria": {
    %s
          },
          "requirements": %s
        }
        """.formatted(
                MOD_ID, name,
                criteriaBuilder.toString().trim(),
                requirementsBuilder.toString()
        );

        try (FileWriter writer = new FileWriter(advFile)) {
            writer.write(advancementJson);
            System.out.println("Advancement gerado: " + advFile.getPath());
        } catch (IOException e) {
            System.err.println("Erro ao gerar advancement " + name + ": " + e.getMessage());
        }
    }

    private static class Input {
        char key;
        String item;

        Input(char key, String item) {
            this.key = key;
            this.item = item;
        }
    }
}
