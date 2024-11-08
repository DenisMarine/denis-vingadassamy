package com.party.Party.controller;

import com.party.Party.dto.CommentDto;
import com.party.Party.dto.ProfileDto;
import com.party.Party.dto.ProfileUpdateDto;
import com.party.Party.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok(profileService.getById(profileId));
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles(@RequestParam(name = "page") int page,
                                                           @RequestParam(name = "page-size") int pageSize) {
        return ResponseEntity.ok(profileService.getAllProfiles(page, pageSize));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable Long profileId,
                                                    @RequestBody ProfileUpdateDto profileUpdateDto) {
        return ResponseEntity.ok(profileService.updateProfile(profileId, profileUpdateDto));
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
        profileService.deleteProfile(profileId);
        return ResponseEntity.noContent().build();
    }
}
