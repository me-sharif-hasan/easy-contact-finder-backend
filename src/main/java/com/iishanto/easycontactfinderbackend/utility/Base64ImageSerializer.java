package com.iishanto.easycontactfinderbackend.utility;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iishanto.easycontactfinderbackend.dto.image.Base64ImageDto;

import java.io.IOException;
import java.util.List;

public class Base64ImageSerializer extends JsonSerializer<List<Base64ImageDto>> {
    @Override
    public void serialize(List<Base64ImageDto> base64ImageDtos, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Base64ImageDto base64ImageDto:base64ImageDtos){
            jsonGenerator.writeString(base64ImageDto.getBase_64_encoded_string());
        }
        jsonGenerator.writeEndArray();
    }
}
