package ua.regin.gif.utils;

import java.util.Random;

public class RandomHelper {

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}

