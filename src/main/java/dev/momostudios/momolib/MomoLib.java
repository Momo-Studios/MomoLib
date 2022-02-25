package dev.momostudios.momolib;

import dev.momostudios.momolib.util.MMRegistryBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("momolib")
public class MomoLib
{
    private static final Logger LOGGER = LogManager.getLogger();

    public MomoLib()
    {
        MinecraftForge.EVENT_BUS.register(this);

        MMRegistryBuilder.newRegistry(Block.class, "momolib")
                .add("testblock",     new Block(AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.3f, 1)))
                .add("testblock_two", new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1, 6)));
    }
}
