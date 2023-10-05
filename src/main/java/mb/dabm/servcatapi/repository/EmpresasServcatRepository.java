package mb.dabm.servcatapi.repository;


import mb.dabm.servcatapi.entity.EmpresasServcat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpresasServcatRepository
        extends JpaRepository<EmpresasServcat, Long> {

    @Query(value = "SELECT S.CAGE_CODE\n" +
        "      ,S.CAGE_STATUS\n" +
        "      ,S.CAGE_TYPE\n" +
        "      ,S.CAGE_CAO\n" +
        "      ,S.CAGE_ADP\n" +
        "      ,S.CAGE_RPLM\n" +
        "      ,S.CAGE_ASSOC\n" +
        "      ,S.CAGE_AFFIL\n" +
        "      ,S.CAGE_SIZE\n" +
        "      ,S.CAGE_PRIMARY_BUSINESS\n" +
        "      ,S.CAGE_TYPE_OF_BUSINESS\n" +
        "      ,S.CAGE_WOMAN_OWNED\n" +
        "      ,S.CAGE_SIC_CODES\n" +
        "      ,S.CAGE_FAX\n" +
        "      ,S.CAGE_COMPANY_NAME\n" +
        "      ,S.CAGE_COMPANY_ADDRESS\n" +
        "      ,S.CAGE_PO_BOX\n" +
        "      ,S.CAGE_CITY\n" +
        "      ,S.CAGE_STATE\n" +
        "      ,S.CAGE_COUNTRY\n" +
        "      ,S.CAGE_ZIP_CODE\n" +
        "      ,S.CAGE_TELEPHONE\n" +
        "      ,S.CAGE_FORMER_ADDRESS\n" +
        "FROM SUPPLIER S",
    nativeQuery = true)
    Page<EmpresasServcat> getByAll(Pageable pageable);



}
