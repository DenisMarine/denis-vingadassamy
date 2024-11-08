package com.party.Party.controller;

import com.party.Party.dto.ParticipantDto;
import com.party.Party.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping("/")
    public ResponseEntity<List<ParticipantDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(participantService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> findById(@PathVariable Long id) {
        ParticipantDto participantDto = participantService.findById(id);
        return participantDto != null ? ResponseEntity.ok(participantDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> save(@RequestBody ParticipantDto participantDto) {
        ParticipantDto createdParticipant = participantService.save(participantDto);
        return ResponseEntity.ok(createdParticipant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> update(@PathVariable Long id, @RequestBody ParticipantDto participantDto) {
        ParticipantDto updatedParticipant = participantService.update(id, participantDto);
        return updatedParticipant != null ? ResponseEntity.ok(updatedParticipant) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
