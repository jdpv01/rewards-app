package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.web.dto.PublicationDTO;
import co.eficacia.com.rewardsapp.persistance.model.Publication;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    Publication fromPublicationDTO(PublicationDTO publicationDTO);

    Publication fromPublicationDTO(UUID id, PublicationDTO publicationDTO);

    PublicationDTO fromPublication(Publication publication);
}