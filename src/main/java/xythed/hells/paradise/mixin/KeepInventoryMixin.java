package xythed.hells.paradise.mixin;
import xythed.hells.paradise.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class KeepInventoryMixin extends LivingEntity{
    protected KeepInventoryMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    
    @ModifyExpressionValue(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean dontDropInventoryOnReaperite(boolean original) {
        return original || this.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.REAPERITE_HELMET);
    }
}
