package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.H2Grupo;
import mb.dabm.servcatapi.service.H2GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h2/grupo")
@Data
@AllArgsConstructor
@Tag(name = "H2_GRUPO endpoints")
public class H2GrupoController {

    @Autowired
    H2GrupoService service;

    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela H2_GRUPO")
    public ResponseEntity<Page<H2Grupo>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByAll(page, size));
    }

    @GetMapping("/{grupo}")
    @Operation(summary = "Retorna um registro da coluna GRUPO na tabela H2_GRUPO")
    public ResponseEntity<H2Grupo> listByGrupoId(
        @PathVariable("grupo") String grupo

    ) {
        return ResponseEntity.ok(service.findByGrupoId(grupo));
    }

    @GetMapping("/name/{grupo}")
    @Operation(summary = "Retorna todos os itens buscados por like pelos dígitos finais na coluna GRUPO da tabela H2_GRUPO")
    List<H2Grupo> listByGrupoLike(
        @PathVariable("grupo") String grupo
    ) {
        return service.getByGrupoLike(grupo);

    }

}
