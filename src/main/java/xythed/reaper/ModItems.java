package xythed.hells_paradise;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item REAPERITE_INGOT = registerItem("reaperite_ingot", new Item(new FabricItemSettings()));
    public static final Item SOUL = registerItem("soul", new Item(new FabricItemSettings()));

    public static final Item REAPERITE_HELMET = registerItem("reaperite_helmet", new ReaperiteArmorItem(ModArmorMaterials.REAPERITE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item REAPERITE_CHESTPLATE = registerItem("reaperite_chestplate", new ReaperiteArmorItem(ModArmorMaterials.REAPERITE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item REAPERITE_LEGGINGS = registerItem("reaperite_leggings", new ReaperiteArmorItem(ModArmorMaterials.REAPERITE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item REAPERITE_BOOTS = registerItem("reaperite_boots", new ReaperiteArmorItem(ModArmorMaterials.REAPERITE, ArmorItem.Type.BOOTS, new FabricItemSettings()));



    public static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier("hells_paradise", name), item);
	}

	public static void addItemToItemGroupIngredients(FabricItemGroupEntries entries){
		entries.add(REAPERITE_INGOT);
        entries.add(SOUL);
        
    }

    public static void addItemToItemGroupTools(Item item){
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(item));
	}

    public static void addItemToItemGroupCombat(FabricItemGroupEntries entries){
		entries.add(REAPERITE_HELMET);
        entries.add(REAPERITE_CHESTPLATE);
        entries.add(REAPERITE_LEGGINGS);
        entries.add(REAPERITE_BOOTS);
	}

    public static void registerModItems(){
        HellsParadise.LOGGER.info("Registering Mod Items for Hell's Paradise");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToItemGroupIngredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemToItemGroupCombat);
    }
}
