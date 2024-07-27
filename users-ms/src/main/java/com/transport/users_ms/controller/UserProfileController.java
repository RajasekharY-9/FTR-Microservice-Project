package com.transport.users_ms.controller;


import com.transport.users_ms.dto.UserProfileDTO;
import com.transport.users_ms.exception.UserProfileException;
import com.transport.users_ms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public UserProfileDTO createUserProfile(@RequestBody UserProfileDTO userProfileDTO) throws UserProfileException {
        return userProfileService.createUserProfile(userProfileDTO);
    }

    @PutMapping("/{userId}")
    public UserProfileDTO updateUserProfile(@PathVariable Long userId, @RequestBody UserProfileDTO userProfileDTO)throws UserProfileException {
        return userProfileService.updateUserProfile(userId, userProfileDTO);
    }

    @GetMapping("/{userId}")
    public UserProfileDTO viewUserProfile(@PathVariable Long userId) throws UserProfileException{
        return userProfileService.viewUserProfile(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserProfile(@PathVariable Long userId)throws UserProfileException {
        userProfileService.deleteUserProfile(userId);
    }
}
