package no.hvl.dat109.expoproject.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(schema = "expo")
public class User {

    @Id
    private String username;
    private String phone;
    private String email;
    private String hash;
    private String salt;
    @OneToMany(mappedBy = "user")
    private List<UserEvent> userEvents;

    public User(String username, String phone, String email, String hash, String salt, List<UserEvent> userEvents) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.userEvents = userEvents;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                ", userEvents=" + userEvents +
                '}';
    }
}
