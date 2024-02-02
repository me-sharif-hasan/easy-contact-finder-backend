package com.iishanto.easycontactfinderbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class ResponseDto implements Serializable {
    protected Object message;
    protected String status;
}
