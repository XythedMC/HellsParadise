package xythed.hells.paradise.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xythed.hells.paradise.ModItems;

@Mixin(ServerPlayerEntity.class)
public abstract class KeepInventoryServerMixin extends PlayerEntity{

    public KeepInventoryServerMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }
    

    @ModifyExpressionValue(method = "copyFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isSpectator()Z"))
    private boolean dontDropInventoryServer(boolean original, ServerPlayerEntity oldPlayer){
        return original || oldPlayer.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.REAPERITE_HELMET);
    }
}
