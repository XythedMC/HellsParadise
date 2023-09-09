package xythed.hells.paradise.mixin;

import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xythed.hells.paradise.ReaperiteBootsAbility;

@Mixin(LivingEntity.class)
public abstract class FreezeLavaReaperiteMixin extends Entity implements Attackable {


    protected FreezeLavaReaperiteMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "applyMovementEffects", at = @At("HEAD"))
    public void applyMovementEffects(BlockPos pos, CallbackInfo ci) {
        ReaperiteBootsAbility.freezeLava((LivingEntity)(Object) this, this.getWorld(), pos);
    }
}