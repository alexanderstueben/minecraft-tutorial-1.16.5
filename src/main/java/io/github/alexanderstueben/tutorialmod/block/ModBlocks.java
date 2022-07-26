package io.github.alexanderstueben.tutorialmod.block;

import java.util.function.Supplier;

import io.github.alexanderstueben.tutorialmod.TutorialMod;
import io.github.alexanderstueben.tutorialmod.item.ModItemGroup;
import io.github.alexanderstueben.tutorialmod.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> AMETHYST_ORE = registerBlocks("amethyst_ore",
        () -> new Block(
            AbstractBlock.Properties.of(Material.STONE)
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .requiresCorrectToolForDrops()
            .strength(3F)
        )
    );
    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlocks("amethyst_block",
        () -> new Block(
            AbstractBlock.Properties.of(Material.METAL)
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .requiresCorrectToolForDrops()
            .strength(5f, 6f)
            .sound(SoundType.METAL)
        )
    );

    public static final RegistryObject<Block> FIRESTONE_BLOCK = registerBlocks("firestone_block",
        () -> new FirestoneBlock(
            AbstractBlock.Properties.of(Material.STONE)
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .requiresCorrectToolForDrops()
            .strength(3f)
            .sound(SoundType.STONE)
        )
    );

    private static <T extends Block>RegistryObject<T> registerBlocks(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject);
        return registryObject;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.TUTORIAL_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
