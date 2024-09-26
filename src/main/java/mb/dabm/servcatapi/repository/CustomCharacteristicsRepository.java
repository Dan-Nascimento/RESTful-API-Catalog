package mb.dabm.servcatapi.repository;

import mb.dabm.servcatapi.entity.Characteristics;

public interface CustomCharacteristicsRepository {
    int saveUsingJdbcTemplate(Characteristics characteristics);
}
