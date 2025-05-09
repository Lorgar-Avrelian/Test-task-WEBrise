package lorgar.avrelian.testtaskwebrise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Пользователь (с подписками)", description = "Модель зарегистрированного пользователя с подписками")
public class UserDTO {
    @Schema(title = "ID", description = "ID зарегистрированного пользователя", defaultValue = "1", required = true, minimum = "1", maximum = "9223372036854775807")
    private Long id;
    @Schema(title = "Логин", description = "Логин зарегистрированного пользователя", defaultValue = "Логин", required = true, minLength = 3, maxLength = 30)
    private String login;
    @Schema(title = "Имя", description = "Имя зарегистрированного пользователя", defaultValue = "Имя", required = true, minLength = 3, maxLength = 30)
    private String name;
    @Schema(title = "Фамилия", description = "Фамилия зарегистрированного пользователя", defaultValue = "Фамилия", required = true, minLength = 3, maxLength = 30)
    private String surname;
    @Schema(title = "Подписки", description = "Подписки пользователя")
    private Collection<SubscriptionDTO> subscriptions;

    public UserDTO() {
    }

    public UserDTO(Long id, String login, String name, String surname, Collection<SubscriptionDTO> subscriptions) {
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

    public Collection<SubscriptionDTO> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Collection<SubscriptionDTO> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(login, userDTO.login) && Objects.equals(name, userDTO.name) && Objects.equals(surname, userDTO.surname) && Objects.equals(subscriptions, userDTO.subscriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, surname, subscriptions);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
