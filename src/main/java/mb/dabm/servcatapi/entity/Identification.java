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

    @Column(name = "FSC", length = 4)
    String fsc;

    @Column(name = "NIIN", length = 17)
    String niin;

    @Column(name = "NSN", length = 13)
    String nsn;

    @Column(name = "ITEM_NAME", length = 128)
    String itemName;

    @Column(name = "INC", length = 5)
    String inc;

    @Column(name = "TIIC", length = 2)
    String tiic;

    @Column(name = "RPDMRC", length = 1)
    String rpdmrc;

    @Column(name = "FMSN", length = 3)
    String fmsn;

    @Column(name = "MGMT_PMI", length = 1)
    String mgmtPmi;

    @Column(name = "MGMT_ADP", length = 1)
    String mgmtAdp;

    @Column(name = "MGMT_DML", length = 1)
    String mgmtDml;

    @Column(name = "MGMT_ESDC", length = 1)
    String mgmtEsdc;

    @Column(name = "MGMT_CC", length = 1)
    String mgmtCc;

    @Column(name = "MGMT_HMIC", length = 1)
    String mgmtHmic;

    @Column(name = "ORIGEM", length = 1)
    String origem;
}
