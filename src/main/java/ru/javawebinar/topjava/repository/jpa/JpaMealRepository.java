package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            if (get(meal.getId(), userId) == null)
                return null;
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id", id).setParameter("user_id", userId).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> list = em.createNamedQuery(Meal.GET, Meal.class).setParameter("id", id).setParameter("user_id", userId).getResultList();
        return DataAccessUtils.singleResult(list);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL, Meal.class).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.ALL_BETWEEN, Meal.class).setParameter("user_id", userId)
                .setParameter("start_date", startDate)
                .setParameter("end_date", endDate)
                .getResultList();
    }
}