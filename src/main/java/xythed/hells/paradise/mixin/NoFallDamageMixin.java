package xythed.hells.paradise.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import xythed.hells.paradise.items.ModItems;

@Mixin(LivingEntity.class)
public abstract class NoFallDamageMixin{

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);


    @ModifyExpressionValue(method = "computeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityType;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean dontTakeFallDamageOnReaperite(boolean original){
        return original || this.getEquippedStack(EquipmentSlot.LEGS).isOf(ModItems.REAPERITE_LEGGINGS);
    }

}
