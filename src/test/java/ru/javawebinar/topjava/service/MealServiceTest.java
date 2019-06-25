package ru.javawebinar.topjava.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestDate.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;

    static {
        SLF4JBridgeHandler.install();
    }

    @Test
    public void get() {
        Meal meal = service.get(FIRST_USER_MEAL_ID, USER_ID);
        assertMatch(FIRST_USER_MEAL, meal);
    }

    @Test(expected = NotFoundException.class)
    public void getForeign(){
        service.get(ADMIN_MEAL_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(10000, USER_ID);
    }

    @Test
    public void delete() {
        service.delete(FIRST_USER_MEAL_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), SECOND_USER_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(10000, USER_ID);
    }
    @Test(expected = NotFoundException.class)
    public void deleteForeign(){
        service.delete(ADMIN_MEAL_ID, USER_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> meals = service.getBetweenDates(LocalDate.of(2019, 6, 23), LocalDate.of(2019, 6, 23), USER_ID);
        assertMatch(meals, SECOND_USER_MEAL);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2019, 6, 24, 10,0,0), LocalDateTime.of(2019, 6, 25, 12, 35), USER_ID);
        assertMatch(meals, FIRST_USER_MEAL);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, FIRST_USER_MEAL, SECOND_USER_MEAL);
    }

    @Test
    public void update() {
        Meal meal = new Meal(FIRST_USER_MEAL_ID, LocalDateTime.now(), "updatedUserMeal", 900);
        service.update(meal, USER_ID);
        assertMatch(meal, service.get(FIRST_USER_MEAL_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateForeign(){
        Meal meal = new Meal(ADMIN_MEAL_ID, LocalDateTime.now(), "newAdminMeal", 1000);
        service.update(meal, USER_ID);
    }

    @Test
    public void create() {
        Meal meal = new Meal(LocalDateTime.now(), "secondUserMeal", 1000);
        service.create(meal, USER_ID);
        assertMatch(service.getAll(USER_ID), meal, FIRST_USER_MEAL, SECOND_USER_MEAL);
    }

    @Test(expected = DataAccessException.class)
    public void createDuplicateDateTime(){
        Meal meal = new Meal(LocalDateTime.of(2019, 6, 24, 10, 0, 0), "meal", 900);
        service.create(meal, USER_ID);
    }
}