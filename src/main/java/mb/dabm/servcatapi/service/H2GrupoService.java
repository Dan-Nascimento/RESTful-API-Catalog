package mb.dabm.servcatapi.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.H2Grupo;
import mb.dabm.servcatapi.repository.H2GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class H2GrupoService {

    @Autowired
    H2GrupoRepository repository;

    public Page<H2Grupo> findByAll(int page, int size) { return repository.getByAll(PageRequest.of(page, size));}


    public H2Grupo findByGrupoId(String grupo) { return repository.getByGrupoId(grupo);}


    public List<H2Grupo> getByGrupoLike(String grupo) {
        long count = grupo.chars().filter(ch -> ch == '*').count();

        if (count > 0) {
            grupo = grupo.replace("*", "%");
        } else {
            grupo = grupo + "%";
        }
        return repository.getByGrupoLike(grupo);
    }


}
