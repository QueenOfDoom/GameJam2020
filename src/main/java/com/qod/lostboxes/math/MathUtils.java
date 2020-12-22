package com.qod.lostboxes.math;

public class MathUtils {
    private MathUtils() {}

    public static float roundFloat(float value, int decimals) {
        return (float) (Math.round(value * Math.pow(10, decimals))/Math.pow(10, decimals));
    }
}
