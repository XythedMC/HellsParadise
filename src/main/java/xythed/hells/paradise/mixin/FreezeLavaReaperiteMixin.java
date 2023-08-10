package xythed.hells.paradise.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xythed.hells.paradise.ReaperiteBootsAbility;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class FreezeLavaReaperiteMixin extends Entity implements Attackable{

    public FreezeLavaReaperiteMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "applyMovementEffects()V", at = @At("HEAD"))
    public void applyMovementEffects(BlockPos pos, CallbackInfo info) {
        ReaperiteBootsAbility.freezeLava((LivingEntity)(Object) this, this.getWorld(), pos);
    }
}