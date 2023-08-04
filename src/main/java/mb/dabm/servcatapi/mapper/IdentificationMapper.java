package mb.dabm.servcatapi.mapper;

import java.util.List;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.model.IdentificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface IdentificationMapper {
    IdentificationDto entityToDTO(Identification identification);

    @Mapping(target = "codGen", ignore = true)
    Identification dtoToEntity(IdentificationDto identification);

    List<IdentificationDto> entityToDTO(
        Iterable<Identification> identification
    );

    List<Identification> dtoToEntity(
        Iterable<IdentificationDto> identifications
    );

    default Page<IdentificationDto> toRest(Page<Identification> page) {
        return page.map(this::entityToDTO);
    }
}
