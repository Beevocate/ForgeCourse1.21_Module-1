package com.beevocate.mccourse.item;

import com.beevocate.mccourse.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier AZURITE = new ForgeTier(1400, 4, 3f, 18,
            ModTags.Blocks.NEEDS_AZURITE_TOOL, () -> Ingredient.of(ModItems.AZURITE.get()),
            ModTags.Blocks.INCORRECT_FOR_AZURITE_TOOL);
}
