package mb.dabm.servcatapi.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.EmpresasServcat;
import mb.dabm.servcatapi.repository.EmpresasServcatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class EmpresasServcatService {

    @Autowired
    EmpresasServcatRepository repository;

    public Page<EmpresasServcat> findByAll(int page, int size) { return repository.getByAll(PageRequest.of(page, size));}


}
