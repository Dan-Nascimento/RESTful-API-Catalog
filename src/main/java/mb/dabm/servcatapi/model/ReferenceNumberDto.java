package mb.dabm.servcatapi.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReferenceNumberDto(

    Long codRef,
    Long codGen,
    String cageCode,
    String refRnfc,
    String refRnvc,
    String refRncc,
    String refRnsc,
    String refRnjc,
    String refDac,
    String refIsc,
    String refRnaac,
    String refNum,
    String refNumNaofor,
    String origem,
    String refMsds,
    String refSadc

) {
}
