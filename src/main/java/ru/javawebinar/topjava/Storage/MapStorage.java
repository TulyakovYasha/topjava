package ru.javawebinar.topjava.Storage;


import ru.javawebinar.topjava.model.MealTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage implements Storage {
    Map<Integer, MealTo> map = new HashMap<>();

    @Override
    public void save(MealTo meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public MealTo get(MealTo meal) {
        return map.get(meal.getId());
    }

    @Override
    public void update(MealTo meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public void delete(MealTo meal) {
        map.remove(meal.getId());
    }
    public List<MealTo> getAll(){
        return new ArrayList<>(map.values());
    }
}
