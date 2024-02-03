package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iishanto.easycontactfinderbackend.dto.ResponseDto;

public abstract class Success extends ResponseDto {
    public Success(String message){
        setMessage(message);
        setStatus("success");
    }
    public Success(){
        setStatus("success");
    }

    @Override
    public Object getMessage() {
        return super.getMessage();
    }
}
