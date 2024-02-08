package com.iishanto.easycontactfinderbackend.dto.server.success;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iishanto.easycontactfinderbackend.dto.server.ResponseDto;
import lombok.ToString;

@ToString(callSuper = true)
public class ServerSuccessResponseDto extends ResponseDto {
    public ServerSuccessResponseDto(){
        setStatus("success");
    }
}
