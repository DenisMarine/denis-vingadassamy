package com.party.Party.service;

import com.party.Party.dto.ItemDto;
import com.party.Party.entity.Item;
import com.party.Party.mapper.ItemMapper;
import com.party.Party.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ItemDto> findAll(Pageable pageable) {
        return itemMapper.toDtos(itemRepository.findAll(pageable).getContent());
    }

    public ItemDto findById(Long id) {
        return itemMapper.toDto(itemRepository.findById(id).orElse(null));
    }

    public ItemDto save(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        return itemMapper.toDto(itemRepository.save(item));
    }

    public ItemDto update(Long id, ItemDto itemDto) {
        Item updatedItem = itemMapper.toEntity(itemDto);
        Item existingItem = itemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            updatedItem.setId(existingItem.getId());
            return itemMapper.toDto(itemRepository.save(updatedItem));
        }
        return null;
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
