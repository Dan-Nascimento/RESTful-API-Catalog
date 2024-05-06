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
@Table(name = "CHARACTERISTICS", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Characteristics implements Serializable {


    @Id
    @Column(name = "COD_CHAR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHAR")
    @SequenceGenerator(name = "SEQCHAR", allocationSize = 1, sequenceName = "SEQCHAR")
    Long codChar;

    @Column(name = "COD_GEN")
    Long codGen;

    @Column(name = "CHAR_MRC", columnDefinition = "char", length = 4)
    String charMrc;

    @Column(name = "CHAR_CLEAR_TEXT_REPLY", length = 4000)
    String charClearTextReply;


}
