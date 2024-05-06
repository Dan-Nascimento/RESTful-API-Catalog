package mb.dabm.servcatapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "REFERENCE_NUMBER", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReferenceNumber implements Serializable {



    @Id
    @Column(name = "COD_REF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQREF")
    @SequenceGenerator(name = "SEQREF", allocationSize = 1, sequenceName = "SEQREF")
    Long codRef;

    @Column(name = "COD_GEN")
    Long codGen;

    @Column(name = "CAGE_CODE", columnDefinition = "char", length = 5)
    String cageCode;

    @Column(name = "REF_RNFC", columnDefinition = "char", length = 1)
    String refRnfc;

    @Column(name = "REF_RNVC", columnDefinition = "char", length = 1)
    String refRnvc;

    @Column(name = "REF_RNCC", columnDefinition = "char", length = 1)
    String refRncc;

    @Column(name = "REF_RNSC", columnDefinition = "char", length = 1)
    String refRnsc;

    @Column(name = "REF_RNJC", columnDefinition = "char", length = 1)
    String refRnjc;

    @Column(name = "REF_DAC", columnDefinition = "char", length = 1)
    String refDac;

    @Column(name = "REF_ISC", columnDefinition = "char", length = 1)
    String refIsc;

    @Column(name = "REF_RNAAC", columnDefinition = "char", length = 1)
    String refRnaac;

    @Column(name = "REF_NUM", columnDefinition = "char", length = 2)
    String refNum;

    @Column(name = "REF_NUM_NAOFOR")
    String refNumNaofor;

    @Column(name = "ORIGEM", columnDefinition = "char", length = 32)
    String origem;

    @Column(name = "REF_MSDS")
    String refMsds;

    @Column(name = "REF_SADC")
    String refSadc;


}
