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
@Table(name = "MANAGEMENT", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Management implements Serializable {


    @Id
    @Column(name = "COD_MAN")
    Long codMan;

    @Column(name = "COD_GEN")
    Long codGen;

    @Column(name = "MGMT_SA", columnDefinition = "char", length = 2)
    String mgmtSa;

    @Column(name = "MGMT_SOS", columnDefinition = "char", length = 3)
    String mgmtSos;

    @Column(name = "MGMT_AAC", columnDefinition = "char", length = 1)
    String mgmtAac;

    @Column(name = "MGMT_QUP", columnDefinition = "char", length = 1)
    String mgmtQup;

    @Column(name = "MGMT_UI", columnDefinition = "char", length = 2)
    String mgmtUi;

    @Column(name = "MGMT_SLC", columnDefinition = "char", length = 1)
    String mgmtSlc;

    @Column(name = "MGMT_CIIC", columnDefinition = "char", length = 1)
    String mgmtCiic;

    @Column(name = "MGMT_RC", columnDefinition = "char", length = 1)
    String mgmtRc;

    @Column(name = "MGMT_PC", columnDefinition = "char", length = 1)
    String mgmtPc;

    @Column(name = "MGMT_UNITPRICE", length = 100)
    String mgmtUnitprice;

    @Column(name = "MGMT_MGMTCTRL", length = 7)
    String mgmtMgmtctrl;

    @Column(name = "MGMT_PHRASESTMT", length = 100)
    String mgmtPhrasestmt;

    @Column(name = "MGMT_OOU", length = 3)
    String mgmtOou;

    @Column(name = "MGMT_JTC", length = 3)
    String mgmtJtc;

    @Column(name = "MGMT_SLA", length = 2)
    String mgmtSla;

    @Column(name = "MGMT_SCHEDULE_B", length = 15)
    String mgmtScheduleB;





}
