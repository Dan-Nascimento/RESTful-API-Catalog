package mb.dabm.servcatapi.repository;

import mb.dabm.servcatapi.entity.H6;
import mb.dabm.servcatapi.model.projection.H6DTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface H6Repository
    extends JpaRepository<H6, Long> {

    @Query(value = """
        SELECT H6.INC,
                 FIIG,
                 STATUS,
                 ITEM_NAME,
                 N_NACIONAL,
                 IC.CLASSE
            FROM H6 H6, INC_CLASSE IC
           WHERE H6.INC = IC.INC(+) AND FIIG = :fiig
        GROUP BY H6.INC,
                 FIIG,
                 ITEM_NAME,
                 DEFINITION,
                 STATUS_INC,
                 N_NACIONAL,
                 STATUS,
                 APP_KEY,
                 CONDITION_CODE,
                 IC.CLASSE""",
        countQuery = "SELECT count(*) FROM H6 H6, INC_CLASSE IC WHERE H6.INC = IC.INC(+) AND FIIG = :fiig",
        nativeQuery = true)
    Page<H6DTO> getAllIncByFiig(String fiig, Pageable pageable);

    @Query(value = """
        SELECT * FROM H6""",
        countQuery = "SELECT COUNT(*) FROM H6",
        nativeQuery = true)
    Page<H6> getByAll(Pageable pageable);

    @Query(value = """
        SELECT * FROM H6 WHERE INC = :inc""",
        nativeQuery = true)
    H6 getByIncId(String inc);

    @Query(value = """
        SELECT * FROM H6 WHERE FIIG = :fiig""",
        nativeQuery = true)
    Page<H6> getByFiig(String fiig, Pageable pageable);

    @Query(value = "SELECT H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, \n" +
        "       CONDITION_CODE, IC.CLASSE, CLASSE_MODIF, N_NACIONAL, D_NACIONAL, STATUS, RELATED_INC\n" +
        "FROM H6 H6, INC_CLASSE IC\n" +
        "WHERE H6.INC = IC.INC (+)\n" +
        "AND H6.INC = :inc\n" +
        "GROUP BY H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, \n" +
        "      CONDITION_CODE, IC.CLASSE,CLASSE_MODIF, N_NACIONAL, D_NACIONAL, STATUS, \n" +
        "      RELATED_INC",
        nativeQuery = true)
    List<H6> getByIncTabelas(String inc);
}
