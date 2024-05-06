package mb.dabm.servcatapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Characteristics;
import mb.dabm.servcatapi.service.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        @PathVariable("codGen") String codGen1
    ){
        return ResponseEntity.ok(service.findByCodGen(codGen1));
    }


    /*
     Teste realizado com os dados fictícios aleatórios, conforme abaixo. A API não está permitindo realizar o teste com os dados constantes no mantis por um possível conflito de cadastro com a API antiga.
    {
    "cod_gen": 14292679,
    "char_mrc": "AGXX",
    "cod_char": 34304559,
    "char_clear_text_reply": "LIQUID"
}

     */

    @PostMapping
    @Operation(summary = "Realiza um post/create na tabela CHARACTERISTICS")
    public ResponseEntity<Characteristics> createCharacteristics(@RequestBody Characteristics characteristics) {

        Characteristics _characteristics = service.createCharacteristics(characteristics);
        return ResponseEntity.status(HttpStatus.CREATED).body(_characteristics);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Realiza um update na tabela CHARACTERISTICS por um codChar id")
    public ResponseEntity<Characteristics> updateIDCharacteristics(@PathVariable("id") Long id,
                                                                   @RequestBody Characteristics characteristics) {
        Optional<Characteristics> characteristicsData = Optional.ofNullable(service.findById(id));

        if (characteristicsData.isPresent()) {
            Characteristics _i = characteristicsData.get();
            _i.setCodChar(id);
            _i.setCodGen(characteristics.getCodGen());
            _i.setCharMrc(characteristics.getCharMrc());
            _i.setCharClearTextReply(characteristics.getCharClearTextReply());

            return new ResponseEntity<>(service.createCharacteristics(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/codchar/{codChar}")
    @Operation(summary = "Realiza um update na tabela CHARACTERISTICS por um codChar id")
    public ResponseEntity<Characteristics> updateCodGenCharacteristics(@PathVariable("codChar") Long codChar,
                                                                       @RequestBody Characteristics characteristics) {
        Optional<Characteristics> characteristicsData = Optional.ofNullable(service.findById(codChar));

        if (characteristicsData.isPresent()) {
            Characteristics _i = characteristicsData.get();
            _i.setCodChar(codChar);
            _i.setCodGen(characteristics.getCodGen());
            _i.setCharMrc(characteristics.getCharMrc());
            _i.setCharClearTextReply(characteristics.getCharClearTextReply());

            return new ResponseEntity<>(service.createCharacteristics(_i), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Realiza um delete na tabela CHARACTERISTICS por um codChar id")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        Optional<Characteristics> productO = service.findById(id);
        if (productO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(productO.get().getCodChar());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
