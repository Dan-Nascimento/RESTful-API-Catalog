package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.H2Classe;
import mb.dabm.servcatapi.service.H2ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h2")
@Data
@AllArgsConstructor
@Tag(name = "H2_CLASSE endpoints")
public class H2ClasseController {


    @Autowired
    H2ClasseService service;


    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela H2_CLASSE")
    public ResponseEntity<Page<H2Classe>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByAll(page, size));
    }



    @GetMapping("/contains/{classe}")
    @Operation(summary = "Retorna uma lista de registro pesquisando por contendo(containing - like) na tabela H2_CLASSE")
    List<H2Classe> listByClasseContaining(
        @PathVariable("classe") String classe,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return service.getByClasseContaining(classe, page, size);

    }



    @GetMapping("/classe/{classe}")
    @Operation(summary = "Retorna um registro da coluna CLASSE na tabela H2_CLASSE")
    public ResponseEntity<H2Classe> listByClasseId(
        @PathVariable("classe") String classe

    ) {
        return ResponseEntity.ok(service.findByClasseId(classe));
    }



    @GetMapping("/grupo/{grupo}")
    @Operation(summary = "Retorna uma lista de registros encontrados sem paginação na tabela H2_CLASSE")
    public ResponseEntity<List<H2Classe>> listByGrupo(
        @PathVariable("grupo") String grupo
    ){
        return ResponseEntity.ok(service.findByGrupoList(grupo));
    }




}
