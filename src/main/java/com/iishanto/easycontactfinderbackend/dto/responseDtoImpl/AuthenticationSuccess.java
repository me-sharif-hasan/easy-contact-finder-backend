package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import com.iishanto.easycontactfinderbackend.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationSuccess extends Success{
    private String token;
    private User user;
    public AuthenticationSuccess(String msg){
        super(msg);
    }
    @Override
    public void setMessage(Object message) {
        super.setMessage(message);
    }
}
