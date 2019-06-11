package ru.javawebinar.topjava.util;

public class CounterUtil {
    public static int mealCounter;
    public static int mealToCounter;

    public static int getMealCounter(){
        synchronized (CounterUtil.class){
            mealCounter++;
        }
        return mealCounter;
    }
    public static int getMealToCounter(){
        synchronized (CounterUtil.class){
            mealToCounter++;
        }
        return mealToCounter;
    }
}
