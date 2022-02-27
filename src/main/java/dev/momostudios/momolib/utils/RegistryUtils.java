package dev.momostudios.momolib.utils;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class RegistryUtils
{

    public static Item registerItem(Identifier id, FabricItemSettings settings, Class<? extends Item> itemClass)
    {
        Item placeHolder = new Item(settings);
        try
        {
            //itemClass = (Class<Item>) (Object) itemClass;
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

    public static Block registerBlock(Identifier id, FabricBlockSettings blockSettings, Class<? extends Block> blockClass, FabricItemSettings itemSettings)
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

    public static ArmorMaterial registerArmorMaterial(Class<? extends ArmorMaterial> armorClass)
    {
        ArmorMaterial placeHolder = new ArmorMaterial()
        {
            @Override
            public int getDurability(EquipmentSlot slot)
            {
                return 0;
            }

            @Override
            public int getProtectionAmount(EquipmentSlot slot)
            {
                return 0;
            }

            @Override
            public int getEnchantability()
            {
                return 0;
            }

            @Override
            public SoundEvent getEquipSound()
            {
                return null;
            }

            @Override
            public Ingredient getRepairIngredient()
            {
                return null;
            }

            @Override
            public String getName()
            {
                return null;
            }

            @Override
            public float getToughness()
            {
                return 0;
            }

            @Override
            public float getKnockbackResistance()
            {
                return 0;
            }
        };
        {
        };
        try
        {
            placeHolder = armorClass.getConstructor().newInstance();
        }
        catch(Exception E)
        {

        }
        return placeHolder;
    }

    //public static BlockEntityType<WizardryStationBlockEntity> WIZARDRY_STATION;
    /*public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String modID, String id, Class<T> blockEntityClass, Block block)
    {
        BlockEntityType<T> placeHolder = null;
        try
        {
            placeHolder = Registry.register(Registry.BLOCK_ENTITY_TYPE, modID + ":" + id, FabricBlockEntityTypeBuilder.create(blockEntityClass.getConstructor(BlockPos.class, BlockState.class)::new, block).build(null));
        }
        catch(Exception E)
        {

        }
        return placeHolder;
        //WIZARDRY_STATION = Registry.register(Registry.BLOCK_ENTITY_TYPE, "superstition:wizardry_station_entity", FabricBlockEntityTypeBuilder.create(WizardryStationBlockEntity::new, BlockInit.WIZARDRY_STATION).build(null));
    }*/
}
