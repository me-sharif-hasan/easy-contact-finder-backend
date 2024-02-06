package com.iishanto.easycontactfinderbackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class ResponseDto implements Serializable {
    protected Object message;
    protected String status;
    private Object data;
}
