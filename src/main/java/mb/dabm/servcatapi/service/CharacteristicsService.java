package mb.dabm.servcatapi.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.Characteristics;
import mb.dabm.servcatapi.entity.Management;
import mb.dabm.servcatapi.repository.CharacteristicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class CharacteristicsService {

    @Autowired
    CharacteristicsRepository repository;

    public Page<Characteristics> findByAll(int page, int size) {
        return repository.getByAll(PageRequest.of(page, size));
    }

    public List<Characteristics> findByCodGen(String codGen) {
        return repository.getByCodGen(codGen);
    }

}
