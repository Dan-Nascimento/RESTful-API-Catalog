package mb.dabm.servcatapi.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import mb.dabm.servcatapi.entity.EmpresasServcat;
import mb.dabm.servcatapi.repository.EmpresasServcatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class EmpresasServcatService {

    @Autowired
    EmpresasServcatRepository repository;

    public Page<EmpresasServcat> findByAll(int page, int size) { return repository.getByAll(PageRequest.of(page, size));}

    /*
    public EmpresasServcat findById(Long id) {
        return repository.getReferenceById(id);
    }
     */

    /*
    public Optional<EmpresasServcat> findById(long id) {
        return repository.findById(id);
    }

     */


    @Transactional
    public ResponseEntity<Void> deleteEmpresasServcatByCageCode(String cageCode) {
        try {
            repository.deleteByCageCode(cageCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /*
    public void deleteEmpresasServcatByCageCode(String cageCode) {
        repository.deleteByCageCode(cageCode);
    }

     */


    public EmpresasServcat findByCageCodeId (String cageCode) {
    return repository.getByCageCodeId(cageCode);}

    public Page<EmpresasServcat> findByCageCountry(String cageCountry, int page, int size) {
        return repository.getByCageCountry(cageCountry, PageRequest.of(page, size));
    }

    public Page<EmpresasServcat> getByCageCompanyName(String cageCompanyName, int page, int size) {
        long count = cageCompanyName.chars().filter(ch -> ch == '*').count();

        if (count > 0) {
            cageCompanyName = cageCompanyName.replace("*", "%");
        } else {
            cageCompanyName = "%" + cageCompanyName + "%";
        }
        return repository.getByCageCompanyName(cageCompanyName, PageRequest.of(page, size));
    }

    public EmpresasServcat createSupplier(EmpresasServcat empresasServcat) {
        return repository.save(empresasServcat);
    }



}
