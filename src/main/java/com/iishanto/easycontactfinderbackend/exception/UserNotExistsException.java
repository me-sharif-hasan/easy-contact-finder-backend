package com.iishanto.easycontactfinderbackend.exception;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserNotExistsException extends Throwable {
    private UserDto userDto;
}
