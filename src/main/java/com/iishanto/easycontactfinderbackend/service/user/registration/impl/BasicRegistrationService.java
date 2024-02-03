package com.iishanto.easycontactfinderbackend.service.user.registration.impl;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.iishanto.easycontactfinderbackend.service.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BasicRegistrationService implements RegistrationService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public UserDto registerGoogle(UserDto userDto) throws RegistrationFailureException {
        if(userRepository.findByEmail(userDto.getEmail())!=null) throw new RegistrationFailureException("User already exists. Please login!");
        User user=modelMapper.map(userDto,User.class);
        user.setUsedSocialLogin(true);
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto registerEmailUser(UserDto userDto) throws RegistrationFailureException {
        try{
            if(userDto.getEmail()==null) throw new RegistrationFailureException("You must provide an email");
            if(userRepository.findByEmail(userDto.getEmail())!=null){
                throw new RegistrationFailureException("Account already exists. Please login");
            }
            User user=modelMapper.map(userDto,User.class);
            userRepository.save(user);
            System.out.println(user);
            return modelMapper.map(user,UserDto.class);
        }catch (Throwable e){
            throw new RegistrationFailureException(e.getMessage());
        }
    }
}
