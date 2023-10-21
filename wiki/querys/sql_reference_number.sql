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
-- QUERY RETORNA UM LISTA paginada DE REFERENCIAS COM A BUSCA POR NIIN
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
;