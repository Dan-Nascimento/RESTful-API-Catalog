package mb.dabm.servcatapi.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record H2ClasseDto(


    String grupo,
    String classe,
    String nEstrangeiro,
    String notaEstrangeira,
    String inclEstrangeiro,
    String exclEstrangeiro,
    String nNacional,
    String notaNacional,
    String status


) {
}
