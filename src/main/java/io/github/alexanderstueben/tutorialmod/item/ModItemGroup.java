package io.github.alexanderstueben.tutorialmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup TUTORIAL_GROUP = new ItemGroup("tutorialModTab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };
}