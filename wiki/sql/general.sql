/* Formatted on 02/09/2023 17:10:57 (QP5 v5.388) */
SELECT                                                       /*  FIRST_ROWS */
       COD_GEN,
       FSC,
       NIIN,
       NSN,
       ITEM_NAME,
       INC,
       TIIC,
       RPDMRC,
       FMSN,
       MGMT_PMI,
       MGMT_ADP,
       MGMT_DML,
       MGMT_ESDC,
       MGMT_CC,
       MGMT_HMIC,
       ORIGEM
  FROM GENERAL
 WHERE 1 = 1
   --AND ROWNUM < 1000
   --AND FSC = '4935'
   AND INC = '03656'
;

SELECT /*  FIRST_ROWS */ COUNT(*) FROM GENERAL;