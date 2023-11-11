package mb.dabm.servcatapi.repository;


import mb.dabm.servcatapi.entity.Management;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagementRepository
    extends JpaRepository<Management, Long> {




    @Query(value = "SELECT S.COD_MAN\n" +
        "      ,S.COD_GEN\n" +
        "      ,S.MGMT_SA\n" +
        "      ,S.MGMT_SOS\n" +
        "      ,S.MGMT_AAC\n" +
        "      ,S.MGMT_QUP\n" +
        "      ,S.MGMT_UI\n" +
        "      ,S.MGMT_SLC\n" +
        "      ,S.MGMT_CIIC\n" +
        "      ,S.MGMT_RC\n" +
        "      ,S.MGMT_PC\n" +
        "      ,S.MGMT_UNITPRICE\n" +
        "      ,S.MGMT_MGMTCTRL\n" +
        "      ,S.MGMT_PHRASESTMT\n" +
        "      ,S.MGMT_OOU\n" +
        "      ,S.MGMT_JTC\n" +
        "      ,S.MGMT_SLA\n" +
        "      ,S.MGMT_SCHEDULE_B\n" +
        "FROM MANAGEMENT S",
        nativeQuery = true)
    Page<Management> getByAll(Pageable pageable);


    @Query(value = """
        SELECT *
        FROM MANAGEMENT
        WHERE COD_GEN = :codGen""",
        nativeQuery = true)
    List<Management> getByCodGen (String codGen);



}
