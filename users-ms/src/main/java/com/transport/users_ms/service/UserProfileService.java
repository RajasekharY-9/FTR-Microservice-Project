package com.transport.users_ms.service;


import com.transport.users_ms.dto.UserProfileDTO;
import com.transport.users_ms.entity.WorkitemDTO;
import com.transport.users_ms.exception.UserProfileException;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserProfileService {
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) throws UserProfileException;
    public UserProfileDTO updateUserProfile(Long userId, UserProfileDTO userProfileDTO) throws UserProfileException;
    public UserProfileDTO viewUserProfile(Long userId) throws UserProfileException;
    public void deleteUserProfile(Long userId) throws UserProfileException;

    WorkitemDTO createWorkItem(WorkitemDTO workitemDTO);


    String updateWorkItem(String workitemId, WorkitemDTO workitemDTO);

    List<String> getAvailableHarborLocations();
}
