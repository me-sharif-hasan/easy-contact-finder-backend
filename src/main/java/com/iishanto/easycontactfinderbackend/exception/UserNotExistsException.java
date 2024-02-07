package com.iishanto.easycontactfinderbackend.exception;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class UserNotExistsException extends Exception {
    public UserNotExistsException(String message){
        super(message);
    }
    private UserRegistrationInfoDto userRegistrationInfoDto;
}
