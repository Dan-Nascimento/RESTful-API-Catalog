package mb.dabm.servcatapi.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.EmpresasServcat;
import mb.dabm.servcatapi.service.EmpresasServcatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
@Data
@AllArgsConstructor
public class EmpresasServCatController {

    @Autowired
    EmpresasServcatService service;


    @GetMapping("/")
    public ResponseEntity<Page<EmpresasServcat>> listAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "20") int size
    ){
        return ResponseEntity.ok(service.findByAll(page, size));
    }


}
