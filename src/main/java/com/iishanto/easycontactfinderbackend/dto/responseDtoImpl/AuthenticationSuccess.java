package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationSuccess extends Success{
    private UserDto userDto;
    private String token;
    public AuthenticationSuccess(String msg){
        super(msg);
    }
    @Override
    public void setMessage(Object message) {
        super.setMessage(message);
    }
}
