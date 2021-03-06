package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

//@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    private int userId;

    private boolean enabled = true;

    private boolean admin = false;

    private String name;

    private String email;

    private String password;

    private Date registered = new Date();

    private int caloriesPerDay;

    private User() {
    }

    public User(String name, String email, String password) {
        this(name, email, password, DEFAULT_CALORIES_PER_DAY);
    }

    public User(String name, String email, String password, int caloriesPerDay) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
    }

    public User(boolean enabled, String name, String email, String password, Date registered, int caloriesPerDay) {
        this.enabled = enabled;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.caloriesPerDay = caloriesPerDay;
    }

    public User(int userId, boolean enabled, String name, String email, String password, Date registered, int caloriesPerDay) {
        this.userId = userId;
        this.enabled = enabled;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.caloriesPerDay = caloriesPerDay;
    }

    public User(int id, boolean enabled, String name, String email, String password, Date registered,
                int calories_per_day, boolean admin) {
        this.userId = id;
        this.enabled = enabled;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.caloriesPerDay = calories_per_day;
        this.admin = admin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", enabled=" + enabled +
            ", getAdmin=" + admin +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", registered=" + registered +
            ", caloriesPerDay=" + caloriesPerDay +
            '}';
    }
}