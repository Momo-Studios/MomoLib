package dev.momostudios.momolib.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MMRegistryBuilder<R extends IForgeRegistryEntry<R>>
{
    public DeferredRegister<R> registry;
    List<RegistryObject<R>> registryObjects = new ArrayList<>();
    String modID;

    public MMRegistryBuilder(Class<R> registryType, String modID)
    {
        registry = DeferredRegister.create(GameRegistry.findRegistry(registryType), modID);
        registry.register(FMLJavaModLoadingContext.get().getModEventBus());
        this.modID = modID;
    }

    public MMRegistryBuilder<R> add(String id, R entry)
    {
        registryObjects.add(registry.register(id, () -> entry));
        return this;
    }

    public static <T extends IForgeRegistryEntry<T>> MMRegistryBuilder<T> newRegistry(Class<T> registryType, String modID)
    {
        return new MMRegistryBuilder<T>(registryType, modID);
    }

    @Nullable
    public RegistryObject<R> get(String id)
    {
        for (RegistryObject<R> registryObject : registryObjects)
        {
            if (registryObject.getId().equals(new ResourceLocation(modID, id)))
            {
                return registryObject;
            }
        }
        return null;
    }
}
