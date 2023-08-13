package xythed.hells.paradise;


import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;


public class ReaperiteLeggingsAbility extends ArmorItem {
    public ReaperiteLeggingsAbility(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Allows the player to walk on lava").formatted(Formatting.DARK_GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }

}
