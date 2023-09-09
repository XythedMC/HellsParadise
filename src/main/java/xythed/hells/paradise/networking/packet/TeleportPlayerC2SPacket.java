package xythed.hells.paradise.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import xythed.hells.paradise.items.ModItems;

public class TeleportPlayerC2SPacket {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //Everything here happens ONLY on the server
        if ( SavePositionC2SPacket.x != 0.0 && SavePositionC2SPacket.y != 0.0 && SavePositionC2SPacket.z != 0.0){
            if (player.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.REAPERITE_CHESTPLATE)){
                if (player.getEntityWorld().getDimension() != SavePositionC2SPacket.dimension){
                    player.teleport(SavePositionC2SPacket.world, SavePositionC2SPacket.x, SavePositionC2SPacket.y, SavePositionC2SPacket.z, 0, 0);
                } else{
                    player.teleport(SavePositionC2SPacket.x, SavePositionC2SPacket.y, SavePositionC2SPacket.z);
                }
                player.damage(player.getDamageSources().genericKill(), 8);
                player.sendMessage(Text.literal("Teleported Player!").formatted(Formatting.BLUE));
            }
        } else {
            player.sendMessage(Text.literal("Position Unset! Please press I or the keybind set in the controls tab for Save Position.").formatted(Formatting.RED));
        }
        
        
        
    } 
}
