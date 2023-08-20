package xythed.hells.paradise;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xythed.hells.paradise.items.ModItems;
import xythed.hells.paradise.networking.ModMessages;

public class HellsParadise implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("hellsparadise");

    @Override
    public void onInitialize() {
		  ModItems.registerModItems();
          ModMessages.registerS2CPackets();
    }

}