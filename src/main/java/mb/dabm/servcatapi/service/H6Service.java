package mb.dabm.servcatapi.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.H6;
import mb.dabm.servcatapi.model.projection.H6DTO;
import mb.dabm.servcatapi.repository.H6Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class H6Service {

    @Autowired
    H6Repository repository;


    public Page<H6> findByAll(int page, int size) {
        return repository.getByAll(PageRequest.of(page, size));
    }


    public H6 findByIncId(String inc) {
        return repository.getByIncId(inc);
    }


    public Page<H6> findByFiig(String fiig, int page, int size) {
        return repository.getByFiig(fiig, PageRequest.of(page, size));
    }

    public List<H6> findByIncTabelas(String inc) {
        return repository.getByIncTabelas(inc);
    }

    public Page<H6DTO> finAllIncByFiig(String fiig, int page, int size) {
        return repository.getAllIncByFiig(fiig, PageRequest.of(page, size));
    }

}
