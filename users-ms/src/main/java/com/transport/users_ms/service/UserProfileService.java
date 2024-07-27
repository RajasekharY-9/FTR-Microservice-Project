package com.transport.users_ms.service;


import com.transport.users_ms.dto.UserProfileDTO;
import com.transport.users_ms.entity.UserProfile;
import com.transport.users_ms.entity.WorkitemDTO;
import com.transport.users_ms.exception.UserProfileException;
import com.transport.users_ms.repo.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;



public interface UserProfileService {
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) throws UserProfileException;
    public UserProfileDTO updateUserProfile(Long userId, UserProfileDTO userProfileDTO) throws UserProfileException;
    public UserProfileDTO viewUserProfile(Long userId) throws UserProfileException;
    public void deleteUserProfile(Long userId) throws UserProfileException;

    WorkitemDTO createWorkItem(WorkitemDTO workitemDTO);

    void updateWorkItem(String workitemId,WorkitemDTO workitemDTO);
}
