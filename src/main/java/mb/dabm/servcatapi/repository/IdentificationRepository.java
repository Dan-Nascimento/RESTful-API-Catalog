package mb.dabm.servcatapi.repository;

import mb.dabm.servcatapi.entity.Identification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IdentificationRepository
    extends JpaRepository<Identification, Long> {
    @Query(value = "SELECT /*+ FIRST_ROWS */ \n" +
        "COD_GEN, \n" +
        "FSC, \n" +
        "NIIN, \n" +
        "NSN, \n" +
        "ITEM_NAME, \n" +
        "INC, \n" +
        "TIIC, \n" +
        "RPDMRC, \n" +
        "FMSN, \n" +
        "MGMT_PMI, \n" +
        "MGMT_ADP, \n" +
        "MGMT_DML, \n" +
        "MGMT_ESDC, \n" +
        "MGMT_CC, \n" +
        "MGMT_HMIC, \n" +
        "ORIGEM \n" +
        "FROM GENERAL \n" +
        "WHERE 1 = 1 \n" +
        "AND NIIN LIKE '%' || :niin || '%' \n",
        nativeQuery = true)
    Page<Identification> getByNiinLike(String niin, Pageable pageable);


    @Query(value = "SELECT /*+ FIRST_ROWS */ \n" +
        "COD_GEN, \n" +
        "FSC, \n" +
        "NIIN, \n" +
        "NSN, \n" +
        "ITEM_NAME, \n" +
        "INC, \n" +
        "TIIC, \n" +
        "RPDMRC, \n" +
        "FMSN, \n" +
        "MGMT_PMI, \n" +
        "MGMT_ADP, \n" +
        "MGMT_DML, \n" +
        "MGMT_ESDC, \n" +
        "MGMT_CC, \n" +
        "MGMT_HMIC, \n" +
        "ORIGEM \n" +
        "FROM GENERAL \n" +
        "WHERE NIIN = :niin \n",
        nativeQuery = true)
    Identification getByNiinId(String niin);

    /**
     * Para esses casos sugere-se colocar outro atributo da anotação @Query, chamado
     * countQuery, conforme está mencionado abaixo, pois quando se tratar de milhões
     * de registros eu aconselho que seja feito diretamente, pois às vezes o spring
     * pode se perder na montagem da query
     */
    @Query(value = "SELECT /*+ FIRST_ROWS */ \n" +
        "COD_GEN, \n" +
        "FSC, \n" +
        "NIIN, \n" +
        "NSN, \n" +
        "ITEM_NAME, \n" +
        "INC, \n" +
        "TIIC, \n" +
        "RPDMRC, \n" +
        "FMSN, \n" +
        "MGMT_PMI, \n" +
        "MGMT_ADP, \n" +
        "MGMT_DML, \n" +
        "MGMT_ESDC, \n" +
        "MGMT_CC, \n" +
        "MGMT_HMIC, \n" +
        "ORIGEM \n" +
        "FROM GENERAL \n",
        countQuery = "SELECT /* FIRST_ROWS */ COUNT(*) FROM GENERAL",
        nativeQuery = true)
    Page<Identification> getByAllNiin(Pageable pageable);


    @Query(value = "SELECT /*+ FIRST_ROWS */ \n" +
        "COD_GEN, \n" +
        "FSC, \n" +
        "NIIN, \n" +
        "NSN, \n" +
        "ITEM_NAME, \n" +
        "INC, \n" +
        "TIIC, \n" +
        "RPDMRC, \n" +
        "FMSN, \n" +
        "MGMT_PMI, \n" +
        "MGMT_ADP, \n" +
        "MGMT_DML, \n" +
        "MGMT_ESDC, \n" +
        "MGMT_CC, \n" +
        "MGMT_HMIC, \n" +
        "ORIGEM \n" +
        "FROM GENERAL \n" +
        "WHERE FSC = :fsc \n",
        nativeQuery = true)
    Page<Identification> getByNiinFromFsc(String fsc, Pageable pageable);


    @Query(value = "SELECT /*+ FIRST_ROWS */ \n" +
        "COD_GEN, \n" +
        "FSC, \n" +
        "NIIN, \n" +
        "NSN, \n" +
        "ITEM_NAME, \n" +
        "INC, \n" +
        "TIIC, \n" +
        "RPDMRC, \n" +
        "FMSN, \n" +
        "MGMT_PMI, \n" +
        "MGMT_ADP, \n" +
        "MGMT_DML, \n" +
        "MGMT_ESDC, \n" +
        "MGMT_CC, \n" +
        "MGMT_HMIC, \n" +
        "ORIGEM \n" +
        "FROM GENERAL \n" +
        "WHERE INC = :inc \n",
        nativeQuery = true)
    Page<Identification> getByNiinFromInc(String inc, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO GENERAL " +
        "(COD_GEN, FSC, NIIN, NSN, ITEM_NAME, INC, TIIC, RPDMRC, FMSN, ORIGEM)" +
        " VALUES " +
        "( " +
        ":#{#id.codGen}, " +
        ":#{#id.fsc}, "+
        ":#{#id.niin}, "+
        ":#{#id.nsn}, "+
        ":#{#id.itemName}, "+
        ":#{#id.inc}, "+
        ":#{#id.tiic}, "+
        ":#{#id.rpdmrc}, "+
        ":#{#id.fmsn}, "+
        ":#{#id.origem} "+
        ")", nativeQuery = true)
    int insert(@Param("id") Identification id);

}
