package mb.dabm.servcatapi.model.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface H6DTO {

    String getInc();

    String getFiig();

    String getStatus();

    String getItemName();

    String getNNacional();

    String getClasse();
}
