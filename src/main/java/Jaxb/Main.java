package Jaxb;

import model.Meal;
import model.MealWithExceed;
import model.User;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static Jaxb.JaxbUtil.marshalingExample;
import static Jaxb.JaxbUtil.unMarshalingExample;
import static model.User.DEFAULT_CALORIES_PER_DAY;
import static util.MealsUtil.getFilteredWithExceeded;

public class Main {
    public static void main(String[] args) throws JAXBException {
        User user1 = new User("Peter", "abc@mail.ru", "12345");

        List<User> usersList = Arrays.asList(
            user1,
            new User("Ivan", "xyz@mail.ru", "67890")
        );

        List<Meal> mealsList = Arrays.asList(
            new Meal(user1, LocalDateTime.of(2017, Month.SEPTEMBER, 29, 10, 0), "Завтрак", 500),
            new Meal(user1, LocalDateTime.of(2017, Month.SEPTEMBER, 29, 13, 0), "Обед", 1000),
            new Meal(user1, LocalDateTime.of(2017, Month.SEPTEMBER, 29, 20, 0), "Ужин", 500)
        );

        Meals meals = new Meals();
        meals.setMealsList(mealsList);
        marshalingExample(meals, "Meals");
        List<Meal> mealsListBack = ((Meals) unMarshalingExample("Meals", "Jaxb.Meals")).getMealsList();
        for (Meal meal : mealsListBack) {
            System.out.println(meal);
        }

        System.out.println("_____________________________________________________");

        Users users = new Users();
        users.setUserslsList(usersList);
        marshalingExample(users, "Users");
        List<User> usersListBack = ((Users) unMarshalingExample("Users", "Jaxb.Users")).getUsersList();
        for (User user : usersListBack) {
            System.out.println(user);
        }

        System.out.println("_____________________________________________________");

        MealsWithExceed mealsWithExceed = new MealsWithExceed();
        List<MealWithExceed> mealsWithExceedList = getFilteredWithExceeded(mealsList, LocalTime.of(0, 0),
            LocalTime.of(23, 59), DEFAULT_CALORIES_PER_DAY);
        mealsWithExceed.setMealsWithExceed(mealsWithExceedList);
        marshalingExample(mealsWithExceed, "MealsWithExceed");
        List<MealWithExceed> mealsWithExceedListBack = ((MealsWithExceed) unMarshalingExample("MealsWithExceed",
            "Jaxb.MealsWithExceed")).getMealsWithExceed();
        for (MealWithExceed mealWithExceed : mealsWithExceedListBack) {
            System.out.println(mealWithExceed);
        }
    }


}
