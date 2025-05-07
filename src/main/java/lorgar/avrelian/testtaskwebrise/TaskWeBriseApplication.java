package lorgar.avrelian.testtaskwebrise;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Тестовое техническое задание",
                description = "Тестовое техническое задание от компании WEBrise (ООО \"Вебрайз\")",
                version = "0.1.0",
                contact = @Contact(
                        name = "Токовенко Виктор",
                        email = "victor-14-244@mail.ru",
                        url = "https://github.com/Lorgar-Avrelian"
                )
        )
)
public class TaskWeBriseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskWeBriseApplication.class, args);
    }

}
