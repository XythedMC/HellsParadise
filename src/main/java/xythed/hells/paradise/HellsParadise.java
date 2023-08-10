package xythed.hells.paradise;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;



public class HellsParadise implements ModInitializer {
	
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");



	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		
	}
	
}