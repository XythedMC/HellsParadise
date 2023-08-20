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

public class SavePositionC2SPacket {

    public static double x;
    public static double y;
    public static double z;


    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        //Everything here happens ONLY on the server
        if (player.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.REAPERITE_CHESTPLATE)){
            x = player.getX();
            y = player.getY();
            z = player.getZ();
            player.sendMessage(Text.literal("Position Saved!").formatted(Formatting.GREEN));
        }
    }
}
