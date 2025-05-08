package lorgar.avrelian.testtaskwebrise.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Пользователь", description = "Модель пользователя")
@Entity
@Table(name = "users")
public class User {
    @Schema(title = "ID", description = "ID пользователя", defaultValue = "1", required = true, minimum = "1", maximum = "9223372036854775807")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @Schema(title = "Логин", description = "Логин пользователя", defaultValue = "Логин", required = true, minLength = 2, maxLength = 30)
    @Column(name = "login", nullable = false, length = 30)
    private String login;
    @Schema(title = "Имя", description = "Имя пользователя", defaultValue = "Имя", required = true, minLength = 2, maxLength = 30)
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Schema(title = "Фамилия", description = "Фамилия пользователя", defaultValue = "Фамилия", required = true, minLength = 2, maxLength = 30)
    @Column(name = "surname", nullable = false, length = 30)
    private String surname;
    @Schema(title = "Подписки", description = "Подписки пользователя")
    @ManyToMany(mappedBy = "users")
    private List<Subscription> subscriptions;

    public User() {
    }

    public User(Long id, String login, String name, String surname, List<Subscription> subscriptions) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.subscriptions = subscriptions;
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

    @JsonManagedReference
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(subscriptions, user.subscriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, surname, subscriptions);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
