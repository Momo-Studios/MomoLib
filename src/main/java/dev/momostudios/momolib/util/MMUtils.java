package dev.momostudios.momolib.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Map;

public class MMUtils
{
    public static float toRadians(float input) {
        return input * (float) (Math.PI / 180);
    }

    public static float toDegrees(float input) {
        return input * (float) (180 / Math.PI);
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }

    public static boolean isBetween(Number value, Number min, Number max) {
        return value.doubleValue() >= min.doubleValue() && value.doubleValue() <= max.doubleValue();
    }

    /**
     * Returns a number between the two given values {@code pointA} and {@code pointB}, based on factor.<br>
     * If {@code factor} = 0, returns {@code pointA}. If {@code factor} = {@code range}, returns {@code pointB}.<br>
     * @param pointA The first value.
     * @param pointB The second value.
     * @param factor The "progress" between pointA and pointB.
     * @param rangeMin The minimum of the range of values over which to interpolate.
     * @param rangeMax The maximum of the range of values over which to interpolate.
     * @return The interpolated value.
     */
    public static double blend(double pointA, double pointB, double factor, double rangeMin, double rangeMax)
    {
        if (factor <= rangeMin) return pointA;
        if (factor >= rangeMax) return pointB;
        return ((1 / (rangeMax - rangeMin)) * (factor - rangeMin)) * (pointB - pointA) + pointA;
    }

    public static double getDistance(Entity entity, Vector3d pos)
    {
        double xDistance = Math.max(0, Math.abs(entity.getPosX() - pos.x) - entity.getWidth() / 2);
        double yDistance = Math.max(0, Math.abs((entity.getPosY() + entity.getHeight() / 2) - pos.y) - entity.getHeight() / 2);
        double zDistance = Math.max(0, Math.abs(entity.getPosZ() - pos.z) - entity.getWidth() / 2);
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance + zDistance * zDistance);
    }

    public static double average(double... values)
    {
        double sum = 0;
        for (double value : values)
        {
            sum += value;
        }
        return sum / values.length;
    }

    /**
     * Takes an average of the two values, with weight<br>
     * The weight of an item should NEVER be 0.<br>
     * @param val1 The first value.
     * @param val2 The second value.
     * @param weight1 The weight of the first value.
     * @param weight2 The weight of the second value.
     * @return The weighted average.
     */
    public static double weightedAverage(double val1, double val2, double weight1, double weight2)
    {
        return (val1 * weight1 + val2 * weight2) / (weight1 + weight2);
    }

    /**
     * Takes an average of all the values in the given map, with weight<br>
     * The weight of an item should NEVER be 0.<br>
     * <br>
     * @param values The map of values to average (value, weight).
     * @return The average of the values in the given array.
     */
    public static double weightedAverage(Map<Double, Double> values)
    {
        double sum = 0;
        double weightSum = 0;
        for (Map.Entry<Double, Double> entry : values.entrySet())
        {
            sum += entry.getKey() * entry.getValue();
            weightSum += entry.getValue();
        }
        return sum / weightSum;
    }

    public static void schedule(Runnable runnable, int delayTicks)
    {
        new Object()
        {
            private int ticks = 0;

            public void start()
            {
                MinecraftForge.EVENT_BUS.register(this);
            }

            @SubscribeEvent
            public void tick(TickEvent.ServerTickEvent event)
            {
                if (event.phase == TickEvent.Phase.END)
                {
                    ticks++;
                    if (ticks >= delayTicks)
                        run();
                }
            }

            private void run()
            {
                runnable.run();
                MinecraftForge.EVENT_BUS.unregister(this);
            }
        }.start();
    }

    public static <T> void replaceList(List<T> list, List<T> newList)
    {
        list.clear();
        list.addAll(newList);
    }
}
