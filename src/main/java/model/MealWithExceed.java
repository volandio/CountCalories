package model;

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

    private int calories;

    private boolean exceed;

    private MealWithExceed() {
    }

    public MealWithExceed(User user, LocalDateTime dateTime, String description, int calories, boolean exceed) {
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
