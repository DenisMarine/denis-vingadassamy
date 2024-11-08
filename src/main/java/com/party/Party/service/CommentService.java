package com.party.Party.service;

import com.party.Party.dto.CommentCreationDto;
import com.party.Party.dto.CommentDto;
import com.party.Party.dto.CommentUpdateDto;
import com.party.Party.dto.ProfileDto;
import com.party.Party.entity.Comment;
import com.party.Party.entity.Profile;
import com.party.Party.mapper.CommentMapper;
import com.party.Party.mapper.ProfileMapper;
import com.party.Party.repository.CommentRepository;
import com.party.Party.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProfileRepository profileRepository;
    private final CommentMapper commentMapper;
    private final ProfileMapper profileMapper;

    public CommentDto addComment(CommentCreationDto commentCreationDto) {
        ProfileDto writtenByDto = profileMapper.toDto(getProfile(commentCreationDto.getAuthorId()));
        ProfileDto profileCommentedDto = profileMapper.toDto(getProfile(commentCreationDto.getCommentedProfileId()));

        OffsetDateTime now = OffsetDateTime.now();

        CommentDto commentDto = new CommentDto();
        commentDto.setText(commentCreationDto.getText());
        commentDto.setRating(commentCreationDto.getRating());
        commentDto.setCreationDate(now);
        commentDto.setCommentedProfile(profileCommentedDto);
        commentDto.setWrittenBy(writtenByDto);

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setUpdateDate(now);

        return commentMapper.toDto(commentRepository.save(comment));
    }

    public List<CommentDto> getAllCommentsOfProfile(Long profileId) {
        getProfile(profileId);
        return commentRepository.findCommentByCommentedProfileId(profileId)
                .stream().map(commentMapper::toDto).toList();
    }

    public List<CommentDto> getAllCommentsWrittenBy(Long profileId) {
        getProfile(profileId);
        return commentRepository.findCommentByWrittenByProfileId(profileId)
                .stream().map(commentMapper::toDto).toList();
    }

    public CommentDto updateComment(Long commentId, CommentUpdateDto commentUpdateDto) {
        Comment comment = getComment(commentId);
        if (Objects.nonNull(commentUpdateDto.getText())) {
            comment.setText(commentUpdateDto.getText());
        }
        if (commentUpdateDto.getRating() > 0 && commentUpdateDto.getRating() <=5) {
            comment.setRating(commentUpdateDto.getRating());
        }
        comment.setUpdateDate(OffsetDateTime.now());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public void deleteComment(Long commentId) {
        getComment(commentId);
        commentRepository.deleteById(commentId);
    }

    private Profile getProfile(Long profileId) {
        return profileRepository.findProfileById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile with id " + profileId + " not found"));
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findCommentById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment with id " + commentId + " not found"));
    }
}
