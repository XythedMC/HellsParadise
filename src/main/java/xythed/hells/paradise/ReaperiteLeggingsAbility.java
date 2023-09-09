package xythed.hells.paradise;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;


public class ReaperiteLeggingsAbility extends ArmorItem{

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers = ArrayListMultimap.create();
    public ReaperiteLeggingsAbility(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);

    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.LEGS){
            EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(UUID.fromString("8a2c421a-424b-4ec8-83e1-1f4b6d0c450f"), this::getTranslationKey, 0.33, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            attributeModifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, entityAttributeModifier);
            attributeModifiers.put(ReaperiteAttributes.GENERIC_JUMP_BOOST, ReaperiteAttributes.entityAttributeModifier);
            return attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Air Tight").formatted(Formatting.BLUE));
        tooltip.add(Text.literal("Disables the player from taking fall damage and increases players speed and jump height").formatted(Formatting.DARK_GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }

}
