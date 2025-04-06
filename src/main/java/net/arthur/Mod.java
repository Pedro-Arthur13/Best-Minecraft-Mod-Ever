package net.arthur;

import net.arthur.event.ModEvents;
import net.arthur.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mod implements ModInitializer {
	public static final String MOD_ID = "mod";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Inicializa os sons personalizados
		ModSounds.registerModSounds();
		ModItems.registerItems(); // <- aqui está certo
		ModEvents.registerEvents();



		// Registra os comandos personalizados
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			CustomCommands.registerCommands(dispatcher);
		});
	}
}
