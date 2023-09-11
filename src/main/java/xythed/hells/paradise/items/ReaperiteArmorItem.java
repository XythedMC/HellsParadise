package xythed.hells.paradise.items;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReaperiteArmorItem extends ArmorItem {
	public static final List<ReaperiteArmorItem> ITEMS = new ArrayList<>();
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers = ArrayListMultimap.create();
	private static final ArmorMaterial MATERIAL = new ArmorMaterial() {


		@Override
		public int getDurability(Type type) {
			return 0;
		}

		@Override
		public int getProtection(Type type) {
			return 0;
		}

		@Override
		public int getEnchantability() {
			return 21;
		}

		@Override
		public SoundEvent getEquipSound() {
			return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return null;
		}

		@Override
		public String getName() {
			return "hellsparadise:reaperite";
		}

		@Override
		public float getToughness() {
			return 0;
		}

		@Override
		public float getKnockbackResistance() {
			return 0;
		}

		@Override
		public String toString() {
			return getName().replace("/", ":");
		}
	};

	public ReaperiteArmorItem(Type type, FabricItemSettings settings)   {
		super(MATERIAL, type, settings.customDamage((is, amt, e, cb) -> 0));
		ITEMS.add(this);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		if (slot == EquipmentSlot.LEGS){
			EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(UUID.fromString("8a2c421a-424b-4ec8-83e1-1f4b6d0c450f"), this::getTranslationKey, 0.33, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
			attributeModifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, entityAttributeModifier);
			return attributeModifiers;
		}
		return super.getAttributeModifiers(slot);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
		if(!world.isClient()){
			if(entity instanceof PlayerEntity player){
				if(player.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.REAPERITE_BOOTS) && player.isInLava()){
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false, true));
				}
				if (player.getEquippedStack(EquipmentSlot.LEGS).isOf(ModItems.REAPERITE_LEGGINGS)){
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false, true));
				}
				if (player.getEquippedStack(EquipmentSlot.CHEST).isOf(ModItems.REAPERITE_CHESTPLATE)){
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 0, false, false, true));
				}
				if (player.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.REAPERITE_HELMET)){
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0, false, false, true));
				}
			}
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.empty());
		tooltip.add(Text.literal("Blocks a fourth of all incoming damage.").formatted(Formatting.GRAY));
		tooltip.add(Text.literal("Helmet Ability:").formatted(Formatting.WHITE));
		tooltip.add(Text.literal("Screen Saver").formatted(Formatting.GREEN));
		tooltip.add(Text.literal("Saves the players items on death.").formatted(Formatting.GREEN));
		tooltip.add(Text.empty());
		tooltip.add(Text.literal("Teleport Steal").formatted(Formatting.AQUA));
		tooltip.add(Text.literal("Allows the player to save their position and teleport to that position on any time but at the cost of four hearts.").formatted(Formatting.AQUA));
		tooltip.add(Text.empty());
		tooltip.add(Text.literal("Air Tight").formatted(Formatting.BLUE));
		tooltip.add(Text.literal("Disables the player from taking fall damage and increases players speed and jump height.").formatted(Formatting.BLUE));
		tooltip.add(Text.empty());
		tooltip.add(Text.literal("Lava Walkers").formatted(Formatting.RED));
		tooltip.add(Text.literal("Allows the player to walk on lava.").formatted(Formatting.RED));
	}
}
