package com.party.Party.repository;

import com.party.Party.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.commentedProfile LEFT JOIN FETCH c.writtenBy WHERE c.id = :commentId")
    Optional<Comment> findCommentById(Long commentId);

    @Query("SELECT c FROM Comment c")
    List<Comment> findAllComments();

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.commentedProfile cp WHERE cp.id = :profileId")
    List<Comment> findCommentByCommentedProfileId(Long profileId);

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.writtenBy wb WHERE wb.id = :profileId")
    List<Comment> findCommentByWrittenByProfileId(Long profileId);
}
