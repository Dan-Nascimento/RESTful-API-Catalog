package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.ReferenceNumber;
import mb.dabm.servcatapi.service.ReferenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/referenceNumber")
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
     * Exemplo: http://localhost:8080/referenceNumber/
     */
    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela REFERENCE_NUMBER")
    public ResponseEntity<Page<ReferenceNumber>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByReferenceNumber(page, size));
    }

}
