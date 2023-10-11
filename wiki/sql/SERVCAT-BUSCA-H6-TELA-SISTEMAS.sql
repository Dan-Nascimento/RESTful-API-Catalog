--2 -  busca/filtro combobox INC da tela de consultas do h6
--imagem: servcat-h6-busca1.png
SELECT DISTINCT INC
FROM H6
WHERE INC LIKE '49106%'
GROUP BY INC;
--2.1 variacao para base 
SELECT *
FROM H6
WHERE INC LIKE '49106%';

--3 -- corresponde a tela servcat da aba resultado com base na busca da tela de consultas do h6
-- imagem: servcat-h6-resltado1.png
SELECT H6.INC, FIIG, ITEM_NAME, IC.CLASSE
FROM H6 H6, INC_CLASSE IC
WHERE H6.INC = IC.INC (+)
AND H6.INC = '49106'
GROUP BY H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, CONDITION_CODE, IC.CLASSE;

--4-- corresponde a tela da aba Artigos com base na busca da tela de consultas do h6
-- imagem: servcat-h6-consulta1.png
SELECT H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, CONDITION_CODE, IC.CLASSE
FROM H6, INC_CLASSE IC
WHERE H6.INC = IC.INC (+) 
AND H6.INC = '49106';

SELECT INC, CLASSE
FROM INC_CLASSE
WHERE INC = '49106';
---------------------------------------------------------------------------------------------------------
--1 
--busca por FIIG na abao h6->tela busca
--imagem: servcat-h6-busca2-FIIG.png
SELECT DISTINCT FIIG
FROM H6
WHERE FIIG LIKE 'A45000%'
GROUP BY FIIG;
-- variacao
SELECT * FROM H6 WHERE FIIG LIKE 'A45000%';

--2-- corresponde a tela servcat da aba resultado com base na busca da tela de consultas do h6 - por FIIG
--imagem:servcat-h6-resultado2-busca-por-fiig.png
SELECT H6.INC, FIIG, ITEM_NAME, IC.CLASSE
FROM H6 H6, INC_CLASSE IC
WHERE H6.INC = IC.INC (+)
AND FIIG = 'A45000'
GROUP BY H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, CONDITION_CODE, IC.CLASSE;


--3--corresponde a tela servcat da aba resultado com base na busca da tela de consultas do h6
--IMAGEM: servcat-h6-DADOS-consulta2.png
SELECT H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, CONDITION_CODE, IC.CLASSE
FROM H6, INC_CLASSE IC
WHERE H6.INC = IC.INC (+) 
AND H6.INC = '48996';

---------------------------------------------------------------------------------------------------------
--

--1 - cb nome do item - BR/EN 
-- nome item: AMOBARBITAL SODIUMCAPSULES
SELECT DISTINCT N_NACIONAL FROM H6;
-- nome ingles
SELECT DISTINCT ITEM_NAME FROM H6;

--2 - filtro cb
SELECT DISTINCT ITEM_NAME
FROM H6
WHERE ITEM_NAME LIKE 'AMOBARBITAL%'
GROUP BY ITEM_NAME;

-- corresponde a tela servcat da aba resultado com base na busca da tela de consultas do h6 - ITEM NAME
-- imagem: servcat-h6-DADOS-consulta3.png
SELECT H6.INC, FIIG, ITEM_NAME, IC.CLASSE
FROM H6 H6, INC_CLASSE IC
WHERE H6.INC = IC.INC (+)
AND ITEM_NAME = 'AMOBARBITAL SODIUMCAPSULES'
GROUP BY H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, CONDITION_CODE, IC.CLASSE;

--3--corresponde a tela servcat da aba resultado com base na busca da tela de consultas do h6
--IMAGEM: servcat-h6-DADOS-consulta3.png
SELECT H6.INC, FIIG, ITEM_NAME, DEFINITION, STATUS_INC, NOME_APROV, APP_KEY, CONDITION_CODE, IC.CLASSE
FROM H6, INC_CLASSE IC
WHERE H6.INC = IC.INC (+) 
AND H6.INC = '48996';