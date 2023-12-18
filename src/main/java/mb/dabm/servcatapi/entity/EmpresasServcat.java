package mb.dabm.servcatapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SUPPLIER", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmpresasServcat implements Serializable {

    @Id
    @Column(name = "CAGE_CODE", columnDefinition = "char", length = 5)
    String cageCode;

    @Column(name = "CAGE_STATUS", columnDefinition = "char", length = 1)
    String cageStatus;

    @Column(name = "CAGE_TYPE", columnDefinition = "char", length = 1)
    String cageType;

    @Column(name = "CAGE_CAO", columnDefinition = "char", length = 6)
    String cageCao;

    @Column(name = "CAGE_ADP", columnDefinition = "char", length = 6)
    String cageAdp;

    @Column(name = "CAGE_RPLM", columnDefinition = "char", length = 5)
    String cageRplm;

    @Column(name = "CAGE_ASSOC", columnDefinition = "char", length = 5)
    String cageAssoc;

    @Column(name = "CAGE_AFFIL", columnDefinition = "char", length = 1)
    String cageAffil;

    @Column(name = "CAGE_SIZE", columnDefinition = "char", length = 1)
    String cageSize;

    @Column(name = "CAGE_PRIMARY_BUSINESS", columnDefinition = "char", length = 1)
    String cagePrimaryBusiness;

    @Column(name = "CAGE_TYPE_OF_BUSINESS", columnDefinition = "char", length = 1)
    String cageTypeOfBusiness;

    @Column(name = "CAGE_WOMAN_OWNED", columnDefinition = "char", length = 1)
    String cageWomanOwned;

    @Column(name = "CAGE_SIC_CODES", length = 40)
    String cageSicCodes;

    @Column(name = "CAGE_FAX", length = 40)
    String cageFax;

    @Column(name = "CAGE_COMPANY_NAME", length = 300)
    String cageCompanyName;

    @Column(name = "CAGE_COMPANY_ADDRESS", length = 300)
    String cageCompanyAddress;

    @Column(name = "CAGE_PO_BOX", length = 80)
    String cagePoBox;

    @Column(name = "CAGE_CITY", length = 200)
    String cageCity;

    @Column(name = "CAGE_STATE", length = 100)
    String cageState;

    @Column(name = "CAGE_COUNTRY", length = 200)
    String cageCountry;

    @Column(name = "CAGE_ZIP_CODE", length = 30)
    String cageZipCode;

    @Column(name = "CAGE_TELEPHONE", length = 30)
    String cageTelephone;

    @Column(name = "CAGE_FORMER_ADDRESS", length = 200)
    String cageFormerAddress;




}
