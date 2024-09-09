package com.transport.users_ms.controller;


import com.transport.users_ms.dto.UserProfileDTO;
import com.transport.users_ms.entity.WorkitemDTO;
import com.transport.users_ms.exception.UserProfileException;
import com.transport.users_ms.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public UserProfileDTO createUserProfile(@RequestBody UserProfileDTO userProfileDTO) throws UserProfileException {
        return userProfileService.createUserProfile(userProfileDTO);
    }
    @PostMapping("/user-newitem")
    //http://localhost:8080/api/user-profile/user-newitem
    public WorkitemDTO createWorkItem(@RequestBody WorkitemDTO workitemDTO) throws UserProfileException {
        return userProfileService.createWorkItem(workitemDTO);
    }
    @PutMapping("/user-update-item/{workitemId}")
    //http://localhost:8080/api/user-profile/user-update-item/{workitemId}
    public String updateWorkItem(@PathVariable String workitemId, @RequestBody WorkitemDTO workitemDTO) throws UserProfileException {
       return  userProfileService.updateWorkItem(workitemId,workitemDTO);
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
    @GetMapping("/all-harbors")
    //
    public List<String> getAvailableHarborLocations(){
     return    userProfileService.getAvailableHarborLocations();
    }
}
