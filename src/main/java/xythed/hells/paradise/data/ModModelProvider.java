package xythed.hells.paradise.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import xythed.hells.paradise.ModItems;

public class ModModelProvider extends FabricModelProvider{
    public ModModelProvider(FabricDataOutput output){
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.REAPERITE_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.REAPERITE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.REAPERITE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.REAPERITE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.REAPERITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOUL, Models.GENERATED);
    }
}