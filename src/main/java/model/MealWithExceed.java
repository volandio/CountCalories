package model;

import Jaxb.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class MealWithExceed {

    private User user;

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime dateTime;

    private String description;

    private int mealId;

    private int calories;

    private boolean exceed;

    private MealWithExceed() {
    }

    public int getMealId() {
        return mealId;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public MealWithExceed(int mealId, User user, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.mealId = mealId;
        this.user = user;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "MealsWithExceed{" +
            "userId=" + user.getUserId() +
            ", dateTime=" + dateTime +
            ", description='" + description + '\'' +
            ", calories=" + calories +
            ", exceed=" + exceed +
            '}';
    }
}
