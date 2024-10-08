package mb.dabm.servcatapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.service.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/identifications")
@Data
@AllArgsConstructor
@Tag(name = "GENERAL endpoints")
public class IdentificationController {

    /* Todos os endpoints estão documentados pela ferramenta swagger acessada pela url:
     http://localhost:8080/swagger-ui/index.html

     */
    @Autowired
    IdentificationService service;

    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela GENERAL")
    public ResponseEntity<Page<Identification>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) throws EntityNotFoundException {
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
    @Operation(summary = "Retorna todos os itens que contenham parte do Id buscado na coluna NIIN da tabela GENERAL")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinLike(
        @PathVariable("niin") String niin,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findByNiinLike(niin, page, size));
    }


    /*
     * Exemplo: http://localhost:8080/identifications/niin/000037455
     */
    @GetMapping("/niin/{niin}")
    @Operation(summary = "Retorna um único item buscado por ID na coluna NIIN da tabela GENERAL")
    public ResponseEntity<Identification> listIdentificationByNiinId(
        @PathVariable("niin") String niin
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findByNiinId(niin));
    }

    /**
     * Exemplo: http://localhost:8080/identifications/list
     * Exemplo: http://localhost:8080/identifications/list?page=1&size=100
     */
    @GetMapping("/list")
    @Operation(summary = "Retorna todos os registros da coluna NIIN da tabela GENERAL")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findByAllNiin(page, size));
    }

    /*
     * Exemplo: http://localhost:8080/identifications/fsc/4935
     * Exemplo: http://localhost:8080/identifications/fsc/4935?page=1&size=100
     */
    @GetMapping("/fsc/{fsc}")
    @Operation(summary = "Retorna todos os itens que contenham o Id buscado na coluna FSC da tabela GENERAL")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinFromFsc(
        @PathVariable("fsc") String fsc,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findByNiinFromFsc(fsc, page, size));
    }

    /*
     * Exemplo: http://localhost:8080/identifications/inc/03656
     * Exemplo: http://localhost:8080/identifications/inc/03656?page=0&size=100
     */
    @GetMapping("/inc/{inc}")
    @Operation(summary = "Retorna todos os itens que contenham o Id buscado na coluna INC da tabela GENERAL")
    public ResponseEntity<Page<Identification>> listIdentificationByNiinFromInc(
        @PathVariable("inc") String inc,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) throws EntityNotFoundException {
        return ResponseEntity.ok(service.findByNiinFromInc(inc, page, size));
    }

    /**
     * Exemplo POST
     *
     * @param identification
     * @return
     */
    @PostMapping
    @Operation(summary = "Realiza um post/create na tabela GENERAL")
    public ResponseEntity<Identification> createGeneral(@RequestBody Identification identification) {

        //preferível
        Identification _identification = service.createGeneral(identification);
        // opcional
        //Identification _identification = service.insertGeneral(identification);
        //System.out.println("ID: " + identification.toString());
        //return new ResponseEntity<>(_identification, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(_identification);
    }

    /**
     * Exemplo PUT
     * @param cod_gen
     * @param general
     * @return
     */
    @PutMapping("/{id}")
    @Operation(summary = "Realiza um update na tabela GENERAL por um codGen id")
    public ResponseEntity<Identification> updateIDGeneral(@PathVariable("id") long id,
                                                          @RequestBody Identification general) {
        Optional<Identification> identificationData = service.findById(id);

        if (identificationData.isPresent()) {
            Identification _i = identificationData.get();
            _i.setCodGen(id);
            _i.setFsc(general.getFsc());
            _i.setNiin(general.getNiin());
            _i.setNsn(general.getNsn());
            _i.setItemName(general.getItemName());
            _i.setInc(general.getInc());
            _i.setTiic(general.getTiic());
            _i.setRpdmrc(general.getRpdmrc());
            _i.setFmsn(general.getFmsn());
            _i.setMgmtPmi(general.getMgmtPmi());
            _i.setMgmtAdp(general.getMgmtAdp());
            _i.setMgmtDml(general.getMgmtDml());
            _i.setMgmtEsdc(general.getMgmtEsdc());
            _i.setMgmtCc(general.getMgmtCc());
            _i.setMgmtHmic(general.getMgmtHmic());
            _i.setOrigem(general.getOrigem());

            return new ResponseEntity<>(service.createGeneral(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Exemplo PUT
     * @param niin
     * @param general
     * @return
     */
    @PutMapping("/niin/{niin}")
    @Operation(summary = "Realiza um update na tabela GENERAL por um niin id")
    public ResponseEntity<Identification> updateNiinGeneral(@PathVariable("niin") String niin,
                                                            @RequestBody Identification general) {
        Optional<Identification> identificationData = Optional.ofNullable(service.findByNiinId(niin));

        if (identificationData.isPresent()) {
            Identification _i = identificationData.get();
            _i.setCodGen(general.getCodGen());
            _i.setFsc(general.getFsc());
            _i.setNiin(niin);
            _i.setNsn(general.getNsn());
            _i.setItemName(general.getItemName());
            _i.setInc(general.getInc());
            _i.setTiic(general.getTiic());
            _i.setRpdmrc(general.getRpdmrc());
            _i.setFmsn(general.getFmsn());
            _i.setMgmtPmi(general.getMgmtPmi());
            _i.setMgmtAdp(general.getMgmtAdp());
            _i.setMgmtDml(general.getMgmtDml());
            _i.setMgmtEsdc(general.getMgmtEsdc());
            _i.setMgmtCc(general.getMgmtCc());
            _i.setMgmtHmic(general.getMgmtHmic());
            _i.setOrigem(general.getOrigem());

            return new ResponseEntity<>(service.createGeneral(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Exemplo DELETE
     * @param cod_gen
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Realiza um delete na tabela GENERAL por um codGen id")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        Optional<Identification> productO = service.findById(id);
        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(productO.get().getCodGen());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
