package com.iishanto.easycontactfinderbackend.dto.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaceDetectDto {
    private String image;
}
