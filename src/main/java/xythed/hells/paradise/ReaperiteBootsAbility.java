package xythed.hells.paradise;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class ReaperiteBootsAbility extends ArmorItem {
    public ReaperiteBootsAbility(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
        if(!world.isClient()){
            if(entity instanceof PlayerEntity player){
                ItemStack itemStack = player.getEquippedStack(EquipmentSlot.FEET);
                if(itemStack.isOf(ModItems.REAPERITE_BOOTS) && player.isInLava()){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false, true));
                }
            }
        }
    }


    public static void freezeLava(LivingEntity entity, World world, BlockPos blockPos) {
        if (!entity.isOnGround()) {
            return;
        }
        BlockState blockState = Blocks.BASALT.getDefaultState();
        int i = Math.min(16, 2 + 1);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-i, -1, -i), blockPos.add(i, -1, i))) {
            BlockState blockState3;
            if (!blockPos2.isWithinDistance(entity.getPos(), (double)i)) continue;
            mutable.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
            BlockState blockState2 = world.getBlockState(mutable);
            if (!blockState2.isAir() || (blockState3 = world.getBlockState(blockPos2)) != Blocks.LAVA.getDefaultState() || !blockState.canPlaceAt(world, blockPos2) || !world.canPlace(blockState, blockPos2, ShapeContext.absent())) continue;
            world.setBlockState(blockPos2, blockState);
            world.scheduleBlockTick(blockPos2, Blocks.BASALT, MathHelper.nextInt(entity.getRandom(), 60, 120));
        }
    }

    

}
