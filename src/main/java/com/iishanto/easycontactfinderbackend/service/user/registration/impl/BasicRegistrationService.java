package com.iishanto.easycontactfinderbackend.service.user.registration.impl;

import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.model.UserVerification;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.iishanto.easycontactfinderbackend.service.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BasicRegistrationService implements RegistrationService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto registerGoogle(UserRegistrationInfoDto userRegistrationInfoDto, UserCredentialDto userCredentialDto) throws RegistrationFailureException {
        if(userRepository.findByEmail(userCredentialDto.getEmail())!=null) throw new RegistrationFailureException("User already exists. Please login!");
        System.out.println(userRegistrationInfoDto);
        User user=modelMapper.map(userRegistrationInfoDto,User.class);
        user.setUsedSocialLogin(true);
        user.setPassword(passwordEncoder.encode("<no-pass-guaranteed>"));
        UserVerification userVerification=new UserVerification();
        userVerification.setState("verified");
        user.setUserVerification(userVerification);
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto registerEmailUser(UserRegistrationInfoDto userDto) throws RegistrationFailureException {
        try{
            if(userDto.getEmail()==null) throw new RegistrationFailureException("You must provide an email");
            if(userRepository.findByEmail(userDto.getEmail())!=null){
                throw new RegistrationFailureException("Account already exists. Please login");
            }
            User user=modelMapper.map(userDto,User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserVerification userVerification=new UserVerification();
            //TODO: user verification
            userVerification.setState("verified");
            user.setUserVerification(userVerification);
            userRepository.save(user);
            System.out.println(user);
            return modelMapper.map(user,UserDto.class);
        }catch (Throwable e){
            throw new RegistrationFailureException(e.getMessage());
        }
    }
}
