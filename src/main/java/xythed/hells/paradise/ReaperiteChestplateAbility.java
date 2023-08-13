package xythed.hells.paradise;


import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;


public class ReaperiteChestplateAbility extends ArmorItem {
    public ReaperiteChestplateAbility(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Teleport Steal").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("Allows the player to save their position and teleport to that position on any time but at the cost of four hearts.").formatted(Formatting.DARK_GRAY));
        tooltip.add(Text.literal("WARNING! You cannot teleport across dimensions!").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, context);
    }

}
