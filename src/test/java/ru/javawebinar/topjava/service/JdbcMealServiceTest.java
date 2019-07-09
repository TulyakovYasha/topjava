package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.Profiles;

import static org.slf4j.LoggerFactory.getLogger;

@ActiveProfiles(Profiles.JDBC)
public class JdbcMealServiceTest extends MealServiceTest {
}
