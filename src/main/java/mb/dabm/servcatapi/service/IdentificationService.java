package mb.dabm.servcatapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.mapper.IdentificationMapper;
import mb.dabm.servcatapi.model.IdentificationDto;
import mb.dabm.servcatapi.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class IdentificationService {

    @Autowired
    IdentificationRepository repository;

    @Autowired
    IdentificationMapper identificationMapper;

    public Page<Identification> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public Page<IdentificationDto> findAllDto(int page, int size) {
        val pages = repository.findAll(PageRequest.of(page, size));
        return identificationMapper.toRest(pages);
    }

    public Identification findById(Long id){
        return repository.getReferenceById(id);
    }

}
