package com.iishanto.easycontactfinderbackend.dto.server.error;

import com.iishanto.easycontactfinderbackend.dto.server.ResponseDto;
import lombok.ToString;

@ToString(callSuper = true)
public class ServerErrorResponseDto extends ResponseDto {
    public ServerErrorResponseDto(){
        setStatus("error");
    }
}
