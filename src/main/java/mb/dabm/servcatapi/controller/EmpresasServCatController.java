package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.EmpresasServcat;
import mb.dabm.servcatapi.service.EmpresasServcatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
@Data
@AllArgsConstructor
@Tag(name = "SUPPLIER endpoints")
public class EmpresasServCatController {


    /* Todos os endpoints estão documentados pela ferramenta swagger acessada pela url:
     http://localhost:8080/swagger-ui/index.html
     */

    @Autowired
    EmpresasServcatService service;

    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela SUPPLIER")
    public ResponseEntity<Page<EmpresasServcat>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByAll(page, size));
    }


    /*
     *Exemplo: http://localhost:8080/companies/cageCode/0026V
     */
    @GetMapping("/cage/{cageCode}")
    @Operation(summary = "Retorna um único item buscado por Id da coluna CAGE_CODE na tabela SUPPLIERS")
    public ResponseEntity<EmpresasServcat> listByCageCodeId(
        @PathVariable("cageCode") String cageCode
    ) {
        return ResponseEntity.ok(service.findByCageCodeId(cageCode));
    }


    /*
     *Exemplo: http://localhost:8080/companies/cageCountry/UNITED%20STATES
     *Exemplo: http://localhost:8080/companies/cageCountry/UNITED%20STATES?page=0&size=100
     */
    @GetMapping("/country/{cageCountry}")
    @Operation(summary = "Retorna todas as empresas do país buscado, conforme a coluna CAGE_COUNTRY da tabela SUPPLIER")
    public ResponseEntity<Page<EmpresasServcat>> listByCageCountry(
        @PathVariable("cageCountry") String cageCountry,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByCageCountry(cageCountry, page, size));
    }


    /*
     *Exemplo: http://localhost:8080/companies/companyName/ADMA
     *Exemplo: http://localhost:8080/companies/companyName/ADMA?page=1&size=100
     */
    @GetMapping("/company/{cageCompanyName}")
    @Operation(summary = "Retorna todos os itens buscados por like tanto pelas iniciais quanto pelas finais na coluna CAGE_COMPANY_NAME da tabela SUPPLIER")
    Page<EmpresasServcat> all(
        @PathVariable("cageCompanyName") String cageCompanyName,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return service.getByCageCompanyName(cageCompanyName, page, size);

    }
}
