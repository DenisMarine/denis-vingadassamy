package com.party.Party.controller;

import com.party.Party.dto.PartyDto;
import com.party.Party.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/party")
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    @GetMapping("/")
    public ResponseEntity<List<PartyDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(partyService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDto> findById(@PathVariable Long id) {
        PartyDto partyDto = partyService.findById(id);
        return partyDto != null ? ResponseEntity.ok(partyDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PartyDto> save(@RequestBody PartyDto partyDto) {
        PartyDto createdParty = partyService.save(partyDto);
        return ResponseEntity.ok(createdParty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartyDto> update(@PathVariable Long id, @RequestBody PartyDto partyDto) {
        PartyDto updatedParty = partyService.update(id, partyDto);
        return updatedParty != null ? ResponseEntity.ok(updatedParty) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        partyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
