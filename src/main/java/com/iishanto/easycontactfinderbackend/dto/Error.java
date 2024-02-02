package com.iishanto.easycontactfinderbackend.dto;


public abstract class Error extends ResponseDto{
    public Error(String message){
        setMessage(message);
    }
    public Error(){
        setStatus("error");
    }
}
