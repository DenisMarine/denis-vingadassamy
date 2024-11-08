package com.party.Party.controller;

import com.party.Party.dto.UserCreateDto;
import com.party.Party.dto.UserDto;
import com.party.Party.service.ProfileService;
import com.party.Party.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileService profileService;

    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> save(@RequestBody UserCreateDto userCreateDto) {
        String hashedPassword = passwordEncoder.encode(userCreateDto.getUserDto().getPassword());
        userCreateDto.getUserDto().setPassword(hashedPassword);
        UserDto userDtoSaved = userService.save(userCreateDto.getUserDto());
        profileService.createProfile(userCreateDto.getProfileCreateDto(), userDtoSaved);
        return ResponseEntity.ok(userDtoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
