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
@Table(name = "CHARACTERISTICS", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = { "hibernate_lazy_initializer", "handler" })
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Characteristics implements Serializable {


    @Id
    @Column(name = "COD_GEN")
    Long codGen;

    @Column(name = "CHAR_MRC", columnDefinition = "char", length = 4)
    String charMrc;

    @Column(name = "COD_CHAR")
    Long codChar;

    @Column(name = "CHAR_CLEAR_TEXT_REPLY", length = 4000)
    String charClearTextReply;


}
