package com.beevocate.mccourse.datagen;

import com.beevocate.mccourse.MCCourseMod;
import com.beevocate.mccourse.block.ModBlocks;
import com.beevocate.mccourse.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MCCourseMod.MOD_ID, existingFileHelper);
    }


    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,"item/" + item.getId().getPath()));
    }


    @Override
    protected void registerModels() {
        basicItem(ModItems.AZURITE.get());
        basicItem(ModItems.RAW_AZURITE.get());
        basicItem(ModItems.ONION.get());
        basicItem(ModItems.AURORA_ASHES.get());
        basicItem(ModItems.CHAINSAW.get());
        basicItem(ModItems.RAW_MEME_OBAMIUM.get());


        handheldItem(ModItems.AZURITE_SWORD);
        handheldItem(ModItems.AZURITE_PICKAXE);
        handheldItem(ModItems.AZURITE_SHOVEL);
        handheldItem(ModItems.AZURITE_AXE);
        handheldItem(ModItems.AZURITE_HOE);
        handheldItem(ModItems.AZURITE_PAXEL);
        handheldItem(ModItems.AZURITE_HAMMER);

        basicItem(ModItems.AZURITE_BOOTS.get());
        basicItem(ModItems.AZURITE_LEGGINGS.get());
        basicItem(ModItems.AZURITE_CHESTPLATE.get());
        basicItem(ModItems.AZURITE_HELMET.get());

        withExistingParent(ModItems.CAPYBARA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BLOOD_DEMON_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));


        buttonItem(ModBlocks.AZURITE_BUTTON, ModBlocks.AZURITE_BLOCK);
        fenceItem(ModBlocks.AZURITE_FENCE, ModBlocks.AZURITE_BLOCK);
        wallItem(ModBlocks.AZURITE_WALL, ModBlocks.AZURITE_BLOCK);

        simpleBlockItem(ModBlocks.AZURITE_DOOR);

        basicItem(ModItems.METAL_DETECTOR.get());
        basicItem(ModItems.DATA_TABLET.get());
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
