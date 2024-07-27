package com.transport.users_ms.service;

import com.transport.users_ms.dto.UserProfileDTO;
import com.transport.users_ms.entity.UserProfile;
import com.transport.users_ms.exception.UserProfileException;
import com.transport.users_ms.repo.UserProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) throws UserProfileException {
        UserProfile userProfile = mapToEntity(userProfileDTO);

            UserProfile savedUserProfile = userProfileRepository.save(userProfile);

        return mapToDTO(savedUserProfile);
    }
    @Override
    public UserProfileDTO updateUserProfile(Long userId, UserProfileDTO userProfileDTO) throws UserProfileException{
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userId);
        if(optionalUserProfile.isEmpty()){
            throw new UserProfileException("SERVICE.User_Not_Exists");
        }
        if (optionalUserProfile.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            userProfile.setFirstName(userProfileDTO.getFirstName());
            userProfile.setLastName(userProfileDTO.getLastName());
            userProfile.setEmailId(userProfileDTO.getEmailId());
            userProfile.setMobileNumber(userProfileDTO.getMobileNumber());
            userProfile.setPassword(userProfileDTO.getPassword());
            userProfile.setNationality(userProfileDTO.getNationality());
            userProfile.setPassportNumber(userProfileDTO.getPassportNumber());
            userProfile.setPermanentAddress(userProfileDTO.getPermanentAddress());
            userProfile.setOfficeAddress(userProfileDTO.getOfficeAddress());
            userProfile.setPersonalIdentificationNumber(userProfileDTO.getPersonalIdentificationNumber());
            UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
            return mapToDTO(updatedUserProfile);
        }
        return null;
    }
    @Override
    public UserProfileDTO viewUserProfile(Long userId) throws UserProfileException{
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
       if(userProfile.isEmpty()){
           throw new UserProfileException("SERVICE.User_Not_Exists");
       }else{
           return mapToDTO(userProfile.get());
       }
    }
    @Override
    public void deleteUserProfile(Long userId) throws UserProfileException{
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if(userProfile.isEmpty()){
            throw new UserProfileException("SERVICE.User_Not_Exists");
        }
        userProfileRepository.deleteById(userId);
    }

    private UserProfile mapToEntity(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userProfileDTO.getUserId());
        userProfile.setFirstName(userProfileDTO.getFirstName());
        userProfile.setLastName(userProfileDTO.getLastName());
        userProfile.setEmailId(userProfileDTO.getEmailId());
        userProfile.setMobileNumber(userProfileDTO.getMobileNumber());
        userProfile.setPassword(userProfileDTO.getPassword());
        userProfile.setNationality(userProfileDTO.getNationality());
        userProfile.setPassportNumber(userProfileDTO.getPassportNumber());
        userProfile.setPermanentAddress(userProfileDTO.getPermanentAddress());
        userProfile.setOfficeAddress(userProfileDTO.getOfficeAddress());
        userProfile.setPersonalIdentificationNumber(userProfileDTO.getPersonalIdentificationNumber());
        return userProfile;
    }

    private UserProfileDTO mapToDTO(UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUserId(userProfile.getUserId());
        userProfileDTO.setFirstName(userProfile.getFirstName());
        userProfileDTO.setLastName(userProfile.getLastName());
        userProfileDTO.setEmailId(userProfile.getEmailId());
        userProfileDTO.setMobileNumber(userProfile.getMobileNumber());
        userProfileDTO.setPassword(userProfile.getPassword());
        userProfileDTO.setNationality(userProfile.getNationality());
        userProfileDTO.setPassportNumber(userProfile.getPassportNumber());
        userProfileDTO.setPermanentAddress(userProfile.getPermanentAddress());
        userProfileDTO.setOfficeAddress(userProfile.getOfficeAddress());
        userProfileDTO.setPersonalIdentificationNumber(userProfile.getPersonalIdentificationNumber());
        return userProfileDTO;
    }
}
