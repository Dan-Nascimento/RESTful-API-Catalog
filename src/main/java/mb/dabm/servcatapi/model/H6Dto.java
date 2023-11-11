package mb.dabm.servcatapi.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record H6Dto(

    String inc,
    String appKey,
    String conditionCode,
    String fiig,
    String classeModif,
    String definition,
    String statusInc,
    String nomeAprov,
    String nNacional,
    String dNacional,
    String status,
    String relatedInc,
    String itemName


) {
}
