package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface Storage {
     void save(MealTo meal);

     MealTo get(String id);

     void update(MealTo meal);

     void delete(String id);

     List<MealTo> getAll();
}
