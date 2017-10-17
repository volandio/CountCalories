package Jaxb;

import model.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {
    @XmlElement(name = "User")
    private List<User> usersList;

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUserslsList(List<User> usersList) {
        this.usersList = usersList;
    }
}
