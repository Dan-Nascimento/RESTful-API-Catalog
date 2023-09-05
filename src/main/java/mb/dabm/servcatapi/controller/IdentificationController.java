package mb.dabm.servcatapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.service.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/identifications")
@Data
@AllArgsConstructor
public class IdentificationController {

    @Autowired
    IdentificationService service;

    @GetMapping("/")
    public ResponseEntity<Page<Identification>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um único objeto do Seg A, de acordo com a chave passada no Path")
    public ResponseEntity<Identification> get(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }


    /*
     * busca. Exemplo: /niin/contains/0003745 - busco no query por ambos os lados
     *  Falta implementar:
     *        Exemplo: /niin/contains/*0037455 - busco pelo like na esquerda
     *        Exemplo: /niin/contains/0003745* - busco pelo like na direita
     * Exemplo: http://localhost:8080/identifications/niin/contains/0000374
     */
    @GetMapping("/niin/contains/{niin}")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinLike(
        @PathVariable("niin") String niin,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByNiinLike(niin, page, size));
    }


    /*
     * Exemplo: http://localhost:8080/identifications/niin/000037455
     */
    @GetMapping("/niin/{niin}")
    public ResponseEntity<Identification> listIdentificationByNiinId(
        @PathVariable("niin") String niin
    ) {
        return ResponseEntity.ok(service.findByNiinId(niin));
    }

    /**
     * Exemplo: http://localhost:8080/identifications/list
     * Exemplo: http://localhost:8080/identifications/list?page=1&size=100
     */
    @GetMapping("/list")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByAllNiin(page, size));
    }

    /*
     * Exemplo: http://localhost:8080/identifications/fsc/4935
     * Exemplo: http://localhost:8080/identifications/fsc/4935?page=1&size=100
     */
    @GetMapping("/fsc/{fsc}")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinFromFsc(
        @PathVariable("fsc") String fsc,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByNiinFromFsc(fsc, page, size));
    }

    /*
     * Exemplo: http://localhost:8080/identifications/inc/03656
     * Exemplo: http://localhost:8080/identifications/inc/03656?page=0&size=100
     */
    @GetMapping("/inc/{inc}")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinFromInc(
        @PathVariable("inc") String inc,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByNiinFromInc(inc, page, size));
    }

}
