package net.arthur;

import net.arthur.registry.ModEntities;
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
		ModEntities.register();


		// Registra os comandos personalizados
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			CustomCommands.registerCommands(dispatcher);
		});
	}
}
