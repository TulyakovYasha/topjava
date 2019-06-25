package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


public class MealTestDate {

    public static final int FIRST_USER_MEAL_ID = START_SEQ + 2;
    public static final int SECOND_USER_MEAL_ID = START_SEQ + 3;
    public static final int ADMIN_MEAL_ID = START_SEQ + 4;

    public static final Meal FIRST_USER_MEAL = new Meal(FIRST_USER_MEAL_ID, LocalDateTime.of(2019, 6, 24, 10, 0, 0), "firstUserMeal", 700);
    public static final Meal SECOND_USER_MEAL = new Meal(SECOND_USER_MEAL_ID, LocalDateTime.of(2019, 6, 23, 13, 0, 0), "SecondUserMeal", 900);
    public static final Meal ADMIN_MEAL = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2019, 6, 24, 9, 0, 0), "AdminMeal", 800);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
