package mb.dabm.servcatapi.repository;


import mb.dabm.servcatapi.entity.Characteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacteristicsRepository
    extends JpaRepository<Characteristics, Long>, CustomCharacteristicsRepository {

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
    List<Characteristics> getByCodGen(String codGen);

/*
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CHARACTERISTICS " +
        "(COD_GEN, CHAR_MRC, COD_CHAR, CHAR_CLEAR_TEXT_REPLY)" +
        " VALUES " +
        "( " +
        ":#{#id.codGen}, " +
        ":#{#id.charMrc}, "+
        ":#{#id.codChar}, "+
        ":#{#id.charClearTextReply}" +
        ")", nativeQuery = true)
    int insert(@Param("id") Characteristics id);


 */

    /*
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CHARACTERISTICS " +
        "(COD_GEN, CHAR_MRC, COD_CHAR, CHAR_CLEAR_TEXT_REPLY)" +
        " VALUES " +
        "( " +
        ":#{#id.codGen}, " +
        ":#{#id.charMrc}, "+
        ":#{#id.codChar}, "+
        ":#{#id.charClearTextReply}" +
        ")", nativeQuery = true)
    int insert(@Param("id") Characteristics id);

     */

}
