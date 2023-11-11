package mb.dabm.servcatapi.repository;


import mb.dabm.servcatapi.entity.H2Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface H2GrupoRepository
        extends JpaRepository<H2Grupo, Long> {




    @Query(value = """
        SELECT * FROM H2_GRUPO""",
    nativeQuery = true)
    Page<H2Grupo> getByAll(Pageable pageable);



    @Query(value = """
    SELECT *
    FROM H2_GRUPO
    WHERE GRUPO = :grupo""",
    nativeQuery = true)
    H2Grupo getByGrupoId(String grupo);


    @Query(value = """
    SELECT *
    FROM H2_GRUPO
    WHERE GRUPO LIKE :grupo
    """,
    nativeQuery = true)
    List<H2Grupo> getByGrupoLike (String grupo);


}
