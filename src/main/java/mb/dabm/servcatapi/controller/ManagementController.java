package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Management;
import mb.dabm.servcatapi.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management")
@Data
@AllArgsConstructor
@Tag(name = "GERÊNCIA endpoints")
public class ManagementController {

    @Autowired
    ManagementService service;

    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela MANAGEMENT")
    public ResponseEntity<Page<Management>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByAll(page, size));
    }

    @GetMapping("/codgen/{codGen}")
    @Operation(summary = "Retorna uma lista de registros encontrados sem paginação na tabela MANAGEMENT")
    public ResponseEntity<List<Management>> listByCodGen(
        @PathVariable("codGen") String codGen
    ) {
        return ResponseEntity.ok(service.findByCodGen(codGen));
    }

}
