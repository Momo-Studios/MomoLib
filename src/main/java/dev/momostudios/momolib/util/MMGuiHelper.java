package dev.momostudios.momolib.util;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

public class MMGuiHelper
{
    public static void createPlayerInventorySlots(Container container, PlayerInventory playerInv, int xStart, int yStart)
    {
        // Make the addSlot method public temporarily
        Method addSlot;
        try
        {
            addSlot = ObfuscationReflectionHelper.findMethod(Container.class, "addSlot", Slot.class);
        }
        catch (Exception e)
        {
            addSlot = ObfuscationReflectionHelper.findMethod(Container.class, "func_75146_a", Slot.class);
        }
        addSlot.setAccessible(true);

        // Add the slots
        try
        {
            // Main player inventory
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 9; col++)
                {
                    addSlot.invoke(container, new Slot(playerInv, col + (9 * row) + 9, xStart + col * 18, yStart - (4 - row) * 18));
                }
            }

            // Player Hotbar
            for (int col = 0; col < 9; col++)
            {
                addSlot.invoke(container, new Slot(playerInv, col, xStart + col * 18, yStart + 58));
            }
        }
        catch (Exception e) {}
    }
}
