package mb.dabm.servcatapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "GENERAL", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Identification implements Serializable {

    @Id
    @Column(name = "COD_GEN")
    Long codGen;

    @Column(name = "FSC", columnDefinition = "char", length = 4)
    String fsc;

    @Column(name = "NIIN", length = 17)
    String niin;

    @Column(name = "NSN", columnDefinition = "char", length = 13)
    String nsn;

    @Column(name = "ITEM_NAME", length = 128)
    String itemName;

    @Column(name = "INC", columnDefinition = "char", length = 5)
    String inc;

    @Column(name = "TIIC", columnDefinition = "char", length = 2)
    String tiic;

    @Column(name = "RPDMRC", columnDefinition = "char", length = 1)
    String rpdmrc;

    @Column(name = "FMSN", columnDefinition = "char", length = 3)
    String fmsn;

    @Column(name = "MGMT_PMI", columnDefinition = "char", length = 1)
    String mgmtPmi;

    @Column(name = "MGMT_ADP", columnDefinition = "char", length = 1)
    String mgmtAdp;

    @Column(name = "MGMT_DML", columnDefinition = "char", length = 1)
    String mgmtDml;

    @Column(name = "MGMT_ESDC", columnDefinition = "char", length = 1)
    String mgmtEsdc;

    @Column(name = "MGMT_CC", columnDefinition = "char", length = 1)
    String mgmtCc;

    @Column(name = "MGMT_HMIC", columnDefinition = "char", length = 1)
    String mgmtHmic;

    @Column(name = "ORIGEM", columnDefinition = "char", length = 1)
    String origem;
}
