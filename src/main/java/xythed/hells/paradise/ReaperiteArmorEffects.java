package xythed.hells.paradise;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import io.netty.util.internal.ThreadLocalRandom;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import xythed.hells.paradise.items.ModItems;
import xythed.hells.paradise.items.ReaperiteArmorItem;


public class ReaperiteArmorEffects {
    private ReaperiteArmorEffects() {}
    public static final AbilitySource src = Pal.getAbilitySource(new Identifier("hellsparadise"));

    public static boolean reaperiteArmorPreventsDamage(LivingEntity entity){
        int parts = 0;
        for (ReaperiteArmorItem item : ReaperiteArmorItem.ITEMS){
            if (entity.getEquippedStack(item.getSlotType()).isOf(item)){
                parts++;
            }
        }
        return ThreadLocalRandom.current().nextDouble() < parts / 4d;
    }

    public static boolean allowFlight(PlayerEntity player) {
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        if (chest.getItem() == ModItems.REAPERITE_CHESTPLATE) {
            return true;
        }
        return false;
    }

    public static boolean canTankFlyIntoWall(ItemStack helmet) {
        return helmet.getItem() == ModItems.REAPERITE_HELMET.asItem();
    }

    public static boolean canTankFall(ItemStack boots) {
        return boots.getItem() == ModItems.REAPERITE_BOOTS.asItem();
    }

    public static void init(){
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            for (var player : world.getPlayers()) {
                if (allowFlight(player)){
                    src.grantTo(player, VanillaAbilities.ALLOW_FLYING);
                } else {
                    src.revokeFrom(player, VanillaAbilities.ALLOW_FLYING);
                }
            }
        });

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (reaperiteArmorPreventsDamage(entity)){
                return false;
            }
            // Find a suitable stack that can "tank" the damage
            ItemStack tankingStack = null;
            EquipmentSlot es = null;
            if (source == entity.getDamageSources().flyIntoWall()) {
                es = EquipmentSlot.HEAD;
                ItemStack head = entity.getEquippedStack(es);
                if (ReaperiteArmorEffects.canTankFlyIntoWall(head)) {
                    tankingStack = head;
                }
            } else if (source == entity.getDamageSources().fall()) {
                es = EquipmentSlot.FEET;
                ItemStack head = entity.getEquippedStack(es);
                if (ReaperiteArmorEffects.canTankFall(head)) {
                    tankingStack = head;
                }
            }
            // Have the stack tank the damage
            if (tankingStack != null) {
                int intAmount = (int) Math.ceil(amount);
                final EquipmentSlot equipmentSlot = es;
                tankingStack.damage(intAmount, entity, p -> entity.sendEquipmentBreakStatus(equipmentSlot));
                return false;
            }
            return true;
        });
    }
}
