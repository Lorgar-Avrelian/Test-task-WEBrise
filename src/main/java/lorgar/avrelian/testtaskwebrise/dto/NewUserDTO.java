package lorgar.avrelian.testtaskwebrise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Новый пользователь", description = "Модель нового пользователя")
public class NewUserDTO {
    @Schema(title = "Логин", description = "Логин нового пользователя", defaultValue = "Логин", required = true, minLength = 3, maxLength = 30)
    private String login;
    @Schema(title = "Имя", description = "Имя нового пользователя", defaultValue = "Имя", required = true, minLength = 3, maxLength = 30)
    private String name;
    @Schema(title = "Фамилия", description = "Фамилия нового пользователя", defaultValue = "Фамилия", required = true, minLength = 3, maxLength = 30)
    private String surname;

    public NewUserDTO() {
    }

    public NewUserDTO(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
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
        NewUserDTO that = (NewUserDTO) o;
        return Objects.equals(login, that.login) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, surname);
    }

    @Override
    public String toString() {
        return "NewUserDTO{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
