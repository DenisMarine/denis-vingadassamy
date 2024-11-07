package com.party.Party.service;

import com.party.Party.dto.UserDto;
import com.party.Party.entity.User;
import com.party.Party.mapper.UserMapper;
import com.party.Party.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public List<UserDto> findAll(Pageable pageable){
        return userMapper.toDtos(userRepository.findAllUsers(pageable).getContent());
    }

    public UserDto findById(Long id){
        return userMapper.toDto(userRepository.findUserById(id).orElse(null));
    }

    public UserDto save(UserDto userDto){
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto update(Long id, UserDto userDto) {
        User updatedUser = userMapper.toEntity(userDto);
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null){
            updatedUser.setId(existingUser.getId());
            return userMapper.toDto(userRepository.save(updatedUser));
        }
        return null;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
