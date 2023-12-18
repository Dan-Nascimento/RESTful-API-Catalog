package mb.dabm.servcatapi.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Characteristics;
import mb.dabm.servcatapi.entity.H2Classe;
import mb.dabm.servcatapi.entity.ReferenceNumber;
import mb.dabm.servcatapi.repository.H2CLasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class H2ClasseService {

    @Autowired
    H2CLasseRepository repository;

    public Page<H2Classe> findByAll(int page, int size) {
        return repository.getByAll(PageRequest.of(page, size));
    }

    public List<H2Classe> getByClasseContaining(String classe, int page, int size) {

        long count = classe.chars().filter(ch -> ch == '*').count();

        if (count > 0) {
            classe = classe.replace("*", "%");
        } else {
            classe = "%" + classe + "%";
        }

        return repository.getByClasseContaining(classe, PageRequest.of(page, size));

    }

    public H2Classe findByClasseId(String classe) {
        return repository.getByClasseId(classe);
    }

    public List<H2Classe> findByGrupoList(String grupo) {
        return repository.getByGrupoList(grupo);
    }

}
