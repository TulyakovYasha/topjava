package ru.javawebinar.topjava.storage;


import ru.javawebinar.topjava.model.MealTo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStorage implements Storage {
    private Map<String, MealTo> map = new ConcurrentHashMap<>();
    private static int counter;

    @Override
    public void save(MealTo meal) {
        synchronized (this) {
            counter++;
        }
        meal.setId(String.valueOf(counter));
        map.put(String.valueOf(counter), meal);
    }

    @Override
    public MealTo get(String id) {
        return map.get(id);
    }

    @Override
    public void update(MealTo meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public List<MealTo> getAll() {
        return new ArrayList<>(map.values());
    }
}
