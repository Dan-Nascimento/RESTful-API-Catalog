package mb.dabm.servcatapi.repository;


import mb.dabm.servcatapi.entity.ReferenceNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReferenceNumberRepository
    extends JpaRepository<ReferenceNumber, Long> {



    @Query(value = "SELECT S.COD_REF\n" +
        "      ,S.COD_GEN\n" +
        "      ,S.CAGE_CODE\n" +
        "      ,S.REF_RNFC\n" +
        "      ,S.REF_RNVC\n" +
        "      ,S.REF_RNCC\n" +
        "      ,S.REF_RNSC\n" +
        "      ,S.REF_DAC\n" +
        "      ,S.REF_RNJC\n" +
        "      ,S.REF_ISC\n" +
        "      ,S.REF_RNAAC\n" +
        "      ,S.REF_NUM\n" +
        "      ,S.REF_NUM_NAOFOR\n" +
        "      ,S.ORIGEM\n" +
        "      ,S.REF_MSDS\n" +
        "      ,S.REF_SADC\n" +
        "FROM REFERENCE_NUMBER S",
        nativeQuery = true)
    Page<ReferenceNumber> getByAllReferenceNumber (Pageable pageable);


}
