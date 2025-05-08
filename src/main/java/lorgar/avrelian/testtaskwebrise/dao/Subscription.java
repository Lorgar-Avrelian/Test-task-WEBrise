package lorgar.avrelian.testtaskwebrise.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Подписка", description = "Модель подписки")
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Schema(title = "ID", description = "ID подписки", defaultValue = "1", required = true, minimum = "1", maximum = "9223372036854775807")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @Schema(title = "Название", description = "Название подписки", defaultValue = "Название", required = true, minLength = 2, maxLength = 30)
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Schema(title = "Описание", description = "Описание подписки", defaultValue = "Описание")
    @Column(name = "description")
    private String description;
    @Schema(title = "Тариф", description = "Тарифный план", defaultValue = "Тарифный план", minLength = 2, maxLength = 30)
    @Column(name = "tariff", nullable = false, length = 30)
    private String tariff;
    @Schema(title = "Пользователи", description = "Пользователи подписки", defaultValue = "null")
    @ManyToMany
    @JoinTable(name = "users_subscriptions", joinColumns = @JoinColumn(name = "subscriptions_id"), inverseJoinColumns = @JoinColumn(name = "users_id"))
    private List<User> users;

    public Subscription() {
    }

    public Subscription(Long id, String title, String description, String tariff, List<User> users) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tariff = tariff;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    @JsonBackReference
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(tariff, that.tariff) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tariff, users);
    }

}
