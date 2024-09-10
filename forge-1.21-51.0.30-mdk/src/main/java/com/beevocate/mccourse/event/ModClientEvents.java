package com.beevocate.mccourse.event;


import com.beevocate.mccourse.MCCourseMod;
import com.beevocate.mccourse.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = MCCourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void onComputerFovModifierEvent(ComputeFovModifierEvent event) {
        fovChangeWhenChargingCustomBow(event, ModItems.KAUPEN_BOW);
        fovChangeWhenChargingCustomBow(event, ModItems.SENTIENT_BOW);
    }

    private static void fovChangeWhenChargingCustomBow(ComputeFovModifierEvent event, RegistryObject<Item> sentientBow) {
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == sentientBow.get()) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20f;
            if (deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }
}