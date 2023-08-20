package xythed.hells.paradise;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import xythed.hells.paradise.networking.ModMessages;

public class HellsParadiseClient implements ClientModInitializer {
	private static KeyBinding keyBindingSavePos;
	private static KeyBinding keyBindingTeleport;


	boolean canTeleport = false;
	
	@Override
	public void onInitializeClient() {
		ModMessages.registerC2SPackets();

		keyBindingSavePos = KeyBindingHelper.registerKeyBinding(new KeyBinding("SavePosition", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, "Hell's Paradise"));
		keyBindingTeleport = KeyBindingHelper.registerKeyBinding(new KeyBinding("Teleport", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "Hell's Paradise"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBindingSavePos.wasPressed()){
				// send packet
				ClientPlayNetworking.send(ModMessages.SAVE_POSTION_ID, PacketByteBufs.create());
            }	
        });
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBindingTeleport.wasPressed()){
				ClientPlayNetworking.send(ModMessages.TELEPORT_PLAYER_ID, PacketByteBufs.create());
            }
        });
	}
}