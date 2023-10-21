package mb.dabm.servcatapi.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.ReferenceNumber;
import mb.dabm.servcatapi.repository.ReferenceNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class ReferenceNumberService {

    @Autowired
    ReferenceNumberRepository repository;

    public Page<ReferenceNumber> findByReferenceNumber (int page, int size) {return repository.getByAllReferenceNumber(PageRequest.of(page, size));}


}
