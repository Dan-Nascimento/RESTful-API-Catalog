package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.EmpresasServcat;
import mb.dabm.servcatapi.entity.ReferenceNumber;
import mb.dabm.servcatapi.service.ReferenceNumberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/references")
@AllArgsConstructor
@Data
@Tag(name = "REFERENCE_NUMBER endpoints")
public class ReferenceNumberController {

     /* Todos os endpoints estão documentados pela ferramenta swagger acessada pela url:
     http://localhost:8080/swagger-ui/index.html
     */

    @Autowired
    ReferenceNumberService service;

    /*
     * Exemplo: http://localhost:8080/reference/
     */
    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela REFERENCE_NUMBER")
    public ResponseEntity<Page<ReferenceNumber>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByReferenceNumber(page, size));
    }

    /*
    exemplo: http://localhost:8080/reference/30620926

     */
    @GetMapping("/{codRef}")
    @Operation(summary = "Query retorna um único registro com a busca por COD_REF na tabela REFERENCE_NUMBER")
    public ResponseEntity<ReferenceNumber> listByCodRefId(
        @PathVariable("codRef") String codRef
    ) {
        return ResponseEntity.ok(service.findByCodRefId(codRef));
    }

    /*
    exemplo: http://localhost:8080/reference/niin/014292368
     */
    @GetMapping("/niin/{niin}")
    @Operation(summary = "Query retorna uma lista paginada de referências com a busca por NIIN na tabela REFERENCE_NUMBER e GENERAL")
    public ResponseEntity<Page<ReferenceNumber>> listByReferenceNiin(
        @PathVariable("niin") String niin,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByReferenceNiin(niin, page, size));
    }

    @GetMapping("/niin/{niin}/reference/{refNumNaoFor}")
    Page<ReferenceNumber> listByNiinAndRefNumNaoFor(
        @PathVariable("niin") String niin,
        @PathVariable("refNumNaoFor") String refNumNaoFor,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return service.getByNiinAndNumRef(niin, refNumNaoFor, page, size);

    }

    @GetMapping("/numref/{refNumNaoFor}")
    Page<ReferenceNumber> listByRefNumNaoforContainingAndOrigem(
        @PathVariable("refNumNaoFor") String refNumNaoFor,
        @RequestParam(value = "origem", defaultValue = "N") String origem,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return service.getByRefNumNaoforContainingAndOrigem(refNumNaoFor, origem, page, size);

    }

}
