package com.beevocate.mccourse.datagen;

import com.beevocate.mccourse.MCCourseMod;
import com.beevocate.mccourse.block.ModBlocks;
import com.beevocate.mccourse.block.custom.AzuriteLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MCCourseMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AZURITE_BLOCK);
        blockWithItem(ModBlocks.AZURITE_ORE);
        blockWithItem(ModBlocks.AZURITE_NETHER_ORE);
        blockWithItem(ModBlocks.AZURITE_END_ORE);
        blockWithItem(ModBlocks.AZURITE_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.MAGIC_BLOCK);
        blockWithItem(ModBlocks.OBAMIUM_MEME_ORE);
        stairsBlock(((StairBlock) ModBlocks.AZURITE_STAIRS.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.AZURITE_SLAB.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.AZURITE_BUTTON.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.AZURITE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.AZURITE_FENCE.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.AZURITE_FENCE_GATE.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.AZURITE_WALL.get()), blockTexture(ModBlocks.AZURITE_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.AZURITE_DOOR.get()), modLoc("block/azurite_door_bottom"), modLoc("block/azurite_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.AZURITE_TRAPDOOR.get()), modLoc("block/azurite_trapdoor"), true, "cutout");

        blockItem(ModBlocks.AZURITE_STAIRS);
        blockItem(ModBlocks.AZURITE_SLAB);

        blockItem(ModBlocks.AZURITE_PRESSURE_PLATE);
        blockItem(ModBlocks.AZURITE_FENCE_GATE);

        blockItem(ModBlocks.AZURITE_TRAPDOOR, "_bottom");

        customLamp();
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.AZURITE_LAMP.get()).forAllStates(state -> {
            if (state.getValue(AzuriteLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("azurite_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, "block/" + "azurite_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("azurite_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, "block/" + "azurite_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.AZURITE_LAMP.get(), models().cubeAll("azurite_lamp_on",
                ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, "block/" + "azurite_lamp_on")));


    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("mccourse:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("mccourse:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
