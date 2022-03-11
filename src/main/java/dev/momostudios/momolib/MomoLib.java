package dev.momostudios.momolib;

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
    }
}
