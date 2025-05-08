package lorgar.avrelian.testtaskwebrise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Schema(title = "Данные", description = "Модель данных подписки пользователя")
public class SubscriptionDataDTO {
    @Schema(title = "Данные", description = "Учётные данные подписки")
    private Map<String, String> data;

    public SubscriptionDataDTO() {
    }

    public SubscriptionDataDTO(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDataDTO that = (SubscriptionDataDTO) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }

    @Override
    public String toString() {
        return "SubscriptionDataDTO{" +
                "data=" + data +
                '}';
    }
}
