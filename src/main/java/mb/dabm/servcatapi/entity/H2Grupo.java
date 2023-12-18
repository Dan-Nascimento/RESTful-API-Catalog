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

@Data
@NoArgsConstructor
@Entity
@Table(name = "H2_GRUPO", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class H2Grupo {


    @Id
    @Column(name = "GRUPO", columnDefinition = "char", length = 2)
    String grupo;

    @Column(name = "N_ESTRANGEIRO", length = 255)
    String nEstrangeiro;

    @Column(name = "NOTA_ESTRANGEIRA", length = 2000)
    String notaEstrangeira;

    @Column(name = "N_NACIONAL", length = 255)
    String nNacional;

    @Column(name = "NOTA_NACIONAL", length = 2000)
    String notaNacional;

    @Column(name = "STATUS", length = 2)
    String status;



}
