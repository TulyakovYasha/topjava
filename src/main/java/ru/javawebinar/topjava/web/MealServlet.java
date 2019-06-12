package ru.javawebinar.topjava.web;


import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MapStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class MealServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        storage = new MapStorage();
        for (MealTo mealTo : MealsUtil.getFilteredWithExcess(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, 2000)) {
            storage.save(mealTo);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String time = request.getParameter("time");
        String description = request.getParameter("description");
        String caloriesParam = request.getParameter("calories");
        int calories;
        if(caloriesParam.equals("")){
            calories = 0;
        }else {
            calories = Integer.parseInt(request.getParameter("calories"));
        }
        boolean excess = Boolean.valueOf(request.getParameter("excess"));
        String id = request.getParameter("id");
        LocalDateTime localDateTime;
        if (time.equals("")) {
            localDateTime = storage.get(id).getDateTime();
        } else {
            localDateTime = LocalDateTime.parse(time);
        }
        MealTo mealTo = new MealTo(localDateTime, description, calories, excess);
        mealTo.setId(id);
        storage.update(mealTo);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        List<MealTo> mealsTo = storage.getAll();
        if (action == null) {
            request.setAttribute("meals", mealsTo);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }
        MealTo mealTo;
        switch (action) {
            case "delete":
                storage.delete(id);
                response.sendRedirect("meals");
                return;
            case "edit":
                mealTo = storage.get(id);
                break;
            default:
                throw new IllegalStateException();
        }
        request.setAttribute("mealTo", mealTo);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }
}
