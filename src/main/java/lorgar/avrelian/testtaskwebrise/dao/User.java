package lorgar.avrelian.testtaskwebrise.dao;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(name = "login", nullable = false, length = 30)
    private String login;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "surname", nullable = false, length = 30)
    private String surname;
    @OneToMany(mappedBy = "user")
    private Collection<SubscriptionData> subscriptionData;

    public User() {
    }

    public User(Long id, String login, String name, String surname, Collection<SubscriptionData> subscriptionData) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.subscriptionData = subscriptionData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Collection<SubscriptionData> getSubscriptionData() {
        return subscriptionData;
    }

    public void setSubscriptionData(Collection<SubscriptionData> subscriptionData) {
        this.subscriptionData = subscriptionData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(subscriptionData, user.subscriptionData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, surname, subscriptionData);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", subscriptionData=" + subscriptionData +
                '}';
    }
}
