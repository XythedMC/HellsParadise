package xythed.hells.paradise.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import xythed.hells.paradise.HellsParadiseClient;
import xythed.hells.paradise.ModItems;

public class TeleportPlayerC2SPacket {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //Everything here happens ONLY on the server
        if (HellsParadiseClient.x != 0.0 && HellsParadiseClient.y != 0.0 && HellsParadiseClient.z != 0.0){
            if (player.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.REAPERITE_CHESTPLATE)){
                player.teleport(((ServerWorld)player.getWorld()), HellsParadiseClient.x, HellsParadiseClient.y, HellsParadiseClient.z, 0, 0);
                
                player.sendMessage(Text.literal("Teleported Player!").formatted(Formatting.BLUE));
            }
        } else {
            player.sendMessage(Text.literal("Position Unset! Please press I or the keybind set in the controls tab for Save Position.").formatted(Formatting.RED));
        }
        
        
        
    } 
}
