package mb.dabm.servcatapi.repository;


import mb.dabm.servcatapi.entity.Characteristics;
import mb.dabm.servcatapi.entity.Management;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacteristicsRepository
    extends JpaRepository<Characteristics, Long> {


    @Query(value = "SELECT S.COD_GEN\n" +
        "      ,S.CHAR_MRC\n" +
        "      ,S.COD_CHAR\n" +
        "      ,S.CHAR_CLEAR_TEXT_REPLY\n" +
        "FROM CHARACTERISTICS S",
        nativeQuery = true)
    Page<Characteristics> getByAll(Pageable pageable);


    @Query(value = """
        SELECT *
        FROM CHARACTERISTICS
        WHERE COD_GEN = :codGen""",
        nativeQuery = true)
    List<Characteristics> getByCodGen (String codGen);

}
