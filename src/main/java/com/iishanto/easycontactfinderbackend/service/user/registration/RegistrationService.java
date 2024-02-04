package com.iishanto.easycontactfinderbackend.service.user.registration;

import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    UserDto registerGoogle(UserRegistrationInfoDto userDto, UserCredentialDto userCredentialDto) throws RegistrationFailureException;
    UserDto registerEmailUser(UserRegistrationInfoDto userDto) throws RegistrationFailureException;
}
