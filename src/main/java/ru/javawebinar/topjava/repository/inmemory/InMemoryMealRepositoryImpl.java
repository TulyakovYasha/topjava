package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Comparator<Meal> DATE_COMPARATOR = (o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime());
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, SecurityUtil.authUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        } else if (get(meal.getId(), userId) == null) {
            return null;
        }
        Map<Integer, Meal> mealMap = repository.computeIfAbsent(userId, HashMap::new);
        mealMap.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> map = repository.get(userId);
        if(map == null){
            return false;
        }
        return map.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> map = repository.get(userId);
        return map !=  null ? map.get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.get(userId).values().stream().sorted(DATE_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFiltered(int userId, LocalDate startDate, LocalDate endDate) {
       return getAll(userId)
                .stream().filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .sorted(DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

}

