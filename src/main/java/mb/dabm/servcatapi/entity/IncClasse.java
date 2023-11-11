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
@Table(name = "INC_CLASSE", schema = "FEDLOGDB")
@JsonIgnoreProperties(value = {"hibernate_lazy_initializer", "handler"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IncClasse implements Serializable {

    @Id
    @Column(name = "INC", columnDefinition = "char", length = 5)
    String inc;

    @Column(name = "CLASSE", columnDefinition = "char", length = 4)
    String classe;




}
