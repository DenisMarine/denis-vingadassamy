package com.party.Party.controller;

import com.party.Party.dto.ProfileDto;
import com.party.Party.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}")
    public ProfileDto getProfile(@PathVariable Long profileId) {
        return profileService.getById(profileId);
    }
}
