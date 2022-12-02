package co.eficacia.com.rewardsapp.mapper;

import co.eficacia.com.rewardsapp.persistance.model.Comment;
import co.eficacia.com.rewardsapp.web.dto.CommentDTO;
import org.mapstruct.Mapper;

import java.util.UUID;
@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment fromCommentDTO(CommentDTO commentDTO);

    Comment fromCommentDTO(UUID id, CommentDTO commentDTO);

    CommentDTO fromComment(Comment user);

}
