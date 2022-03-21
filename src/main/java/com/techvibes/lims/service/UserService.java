package com.techvibes.lims.service;

import com.techvibes.lims.dto.UserRegistrationDto;
import com.techvibes.lims.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
