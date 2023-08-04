package mb.dabm.servcatapi.repository;

import mb.dabm.servcatapi.entity.Identification;
import org.springframework.data.jpa.repository.JpaRepository;

// import org.springframework.data.repository.CrudRepository;

public interface IdentificationRepository
    extends JpaRepository<Identification, Long> {}
