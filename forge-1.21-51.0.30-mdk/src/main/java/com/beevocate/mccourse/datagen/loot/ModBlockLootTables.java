package com.beevocate.mccourse.datagen.loot;

import com.beevocate.mccourse.block.ModBlocks;
import com.beevocate.mccourse.block.custom.OnionCropBlock;
import com.beevocate.mccourse.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.AZURITE_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());

        dropSelf(ModBlocks.AZURITE_LAMP.get());

        this.add(ModBlocks.OBAMIUM_MEME_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.OBAMIUM_MEME_ORE.get(), ModItems.RAW_MEME_OBAMIUM.get(),1,3));

        this.add(ModBlocks.AZURITE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.AZURITE_ORE.get(), ModItems.RAW_AZURITE.get(),2,5));

        this.add(ModBlocks.AZURITE_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.AZURITE_DEEPSLATE_ORE.get(), ModItems.RAW_AZURITE.get(),2,5));

        this.add(ModBlocks.AZURITE_NETHER_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.AZURITE_DEEPSLATE_ORE.get(), ModItems.RAW_AZURITE.get(),2,5));

        this.add(ModBlocks.AZURITE_END_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.AZURITE_DEEPSLATE_ORE.get(), ModItems.RAW_AZURITE.get(),2,5));


        this.dropSelf(ModBlocks.AZURITE_STAIRS.get());
        this.dropSelf(ModBlocks.AZURITE_BUTTON.get());

        this.dropSelf(ModBlocks.AZURITE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.AZURITE_FENCE.get());
        this.dropSelf(ModBlocks.AZURITE_WALL.get());

        this.dropSelf(ModBlocks.AZURITE_PRESSURE_PLATE.get());
        this.add(ModBlocks.AZURITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.AZURITE_SLAB.get()));

        this.dropSelf(ModBlocks.AZURITE_TRAPDOOR.get());

        this.add(ModBlocks.AZURITE_DOOR.get(),
                block -> createDoorTable(ModBlocks.AZURITE_DOOR.get()));

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.ONION_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OnionCropBlock.AGE, 3));
        this.add(ModBlocks.ONION_CROP.get(), this.createCropDrops(ModBlocks.ONION_CROP.get(),
                ModItems.ONION.get(), ModItems.ONION_SEEDS.get(), lootItemConditionBuilder));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock,Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
