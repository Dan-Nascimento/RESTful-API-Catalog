package mb.dabm.servcatapi.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.ReferenceNumber;
import mb.dabm.servcatapi.repository.ReferenceNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class ReferenceNumberService {

    @Autowired
    ReferenceNumberRepository repository;

    public Page<ReferenceNumber> findByReferenceNumber(int page, int size) {
        return repository.getByAllReferenceNumber(PageRequest.of(page, size));
    }

    public ReferenceNumber findByCodRefId (String codRef) {
        return repository.getByCodRefId(codRef);
    }

    public ReferenceNumber findById(Long id) {
        return repository.getReferenceById(id);
    }

    public Optional<ReferenceNumber> findById(long id) {
        return repository.findById(id);
    }

    public void deleteByReferenceId(long id) {
        repository.deleteById(id);
    }

    public Page<ReferenceNumber> findByReferenceNiin (String niin, int page, int size) {
        return repository.getByReferenceNiin(niin, PageRequest.of(page, size));
    }

    public Page<ReferenceNumber> getByNiinAndNumRef (String niin, String refNumNaoFor, int page, int size) {

        long count = refNumNaoFor.chars().filter(ch -> ch == '*').count();

        if (count > 0) {
            refNumNaoFor = refNumNaoFor.replace("*", "%");
        } else {
            refNumNaoFor = "%" + refNumNaoFor + "%";
        }

        return repository.getByNiinAndNumRef(niin, refNumNaoFor, PageRequest.of(page, size));

    }

    public Page<ReferenceNumber> getByRefNumNaoforContainingAndOrigem(String refNumNaoFor, String origem, int page, int size) {
        return repository.findByRefNumNaoforContainingAndOrigem(refNumNaoFor, origem, PageRequest.of(page, size));
    }

    public ReferenceNumber createReference(ReferenceNumber referenceNumber) {
        return repository.save(referenceNumber);
    }

}


/*
    long count = niin.chars().filter(ch -> ch == '*').count();

        if (count > 0) {
            niin = niin.replace("*", "%");
        } else {
            niin = "%" + niin + "%";
        }
 */
