package Jaxb;

import model.Meal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Meals")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meals {
    @XmlElement(name = "Meal")
    private List<Meal> mealsList;

    public Meals() {
    }

    public List<Meal> getMealsList() {
        return mealsList;
    }

    public void setMealsList(List<Meal> mealsList) {
        this.mealsList = mealsList;
    }
}
