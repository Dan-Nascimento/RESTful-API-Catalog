package mb.dabm.servcatapi.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EmpresasServcatDto(

    String cageCode,
    String cageStatus,
    String cageType,
    String cageCao,
    String cageAdp,
    String cageRplm,
    String cageAssoc,
    String cageAffil,
    String cageSize,
    String cagePrimaryBusiness,
    String cageTypeOfBusiness,
    String cageWomanOwned,
    String cageSicCodes,
    String cageFax,
    String cageCompanyName,
    String cageCompanyAddress,
    String cagePoBox,
    String cageCity,
    String cageState,
    String cageCountry,
    String cageZipCode,
    String cageTelephone,
    String cageFormerAddress

) {

}
