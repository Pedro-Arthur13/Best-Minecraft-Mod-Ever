package net.arthur.datagen;// ❌ Remova esta linha:
// import net.minecraft.client.input.Input;

// ✅ Mantenha sua própria classe Input:
public class Input {
    char key;
    String item;

    Input(char key, String item) {
        this.key = key;
        this.item = item;
    }
}
