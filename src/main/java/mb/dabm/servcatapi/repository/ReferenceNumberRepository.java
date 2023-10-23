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
    Page<ReferenceNumber> getByAllReferenceNumber(Pageable pageable);


    @Query(value = """
        SELECT *
        FROM REFERENCE_NUMBER
        WHERE 1 = 1
        AND COD_REF = :codRef""",
        nativeQuery = true)
    ReferenceNumber getByCodRefId(String codRef);




    @Query(value = """
        SELECT *
        FROM REFERENCE_NUMBER
        WHERE 1 = 1
        AND COD_GEN = (SELECT COD_GEN FROM GENERAL WHERE NIIN = :niin)
        """,
    nativeQuery = true)
    Page<ReferenceNumber> getByReferenceNiin (String niin, Pageable pageable);





    @Query(value = """
        SELECT *
        "FROM REFERENCE_NUMBER"
        "WHERE 1 = 1"
        "AND COD_GEN = (SELECT COD_GEN FROM GENERAL WHERE NIIN = :niin) \n" +
        "AND REF_NUM_NAOFOR LIKE :refNumNaoFor"
        """,
    nativeQuery = true)
    Page<ReferenceNumber> getByNiinAndNumRef (String niin, String refNumNaofor, Pageable pageable);


}





/*
-- ENDPOINT 1:

        -- QUERY RETORNA UM UNICO REGISTRO COM A BUSCA POR COD_REF
-- exemplo: http://localhost:8080/reference/{cod_ref}
        -- exemplo: http://localhost:8080/reference/30620926
    SELECT *
    FROM REFERENCE_NUMBER
    WHERE 1 = 1
    AND COD_REF = 30620926
        ;


-- ENDPOINT 2:
        -- QUERY RETORNA UMA LISTA paginada DE REFERENCIAS COM A BUSCA POR NIIN
-- exemplo: http://localhost:8080/reference/niin/{niin}
        -- exemplo: http://localhost:8080/reference/niin/014292368
    SELECT *
    FROM REFERENCE_NUMBER
    WHERE 1 = 1
    AND COD_GEN = (SELECT COD_GEN FROM GENERAL WHERE NIIN = '014292368')
    ;

-- ENDPOINT 3:
        -- QUERY COM A BUSCA POR NIIN E NUMERO_REFERENCIA COM LIKE
-- NESSA MODALIDADE IRA RETORNAR UM LISTA paginada DE REFERENCIAS PARA O NIIN BUSCADO
-- exemplo: http://localhost:8080/reference/niin/{niin}/reference/{numref}
        -- exemplo: http://localhost:8080/reference/niin/014292368/reference/MILPRF87937
        -- exemplo: http://localhost:8080/reference/niin/014292368/reference/MILPRF879*
        -- exemplo: http://localhost:8080/reference/niin/014292368/reference/*LPRF87937
        -- exemplo: http://localhost:8080/reference/niin/014292368/reference/*LPRF8793*

        -- aplicar ainda nos endpoints acima um filtro opcional
-- exemplo: http://localhost:8080/reference/niin/014292368/reference/MILPRF87937?REF_RNCC=4&REF_RNVC=1&ORIGEM=N
        -- exemplo: http://localhost:8080/reference/niin/014292368/reference/MILPRF87937?REF_RNCC=4&ORIGEM=N
        -- exemplo: http://localhost:8080/reference/niin/014292368/reference/MILPRF87937?ORIGEM=N
    SELECT *
    FROM REFERENCE_NUMBER
    WHERE 1 = 1
    AND COD_GEN = (SELECT COD_GEN FROM GENERAL WHERE NIIN = '014292368')
        --aqui deverá ser aplicado o LIKE PELA DIREITA E/OU LIKE PELA ESQUERDA, ou ainda sem like
  --MILPRF87937
    AND REF_NUM_NAOFOR LIKE 'MILPRF87937%'



*/












