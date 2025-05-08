package lorgar.avrelian.testtaskwebrise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Подписка (с пользователями)", description = "Модель возможной подписки с пользователями")
public class SubscriptionDTO {
    @Schema(title = "ID", description = "ID возможной подписки", defaultValue = "1", required = true, minimum = "1", maximum = "9223372036854775807")
    private Long id;
    @Schema(title = "Название", description = "Название возможной подписки", defaultValue = "Название", required = true, minLength = 3, maxLength = 30)
    private String title;
    @Schema(title = "Описание", description = "Описание возможной подписки", defaultValue = "Описание")
    private String description;
    @Schema(title = "Тариф", description = "Тарифный план возможной подписки", defaultValue = "Тариф", required = true, minLength = 3, maxLength = 30)
    private String tariff;
    @Schema(title = "Пользователи", description = "Пользователи данной подписки")
    private Collection<UserNoSubscriptions> users;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Long id, String title, String description, String tariff, Collection<UserNoSubscriptions> users) {
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

    public Collection<UserNoSubscriptions> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserNoSubscriptions> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDTO that = (SubscriptionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(tariff, that.tariff) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tariff, users);
    }

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tariff='" + tariff + '\'' +
                ", users=" + users +
                '}';
    }
}
