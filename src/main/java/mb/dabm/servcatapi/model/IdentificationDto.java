package mb.dabm.servcatapi.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record IdentificationDto(
    Long codGen,
    String fsc,
    String niin,
    String nsn,
    String itemName,
    String inc,
    String tiic,
    String rpdmrc,
    String fmsn,
    String origem
) {
}
