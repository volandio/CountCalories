package util;

import model.Meal;
import model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealsUtil {
    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        for (Meal meal : meals) {
            if (caloriesSumByDate.get(meal.getDate()) == null) {
                caloriesSumByDate.put(meal.getDate(), meal.getCalories());
            } else {
                caloriesSumByDate.put(meal.getDate(), caloriesSumByDate.get(meal.getDate()) + meal.getCalories());
            }
        }

        List<MealWithExceed> mealsWithExceeded = new ArrayList<>();
        for (Meal meal : meals) {
            if (TimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
                mealsWithExceeded.add((createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay)));
            }
        }
        return mealsWithExceeded;
    }

    private static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getIdMeal(), meal.getUser(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
}