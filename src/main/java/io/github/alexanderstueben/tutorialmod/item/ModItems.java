package io.github.alexanderstueben.tutorialmod.item;

import io.github.alexanderstueben.tutorialmod.TutorialMod;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
        () -> new Item(new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));
    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone",
        () -> new Firestone(new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP).durability(8)));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
