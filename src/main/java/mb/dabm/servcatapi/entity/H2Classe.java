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
@Table(name = "H2_CLASSE", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class H2Classe implements Serializable {



    @Id
    @Column(name = "GRUPO", columnDefinition = "char", length = 2)
    String grupo;

    @Column(name = "CLASSE", columnDefinition = "char", length = 4)
    String classe;

    @Column(name = "N_ESTRANGEIRO", length = 150)
    String nEstrangeiro;

    @Column(name = "NOTA_ESTRANGEIRA", length = 2000)
    String notaEstrangeira;

    @Column(name = "INCL_ESTRANGEIRO", length = 1000)
    String inclEstrangeiro;

    @Column(name = "EXCL_ESTRANGEIRO", length = 1000)
    String exclEstrangeiro;

    @Column(name = "N_NACIONAL", length = 150)
    String nNacional;

    @Column(name = "NOTA_NACIONAL", length = 4000)
    String notaNacional;

    @Column(name = "STATUS", length = 2)
    String status;




}
