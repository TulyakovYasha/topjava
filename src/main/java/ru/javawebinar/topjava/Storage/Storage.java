package ru.javawebinar.topjava.Storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

public interface Storage {
     void save(MealTo meal);

     MealTo get(MealTo meal);

     void update(MealTo meal);

     void delete(MealTo meal);
}
