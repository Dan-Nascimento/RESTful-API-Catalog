package mb.dabm.servcatapi.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.SortedMap;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ApiInfoDto(
    String apiName,
    String apiStatus,
    String apiVersion,
    String apiDescription,
    String apiEncoding,
    String apiJavaVersion,
    String apiSpringVersion,
    String apiAmbienteProfile,
    String apiBuildDate,
    String apiBuildGroup,
    String[] Mantenedores,
    String[] Equipe
) {
}
