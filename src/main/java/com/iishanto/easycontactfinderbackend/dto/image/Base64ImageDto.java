package com.iishanto.easycontactfinderbackend.dto.image;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;


@AllArgsConstructor
@Data
public class Base64ImageDto {
    private String base_64_encoded_string;
}
