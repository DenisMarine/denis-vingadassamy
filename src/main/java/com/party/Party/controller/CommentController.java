package com.party.Party.controller;

import com.party.Party.dto.CommentCreationDto;
import com.party.Party.dto.CommentDto;
import com.party.Party.dto.CommentUpdateDto;
import com.party.Party.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Transactional
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentCreationDto commentCreationDto) {
        return ResponseEntity.ok(commentService.addComment(commentCreationDto));
    }

    @GetMapping("/profile/{profileId}/commented")
    public ResponseEntity<List<CommentDto>> getAllCommentsOfProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok(commentService.getAllCommentsOfProfile(profileId));
    }

    @GetMapping("/profile/{profileId}/written")
    public ResponseEntity<List<CommentDto>> getAllCommentsWrittenBy(@PathVariable Long profileId) {
        return ResponseEntity.ok(commentService.getAllCommentsWrittenBy(profileId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto commentUpdateDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, commentUpdateDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
