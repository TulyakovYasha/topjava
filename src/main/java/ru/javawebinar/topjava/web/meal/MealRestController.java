package ru.javawebinar.topjava.web.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Controller
public class MealRestController {
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        ValidationUtil.checkNew(meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        return MealsUtil.getWithExcess(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getFiltered(String startTime, String endTime, String startDate, String endDate) {
        return MealsUtil.getFilteredWithExcess(service.getFiltered(SecurityUtil.authUserId(), startDate.equals("") ? LocalDate.MIN : LocalDate.parse(startDate),
                endDate.equals("") ? LocalDate.MAX : LocalDate.parse(endDate)),
                SecurityUtil.authUserCaloriesPerDay(),
                startTime.equals("") ? LocalTime.MIN : LocalTime.parse(startTime),
                endTime.equals("") ? LocalTime.MAX : LocalTime.parse(endTime));
    }

    public Meal update(Meal meal, int id) {
        ValidationUtil.assureIdConsistent(meal, id);
        return service.create(meal, SecurityUtil.authUserId());
    }
}