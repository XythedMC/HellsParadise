package xythed.hells.paradise.mixin;

import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xythed.hells.paradise.ReaperiteAttributes;

@Mixin(LivingEntity.class)
public abstract class JumpBoostMixin extends Entity implements Attackable {

    @Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);

    public JumpBoostMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "createLivingAttributes", at = @At(value = "RETURN"))
    private static void addCustomAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir){
        cir.getReturnValue().add(ReaperiteAttributes.GENERIC_JUMP_BOOST);
    }

    @Inject(method = "getJumpVelocity", at = @At("RETURN"), cancellable = true)
    private void applyJumpBoost(CallbackInfoReturnable<Float> cir){
        EntityAttributeInstance instance = getAttributeInstance(ReaperiteAttributes.GENERIC_JUMP_BOOST);
        cir.setReturnValue(cir.getReturnValue() * (float) instance.getValue());
    }

}
