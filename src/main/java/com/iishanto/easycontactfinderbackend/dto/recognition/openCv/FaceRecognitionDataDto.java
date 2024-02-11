package com.iishanto.easycontactfinderbackend.dto.recognition.openCv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iishanto.easycontactfinderbackend.dto.image.Base64ImageDto;
import com.iishanto.easycontactfinderbackend.utility.Base64ImageSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaceRecognitionDataDto {
    /**
     * {
     *   "collections": [
     *     "string"
     *   ],
     *   "date_of_birth": "2024-02-07",
     *   "gender": "M",
     *   "id": "string",
     *   "images": [
     *     "base64_encoded_string"
     *   ],
     *   "is_bulk_insert": false,
     *   "name": "string",
     *   "nationality": "string",
     *   "notes": "string"
     * }
     */

    private List <String> collections=new ArrayList<>();
    private String date_of_birth="2000-02-07";
    private String gender="M";
    private String id;
    @JsonSerialize(using = Base64ImageSerializer.class)
    private List<Base64ImageDto> images;
    private String is_bulk_insert="false";
    private String name;
    private String nationality="Bangladeshi";
    private String notes="App user";
    private String search_mode="ACCURATE";
}
