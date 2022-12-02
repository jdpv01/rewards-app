package co.eficacia.com.rewardsapp.web.controller;

import co.eficacia.com.rewardsapp.mapper.CommentMapper;
import co.eficacia.com.rewardsapp.mapper.UserMapper;
import co.eficacia.com.rewardsapp.web.api.PublicationAPI;
import co.eficacia.com.rewardsapp.web.dto.CommentDTO;
import co.eficacia.com.rewardsapp.web.dto.PublicationDTO;
import co.eficacia.com.rewardsapp.mapper.PublicationMapper;
import co.eficacia.com.rewardsapp.service.PublicationService;
import co.eficacia.com.rewardsapp.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PublicationController implements PublicationAPI {

    private final PublicationService publicationService;

    private final PublicationMapper publicationMapper;

    private final CommentMapper commentMapper;

    private final UserMapper userMapper;

    @Override
    public PublicationDTO createPublication(PublicationDTO publicationDTO) {
        return publicationMapper.fromPublication(publicationService.createPublication(publicationMapper.fromPublicationDTO(publicationDTO)));
    }

    @Override
    public PublicationDTO getPublication(UUID id) {
        return publicationMapper.fromPublication(publicationService.getPublication(id));
    }

    @Override
    public List<PublicationDTO> getPublications() {
        return publicationService.getPublications().stream().map(publicationMapper::fromPublication).collect(Collectors.toList());
    }

    @Override
    public PublicationDTO updatePublication(UUID id, PublicationDTO PublicationDTO) {
        return publicationMapper.fromPublication(publicationService.updatePublication(publicationMapper.fromPublicationDTO(id, PublicationDTO)));
    }

    @Override
    public boolean deletePublication(UUID id) {
        return publicationService.deletePublication(id);
    }

    @Override
    public void addCommentToPublication(PublicationDTO publicationDTO, CommentDTO commentDTO, UserDTO userDTO) {
        publicationService.addCommentToPublication(publicationMapper.fromPublicationDTO(publicationDTO), commentMapper.fromCommentDTO(commentDTO), userMapper.fromUserDTO(userDTO));
    }

}
