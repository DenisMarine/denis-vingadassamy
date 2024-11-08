package com.party.Party.service;

import com.party.Party.dto.BringItemDto;
import com.party.Party.entity.BringItem;
import com.party.Party.mapper.BringItemMapper;
import com.party.Party.repository.BringItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BringItemService {

    private final BringItemRepository bringItemRepository;
    private final BringItemMapper bringItemMapper;

    public List<BringItemDto> findAll(Pageable pageable) {
        return bringItemMapper.toDtos(bringItemRepository.findAll(pageable).getContent());
    }

    public BringItemDto findById(Long id) {
        return bringItemMapper.toDto(bringItemRepository.findById(id).orElse(null));
    }

    public BringItemDto save(BringItemDto bringItemDto) {
        BringItem bringItem = bringItemMapper.toEntity(bringItemDto);
        return bringItemMapper.toDto(bringItemRepository.save(bringItem));
    }

    public BringItemDto update(Long id, BringItemDto bringItemDto) {
        BringItem updatedBringItem = bringItemMapper.toEntity(bringItemDto);
        BringItem existingBringItem = bringItemRepository.findById(id).orElse(null);
        if (existingBringItem != null) {
            updatedBringItem.setId(existingBringItem.getId());
            return bringItemMapper.toDto(bringItemRepository.save(updatedBringItem));
        }
        return null;
    }

    public void delete(Long id) {
        bringItemRepository.deleteById(id);
    }
}
