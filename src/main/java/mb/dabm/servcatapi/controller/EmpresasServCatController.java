package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.EmpresasServcat;
import mb.dabm.servcatapi.service.EmpresasServcatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<EmpresasServcat> createSupplier(@RequestBody EmpresasServcat empresasServcat) {

        EmpresasServcat _empresasServcat = service.createSupplier(empresasServcat);
        return ResponseEntity.status(HttpStatus.CREATED).body(_empresasServcat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresasServcat> updateIDSupplier(@PathVariable("id") long id,
                                                          @RequestBody EmpresasServcat supplier) {
        Optional<EmpresasServcat> empresasServcatData = service.findById(id);

        if (empresasServcatData.isPresent()) {
            EmpresasServcat _i = empresasServcatData.get();
            _i.setCageCode(supplier.getCageCode());
            _i.setCageStatus(supplier.getCageStatus());
            _i.setCageType(supplier.getCageType());
            _i.setCageCao(supplier.getCageCao());
            _i.setCageAdp(supplier.getCageAdp());
            _i.setCageRplm(supplier.getCageRplm());
            _i.setCageAssoc(supplier.getCageAssoc());
            _i.setCageAffil(supplier.getCageAffil());
            _i.setCageSize(supplier.getCageSize());
            _i.setCagePrimaryBusiness(supplier.getCagePrimaryBusiness());
            _i.setCageTypeOfBusiness(supplier.getCageTypeOfBusiness());
            _i.setCageWomanOwned(supplier.getCageWomanOwned());
            _i.setCageSicCodes(supplier.getCageSicCodes());
            _i.setCageFax(supplier.getCageFax());
            _i.setCageCompanyName(supplier.getCageCompanyName());
            _i.setCageCompanyAddress(supplier.getCageCompanyAddress());
            _i.setCagePoBox(supplier.getCagePoBox());
            _i.setCageCity(supplier.getCageCity());
            _i.setCageState(supplier.getCageState());
            _i.setCageCountry(supplier.getCageCountry());
            _i.setCageZipCode(supplier.getCageZipCode());
            _i.setCageTelephone(supplier.getCageTelephone());
            _i.setCageFormerAddress(supplier.getCageFormerAddress());

            return new ResponseEntity<>(service.createSupplier(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cage/{cageCode}")
    public ResponseEntity<EmpresasServcat> updateCageCodeSupplier(@PathVariable("cageCode") String cageCode,
                                                            @RequestBody EmpresasServcat supplier) {
        Optional<EmpresasServcat> empresasServcatData = Optional.ofNullable(service.findByCageCodeId(cageCode));

        if (empresasServcatData.isPresent()) {
            EmpresasServcat _i = empresasServcatData.get();
            _i.setCageCode(supplier.getCageCode());
            _i.setCageStatus(supplier.getCageStatus());
            _i.setCageType(supplier.getCageType());
            _i.setCageCao(supplier.getCageCao());
            _i.setCageAdp(supplier.getCageAdp());
            _i.setCageRplm(supplier.getCageRplm());
            _i.setCageAssoc(supplier.getCageAssoc());
            _i.setCageAffil(supplier.getCageAffil());
            _i.setCageSize(supplier.getCageSize());
            _i.setCagePrimaryBusiness(supplier.getCagePrimaryBusiness());
            _i.setCageTypeOfBusiness(supplier.getCageTypeOfBusiness());
            _i.setCageWomanOwned(supplier.getCageWomanOwned());
            _i.setCageSicCodes(supplier.getCageSicCodes());
            _i.setCageFax(supplier.getCageFax());
            _i.setCageCompanyName(supplier.getCageCompanyName());
            _i.setCageCompanyAddress(supplier.getCageCompanyAddress());
            _i.setCagePoBox(supplier.getCagePoBox());
            _i.setCageCity(supplier.getCageCity());
            _i.setCageState(supplier.getCageState());
            _i.setCageCountry(supplier.getCageCountry());
            _i.setCageZipCode(supplier.getCageZipCode());
            _i.setCageTelephone(supplier.getCageTelephone());
            _i.setCageFormerAddress(supplier.getCageFormerAddress());

            return new ResponseEntity<>(service.createSupplier(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteByCompaniesId(@PathVariable("id") long id) {
        Optional<EmpresasServcat> productO = service.findById(id);
        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteByCompaniesId(Long.parseLong(productO.get().getCageCode()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
