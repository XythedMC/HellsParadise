package xythed.hells.paradise;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import xythed.hells.paradise.networking.ModMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HellsParadise implements ModInitializer {
	
    public static final Logger LOGGER = LoggerFactory.getLogger("hellsparadise");

    @Override
    public void onInitialize() {
		  ModItems.registerModItems();
      ModMessages.registerS2CPackets();
    }

    public void teleportPlayer(PlayerEntity player, double x, double y, double z){
      player.requestTeleport(x, y, z);
    }
}