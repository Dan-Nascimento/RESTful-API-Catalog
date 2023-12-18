package mb.dabm.servcatapi.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record H2GrupoDto(

    String grupo,
    String nEstrangeiro,
    String notaEstrangeira,
    String nNacional,
    String notaNacional,
    String status


) {
}
