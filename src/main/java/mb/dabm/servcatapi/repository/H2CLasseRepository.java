package mb.dabm.servcatapi.repository;


import mb.dabm.servcatapi.entity.H2Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface H2CLasseRepository
    extends JpaRepository<H2Classe, Long> {

    @Query(value = "SELECT * FROM H2_CLASSE",
        nativeQuery = true)
    Page<H2Classe> getByAll(Pageable pageable);

    @Query(value = """
        SELECT *
        FROM H2_CLASSE
        WHERE CLASSE LIKE :classe
        """,
        nativeQuery = true)
    List<H2Classe> getByClasseContaining(String classe, Pageable pageable);

    @Query(value = """
        SELECT *
        FROM H2_CLASSE
        WHERE 1 = 1
        AND CLASSE = :classe""",
        nativeQuery = true)
    H2Classe getByClasseId(String classe);

    @Query(value = """
        SELECT *
        FROM H2_CLASSE
        WHERE GRUPO = :grupo""",
        nativeQuery = true)
    List<H2Classe> getByGrupoList(String grupo);

/*
@Query(value = "SELECT S.GRUPO\n" +
        "      ,S.CLASSE\n" +
        "      ,S.N_ESTRANGEIRO\n" +
        "      ,S.NOTA_ESTRANGEIRA\n" +
        "      ,S.INCL_ESTRANGEIRO\n" +
        "      ,S.EXCL_ESTRANGEIRO\n" +
        "      ,S.N_NACIONAL\n" +
        "      ,S.NOTA_NACIONAL\n" +
        "      ,S.STATUS\n" +
        "FROM H2_CLASSE S",
        nativeQuery = true)
 */

}
