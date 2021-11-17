package de.db.webapp.controllers.mappers;

import de.db.webapp.controllers.dtos.SchweinDTO;
import de.db.webapp.repositories.entities.SchweinEntity;
import de.db.webapp.services.models.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDTOMapper {

    Schwein convert(SchweinDTO dto);
    SchweinDTO convert(Schwein schwein);
    Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
}
