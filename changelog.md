# Changelog do Projeto da API do SISCAT-BR

Todas as alterações da **API de consulta do Sistema do SISCAT-BR** serão documentadas neste arquivo.

## [3.0.0] Integração SINGRA + CEID - 15.06.2022

### Implementação
- Adição do package _br.mil.fab_ com implementações de interesse da FAB
- Adição do package _br.gov.defesa_ com implementações de interesse do CASLODE/MD
- Update da _lib_ do Swagger para versão 3.x (/swagger-ui/index.html) e (/v2/api-docs)

## [2.0.2] Lançamento de Correção - 11.04.2022

### Bug
- Erro ao exibir os dados do recurso do Segmento 8 - Dados de Empresa; e
- Erro ao exibir os dados do Recurso de Segmentos - Segmentos A, B, C e V.

## [2.0.1] Lançamento Correção - 15.03.2022

### Bug
- Removido Null de getAnteriores Replaced (Substituídos) - getSubstituicoes Replacements (Substituições).

## [1.0.10] Lançamento de Funcionalidades - 21.09.2021

### Implementação
- Resource IDN completo/incompleto, basta colocar *;
- Resource Segmento 8 com novas chaves de REPLACEMENT e REPLACED;
- Resource /empresas/replacement/{NCAGE} para um ou mais NCAGE/CODEMP sucessores (substitutos do NCAGE) do NCAGE pesquisado; e
- Resource /empresas/replaced/{NCAGE} para um ou mais NCAGE/CODEMP substituídos (antecessores) o NCAGE pesquisado.

## [1.0.2] Lançamento de Funcionalidades - 25.08.2021

### Implementação
- Refatorado SQL Empresas, retornando STE, ISIC e IDN.

## [1.0.1] Lançamento Correção - 21.02.2021

### Implementação
- Refatorado SQL Empresas, retornando se houver NCAGE_REPLACE.


## [1.0.0] Lançamento Oficial - 20/07/2020
### Implementação
- Segmentos A, B, C e V;
- Implementado JOSN de erros para recursos não encontrados;
- SQL MRC CXCY para EVO BR;
- Script Consulta SEG C, retornando a Referencia (NCAGE + NUMREF);
- Script Consulta SEG V, retornando busca por MRC;
- Consulta Países com ISO preenchido;

### Descontinuada
- Nenhuma.

### Bug
- Refatorado Erro Resource segmentos, quando for passando um NIIN que não exista no SISCAT-BR;

### Removida
- Nenhuma.

### Segurança
- Autenticação Básica;e
- Implementada https.
