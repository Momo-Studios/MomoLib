package dev.momostudios.momolib.utils;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class RegistryUtils
{


    public static Item registerItem(Identifier id, FabricItemSettings settings, Class<Item> itemClass)
    {
        Item placeHolder = new Item(settings);
        try
        {
            placeHolder = itemClass.getConstructor(FabricItemSettings.class).newInstance(settings);
        }
        catch (Exception E)
        {

        }
        net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, id, placeHolder);
        return placeHolder;
    }

    public static Item registerItem(Identifier id, FabricItemSettings settings)
    {
        return registerItem(id, settings, Item.class);
    }

    public static Block registerBlock(Identifier id, FabricBlockSettings blockSettings, Class<Block> blockClass, FabricItemSettings itemSettings)
    {
        Block placeHolder = new Block(blockSettings);
        try
        {
            placeHolder = blockClass.getConstructor(FabricBlockSettings.class).newInstance(blockSettings);
        }
        catch (Exception E)
        {

        }
        net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.BLOCK, id, placeHolder);

        net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, id, new BlockItem(placeHolder, itemSettings));
        return placeHolder;
    }

    public static Block registerBlock(Identifier id, FabricBlockSettings blockSettings, FabricItemSettings itemSettings)
    {
        Block placeHolder = new Block(blockSettings);
        try
        {
            placeHolder =  Block.class.getConstructor(FabricBlockSettings.class).newInstance(blockSettings);
        }
        catch (Exception E)
        {

        }
        net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.BLOCK, id, placeHolder);

        net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, id, new BlockItem(placeHolder, itemSettings));
        return placeHolder;
    }
}
