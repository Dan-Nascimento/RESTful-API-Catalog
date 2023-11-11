package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.H6;
import mb.dabm.servcatapi.service.H6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/h6")
@Tag(name = "H6 endpoints")
public class H6Controller {

    @Autowired
    H6Service service;




    @GetMapping("/")
    @Operation(summary = "Retorna todos os itens cadastrados na tabela H6")
    public ResponseEntity<Page<H6>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByAll(page, size));
    }



    @GetMapping("/inc/{inc}")
    @Operation(summary = "Retorna um registro para a coluna INC da tabela H6")
    public ResponseEntity<H6> listByIncId(
        @PathVariable("inc") String inc
    ) {
        return ResponseEntity.ok(service.findByIncId(inc));
    }



    @GetMapping("/fiig/{fiig}")
    @Operation(summary = "Retorna uma lista de registros, com paginação, para a coluna FIIG da tabela H6")
    public ResponseEntity<Page<H6>> listByFiig(
        @PathVariable("fiig") String fiig,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(service.findByFiig(fiig, page, size));
    }


    @GetMapping("/inc-classe/{inc}")
    @Operation(summary = "Retorna uma lista de registros, sem paginação, para a coluna INC das tabelas H6 e INC_CLASSE")
    public ResponseEntity<List<H6>> listByIncTabelas(
        @PathVariable("inc") String inc
    ) {
        return ResponseEntity.ok(service.findByIncTabelas(inc));
    }


}
