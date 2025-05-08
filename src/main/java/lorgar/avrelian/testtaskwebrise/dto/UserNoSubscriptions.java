package lorgar.avrelian.testtaskwebrise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Пользователь (без подписок)", description = "Модель зарегистрированного пользователя без подписок")
public class UserNoSubscriptions {
    @Schema(title = "ID", description = "ID зарегистрированного пользователя", defaultValue = "1", required = true, minimum = "1", maximum = "9223372036854775807")
    private Long id;
    @Schema(title = "Логин", description = "Логин зарегистрированного пользователя", defaultValue = "Логин", required = true, minLength = 3, maxLength = 30)
    private String login;
    @Schema(title = "Имя", description = "Имя зарегистрированного пользователя", defaultValue = "Имя", required = true, minLength = 3, maxLength = 30)
    private String name;
    @Schema(title = "Фамилия", description = "Фамилия зарегистрированного пользователя", defaultValue = "Фамилия", required = true, minLength = 3, maxLength = 30)
    private String surname;

    public UserNoSubscriptions() {
    }

    public UserNoSubscriptions(Long id, String login, String name, String surname) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserNoSubscriptions that = (UserNoSubscriptions) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, surname);
    }

    @Override
    public String toString() {
        return "UpdateUserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
