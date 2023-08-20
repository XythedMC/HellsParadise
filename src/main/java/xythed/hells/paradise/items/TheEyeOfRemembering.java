package xythed.hells.paradise.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheEyeOfRemembering extends Item {
    public TheEyeOfRemembering(Settings settings) {
        super(settings);
    }

    public static List<LivingEntity> mob_list;

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(mob_list == null && entity instanceof LivingEntity) {
            if(entity.getType() != EntityType.ENDER_DRAGON ||
                    entity.getType() != EntityType.WARDEN ||
                    entity.getType() != EntityType.SKELETON_HORSE ||
                    entity.getType() != EntityType.WITHER ||
                    entity.getType() != EntityType.MOOSHROOM ||
                    entity.getType() != EntityType.ELDER_GUARDIAN){
                mob_list.add(entity);
                user.sendMessage(Text.literal(entity.getType().toString()).append(" Found!"));
                if (mob_list.size() == 1){
                    user.sendMessage(Text.literal("So have you found out already? Its powers are remembering all the mobs you find excluding bosses, Skeleton horses, mooshrooms and jockeys and when all 70 mobs are found... you could reach a whole new world..."));
                }
                }
        } else if (!mob_list.contains(entity) && entity instanceof LivingEntity) {
            if (mob_list.size() != 70){
                mob_list.add(entity);
                user.sendMessage(Text.literal(entity.getType().toString()).append(" Found!"));
                NbtCompound nbtData = new NbtCompound();
                nbtData.putInt("hellsparadise.entity_list", (mob_list.size() + 1));
                if(stack.hasNbt()){
                    user.getOffHandStack().setNbt(new NbtCompound());
                    user.getOffHandStack().setNbt(nbtData);
                } else{
                    user.getOffHandStack().setNbt(nbtData);
                }
            } else {
                user.sendMessage(Text.literal("Found all needed mobs!").formatted(Formatting.GREEN));
                user.getInventory().removeStack(40);

            }
        }


        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("A mysterious eye that can remember.... right click a mob with the eye in the offhand to find out what"));
        if (stack.hasNbt()) {
            int mobs = stack.getNbt().getInt("hellsparadise.entity_list");
            tooltip.add(Text.literal("Mobs found: ").append(String.valueOf(mobs)).append("out of ").append("70"));
        }
    }

}
