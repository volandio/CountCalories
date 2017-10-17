package Jaxb;

import model.MealWithExceed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MealsWithExceed")
@XmlAccessorType(XmlAccessType.FIELD)
public class MealsWithExceed {
    @XmlElement(name = "MealWithExceed")
    private List<MealWithExceed> mealsWithExceedList;

    public MealsWithExceed() {
    }

    public List<MealWithExceed> getMealsWithExceed() {
        return mealsWithExceedList;
    }

    public void setMealsWithExceed(List<MealWithExceed> mealsWithExceedList) {
        this.mealsWithExceedList = mealsWithExceedList;
    }
}
