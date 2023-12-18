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
@Table(name = "H6", schema = "FEDLOGDB")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(value = {"hibernate_lazy_initializer", "handler"})
public class H6 implements Serializable {

    @Id
    @Column(name = "INC", columnDefinition = "char", length = 5)
    String inc;

    @Column(name = "APP_KEY", length = 3)
    String appKey;

    @Column(name = "CONDITION_CODE", length = 1)
    String conditionCode;

    @Column(name = "FIIG", length = 6)
    String fiig;

    @Column(name = "CLASSE_MODIF", length = 100)
    String classeModif;

    @Column(name = "DEFINITION", length = 2000)
    String definition;

    @Column(name = "STATUS_INC", columnDefinition = "char", length = 1)
    String statusInc;

    @Column(name = "NOME_APROV", columnDefinition = "char", length = 1)
    String nomeAprov;

    @Column(name = "N_NACIONAL", length = 255)
    String nNacional;

    @Column(name = "D_NACIONAL", length = 2000)
    String dNacional;

    @Column(name = "STATUS", length = 2)
    String status;

    @Column(name = "RELATED_INC", length = 2000)
    String relatedInc;

    @Column(name = "ITEM_NAME", length = 200)
    String itemName;

}
