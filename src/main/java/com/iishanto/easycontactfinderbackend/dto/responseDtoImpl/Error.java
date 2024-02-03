package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iishanto.easycontactfinderbackend.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Error extends ResponseDto{
    @JsonIgnore
    private Exception exception;
    public Error(String message){
        setMessage(message);
        setStatus("error");
    }
    public Error(){
        setStatus("error");
    }

    @Override
    public Object getMessage() {
        return super.getMessage()+(exception!=null?" with exception: "+exception.getLocalizedMessage():"");
    }
}
