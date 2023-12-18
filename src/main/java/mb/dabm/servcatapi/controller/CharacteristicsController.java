package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Characteristics;
import mb.dabm.servcatapi.service.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characteristics")
@Data
@AllArgsConstructor
@Tag(name = "CHARACTERISTICS endpoints")
public class CharacteristicsController {

    @Autowired
    CharacteristicsService service;

    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela CHARACTERISTICS")
    public ResponseEntity<Page<Characteristics>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByAll(page, size));
    }

    @GetMapping("/codgen/{codGen}")
    @Operation(summary = "Retorna uma lista de registros encontrados sem paginação na tabela CHARACTERISTICS")
    public ResponseEntity<List<Characteristics>> listByCodGen(
        @PathVariable("codGen") String codGen
    ){
        return ResponseEntity.ok(service.findByCodGen(codGen));
    }

}
