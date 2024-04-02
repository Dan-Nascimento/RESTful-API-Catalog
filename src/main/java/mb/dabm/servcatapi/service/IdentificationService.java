package mb.dabm.servcatapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class IdentificationService {

    @Autowired
    IdentificationRepository repository;

    public Page<Identification> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Identification findById(Long id) {
        return repository.getReferenceById(id);
    }

    /**
     * @param cod_gen
     * @return
     */
    public Optional<Identification> findById(long id) {
        return repository.findById(id);
    }

    /**
     * @param cod_gen
     */
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Page<Identification> findByNiinLike(String niin, int page, int size) {
        return repository.getByNiinLike(niin, PageRequest.of(page, size));
    }

    public Identification findByNiinId(String niin) {
        return repository.getByNiinId(niin);
    }

    public Page<Identification> findByAllNiin(int page, int size) {
        return repository.getByAllNiin(PageRequest.of(page, size));
    }

    public Page<Identification> findByNiinFromFsc(String fsc, int page, int size) {
        return repository.getByNiinFromFsc(fsc, PageRequest.of(page, size));
    }

    public Page<Identification> findByNiinFromInc(String inc, int page, int size) {
        return repository.getByNiinFromInc(inc, PageRequest.of(page, size));
    }

    /**
     * Forma 1 - preferível
     *
     * @param identification
     * @return
     */
    public Identification createGeneral(Identification identification) {
        return repository.save(identification);
    }

    /**
     * Forma 2 - opcional com SQL NATIVE
     *
     * @param identification
     * @return Identification | null
     */
    public Identification insertGeneral(Identification identification) {
        int rs = repository.insert(identification);
        return (rs > 0) ? identification : null;
    }


}
