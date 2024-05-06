package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.ReferenceNumber;
import mb.dabm.servcatapi.service.ReferenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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

    /*
    @PostMapping
    @Operation(summary = "Realiza um Post/create na tabela REFERENCE_NUMBER")
    public ResponseEntity<ReferenceNumber> createReference(@RequestBody ReferenceNumber referenceNumber) {

        ReferenceNumber _referenceNumber = service.insertReferenceNumber(referenceNumber);
        System.out.println("ID: " + referenceNumber.toString());
        return new ResponseEntity<>(_referenceNumber, HttpStatus.CREATED);

    }

     */

    @PostMapping
    @Operation(summary = "Realiza um post/create na tabela REFERENCE_NUMBER")
    public ResponseEntity<ReferenceNumber> createReference(@RequestBody ReferenceNumber referenceNumber) {

        ReferenceNumber _referenceNumber = service.createReference(referenceNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(_referenceNumber);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Realiza um update na tabela REFERENCE_NUMBER utilizando um codRef id")
    public ResponseEntity<ReferenceNumber> updateIDReference(@PathVariable("id") long id,
                                                            @RequestBody ReferenceNumber referenceNumber) {
        Optional<ReferenceNumber> referenceNumberData = service.findById(id);

        if (referenceNumberData.isPresent()) {
            ReferenceNumber _i = referenceNumberData.get();
            _i.setCodRef(id);
            _i.setCodGen(referenceNumber.getCodGen());
            _i.setCageCode(referenceNumber.getCageCode());
            _i.setRefRnfc(referenceNumber.getRefRnfc());
            _i.setRefRnvc(referenceNumber.getRefRnvc());
            _i.setRefRncc(referenceNumber.getRefRncc());
            _i.setRefRnsc(referenceNumber.getRefRnsc());
            _i.setRefDac(referenceNumber.getRefDac());
            _i.setRefRnjc(referenceNumber.getRefRnjc());
            _i.setRefIsc(referenceNumber.getRefIsc());
            _i.setRefRnaac(referenceNumber.getRefRnaac());
            _i.setRefNum(referenceNumber.getRefNum());
            _i.setRefNumNaofor(referenceNumber.getRefNumNaofor());
            _i.setOrigem(referenceNumber.getOrigem());
            _i.setRefMsds(referenceNumber.getRefMsds());
            _i.setRefSadc(referenceNumber.getRefSadc());

            return new ResponseEntity<>(service.createReference(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/codref/{codRef}")
    @Operation(summary = "Realiza um update na tabela REFERENCE_NUMBER utilizando um codRef id")
    public ResponseEntity<ReferenceNumber> updateCodRefReferenceNumber(@PathVariable("codRef") Long codRef,
                                                                  @RequestBody ReferenceNumber referenceNumber) {
        Optional<ReferenceNumber> referenceNumberData = Optional.ofNullable(service.findById(codRef));

        if (referenceNumberData.isPresent()) {
            ReferenceNumber _i = referenceNumberData.get();
            _i.setCodRef(codRef);
            _i.setCodGen(referenceNumber.getCodGen());
            _i.setCageCode(referenceNumber.getCageCode());
            _i.setRefRnfc(referenceNumber.getRefRnfc());
            _i.setRefRnvc(referenceNumber.getRefRnvc());
            _i.setRefRncc(referenceNumber.getRefRncc());
            _i.setRefRnsc(referenceNumber.getRefRnsc());
            _i.setRefDac(referenceNumber.getRefDac());
            _i.setRefRnjc(referenceNumber.getRefRnjc());
            _i.setRefIsc(referenceNumber.getRefIsc());
            _i.setRefRnaac(referenceNumber.getRefRnaac());
            _i.setRefNum(referenceNumber.getRefNum());
            _i.setRefNumNaofor(referenceNumber.getRefNumNaofor());
            _i.setOrigem(referenceNumber.getOrigem());
            _i.setRefMsds(referenceNumber.getRefMsds());
            _i.setRefSadc(referenceNumber.getRefSadc());

            return new ResponseEntity<>(service.createReference(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Realiza um delete na tabela REFERENCE_NUMBER por um codRef id")
    public ResponseEntity<HttpStatus> deleteByReferenceId(@PathVariable("id") long id) {
        Optional<ReferenceNumber> productO = service.findById(id);
        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteByReferenceId(productO.get().getCodRef());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
