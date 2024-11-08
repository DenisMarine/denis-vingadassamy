package com.party.Party.controller;

import com.party.Party.dto.BringItemDto;
import com.party.Party.service.BringItemService;
import com.party.Party.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bring-items")
@RequiredArgsConstructor
public class BringItemController {

    private final BringItemService bringItemService;

    @GetMapping("/")
    public ResponseEntity<List<BringItemDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bringItemService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BringItemDto> findById(@PathVariable Long id) {
        BringItemDto bringItemDto = bringItemService.findById(id);
        return bringItemDto != null ? ResponseEntity.ok(bringItemDto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BringItemDto> save(@RequestBody BringItemDto bringItemDto) {
        BringItemDto createdBringItem = bringItemService.save(bringItemDto);
        return ResponseEntity.ok(createdBringItem);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<BringItemDto> update(@PathVariable Long id, @RequestBody BringItemDto bringItemDto) {
        BringItemDto updatedBringItem = bringItemService.update(id, bringItemDto);
        return updatedBringItem != null ? ResponseEntity.ok(updatedBringItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bringItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
