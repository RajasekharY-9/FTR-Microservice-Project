package com.transport.users_ms.service;

import com.transport.users_ms.dto.UserProfileDTO;
import com.transport.users_ms.entity.UserProfile;
import com.transport.users_ms.entity.WorkitemDTO;
import com.transport.users_ms.exception.UserProfileException;
import com.transport.users_ms.repo.UserProfileRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, RestTemplate restTemplate) {
        this.userProfileRepository = userProfileRepository;
        this.restTemplate = restTemplate;
    }


    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) throws UserProfileException {
        UserProfile userProfile = mapToEntity(userProfileDTO);
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        return mapToDTO(savedUserProfile);
    }

    @Override
    public UserProfileDTO updateUserProfile(Long userId, UserProfileDTO userProfileDTO) throws UserProfileException {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userId);
        if (optionalUserProfile.isEmpty()) {
            throw new UserProfileException("SERVICE.User_Not_Exists");
        }

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

    @Override
    public UserProfileDTO viewUserProfile(Long userId) throws UserProfileException {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isEmpty()) {
            throw new UserProfileException("SERVICE.User_Not_Exists");
        }
        return mapToDTO(userProfile.get());
    }

    @Override
    public void deleteUserProfile(Long userId) throws UserProfileException {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isEmpty()) {
            throw new UserProfileException("SERVICE.User_Not_Exists");
        }
        userProfileRepository.deleteById(userId);
    }

    private static final String POST_WORK_ITEM_URL = "http://workitem-ms/ftr/workItems";
    private static final String UPDATE_WORK_ITEM_URL = "http://workitem-ms/ftr/workItems/{workitemId}";
    private static final String ALL_HARBORS_URL = "http://vehicle-ms/vehicles/harbor-all";

    @Override
    @CircuitBreaker(name = "createWorkItem", fallbackMethod = "fallbackCreateWorkItem")
   // @Retry(name = "default", fallbackMethod = "fallbackCreateWorkItem")
    // @RateLimiter(name = "default", fallbackMethod = "fallbackCreateWorkItem")
  //  @Bulkhead(name = "default", fallbackMethod = "fallbackCreateWorkItem")
   // @TimeLimiter(name = "default", fallbackMethod = "fallbackCreateWorkItem")
    public WorkitemDTO createWorkItem(WorkitemDTO workitemDTO) {
        return restTemplate.postForObject(POST_WORK_ITEM_URL, workitemDTO, WorkitemDTO.class);
    }

    public WorkitemDTO fallbackCreateWorkItem(Exception e) {
        // Log the error (optional)
        logger.error("Fallback for createWorkItem due to: {}", e.getMessage());
        // Return a default or empty response
        return new WorkitemDTO();
    }

    @Override
    @CircuitBreaker(name = "updateWorkItem", fallbackMethod = "fallbackUpdateWorkItem")
    //@Retry(name = "default", fallbackMethod = "fallbackUpdateWorkItem")
    //@RateLimiter(name = "default", fallbackMethod = "fallbackUpdateWorkItem")
    //@Bulkhead(name = "default", fallbackMethod = "fallbackUpdateWorkItem")
    //@TimeLimiter(name = "default", fallbackMethod = "fallbackUpdateWorkItem")
    public String updateWorkItem(String workitemId, WorkitemDTO workitemDTO) {
        if (workitemId == null || workitemId.isEmpty()) {
            throw new IllegalArgumentException("Workitem ID must not be null or empty");
        }
        String url = String.format("%s/%s", UPDATE_WORK_ITEM_URL, workitemId);
        restTemplate.put(url, workitemDTO);
        return "Details updated";
    }

    public String fallbackUpdateWorkItem(Exception t) {
        // Log the error (optional)
        logger.error("Fallback for updateWorkItem due to: {}", t.getMessage());
        // Return a fallback message or handle accordingly
        return "Oops!!! Please try after some time to update. Thanks for understanding.";
    }

    @Override
    @CircuitBreaker(name = "getAvailableHarborLocations", fallbackMethod = "fallbackGetHarborLocations")
    //@Retry(name = "default", fallbackMethod = "fallbackGetHarborLocations")
    //@RateLimiter(name = "default", fallbackMethod = "fallbackGetHarborLocations")
    //@Bulkhead(name = "default", fallbackMethod = "fallbackGetHarborLocations")
    //@TimeLimiter(name = "default", fallbackMethod = "fallbackGetHarborLocations")
    public List<String> getAvailableHarborLocations() {
        return restTemplate.getForObject(ALL_HARBORS_URL, List.class);
    }

    public List<String> fallbackGetHarborLocations(Exception t) {
        // Log the error (optional)
        logger.error("Fallback for getAvailableHarborLocations due to: {}", t.getMessage());
        // Return a default or fallback value
        return Arrays.asList("Chennai", "Machilipatnam");
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
