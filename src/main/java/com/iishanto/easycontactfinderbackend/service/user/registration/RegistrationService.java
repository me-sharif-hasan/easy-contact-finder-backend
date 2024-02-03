package com.iishanto.easycontactfinderbackend.service.user.registration;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    UserDto registerGoogle(UserDto userDto) throws RegistrationFailureException;
    UserDto registerEmailUser(UserDto userDto) throws RegistrationFailureException;
}
