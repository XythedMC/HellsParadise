package xythed.hells.paradise;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HellsParadise implements ModInitializer {
	
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");



	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		
	}
	
}