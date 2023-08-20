package xythed.hells.paradise.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import xythed.hells.paradise.networking.packet.SavePositionC2SPacket;
import xythed.hells.paradise.networking.packet.TeleportPlayerC2SPacket;

public class ModMessages {
    public static final Identifier TELEPORT_PLAYER_ID = new Identifier("hellsparadise", "teleport_player");
    public static final Identifier SAVE_POSTION_ID = new Identifier("hellsparadise", "save_position");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(TELEPORT_PLAYER_ID, TeleportPlayerC2SPacket::recieve);
        ServerPlayNetworking.registerGlobalReceiver(SAVE_POSTION_ID, SavePositionC2SPacket::recieve);
    }

    public static void registerS2CPackets(){
        
    }
}
