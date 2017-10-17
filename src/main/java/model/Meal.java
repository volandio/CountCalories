package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//@XmlRootElement(name = "Meal")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meal {
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime dateTime;

    private int mealId;

    private String description;

    private int calories;

    public int getIdMeal() {
        return mealId;
    }

    public void setIdMeal(int mealId) {
        this.mealId = mealId;
    }

    private User user;

    private Meal() {
    }

    public Meal(int mealId, User user, LocalDateTime dateTime, String description, int calories) {
        this.mealId = mealId;
        this.user = user;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(User user, LocalDateTime dateTime, String description, int calories) {
        this.user = user;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
            "dateTime=" + dateTime +
            ", description='" + description + '\'' +
            ", calories=" + calories +
            ", userId=" + user.getUserId() +
            '}';
    }
}
