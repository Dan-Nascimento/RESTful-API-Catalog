package mb.dabm.servcatapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Servcat-API", version = "1", description = "API desenvolvida para realizar buscas no banco de dados FEDLOGDB"))
public class ServcatApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServcatApiApplication.class, args);
	}

}
