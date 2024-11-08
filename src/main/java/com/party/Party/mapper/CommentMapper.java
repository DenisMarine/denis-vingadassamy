package com.party.Party.mapper;

import com.party.Party.dto.CommentDto;
import com.party.Party.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);
    List<CommentDto> toDtos(List<Comment> comments);
    List<Comment> toEntities(List<CommentDto> commentDtos);
}
