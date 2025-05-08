package lorgar.avrelian.testtaskwebrise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Новая подписка", description = "Модель нового варианта подписки")
public class NewSubscriptionDTO {
    @Schema(title = "Название", description = "Название нового варианта подписки", defaultValue = "Название", required = true, minLength = 3, maxLength = 30)
    private String title;
    @Schema(title = "Описание", description = "Описание нового варианта подписки", defaultValue = "Описание")
    private String description;
    @Schema(title = "Тариф", description = "Тарифный план нового варианта подписки", defaultValue = "Тариф", required = true, minLength = 3, maxLength = 30)
    private String tariff;

    public NewSubscriptionDTO() {
    }

    public NewSubscriptionDTO(String title, String description, String tariff) {
        this.title = title;
        this.description = description;
        this.tariff = tariff;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NewSubscriptionDTO that = (NewSubscriptionDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(tariff, that.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, tariff);
    }

    @Override
    public String toString() {
        return "NewSubscriptionDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tariff='" + tariff + '\'' +
                '}';
    }
}
