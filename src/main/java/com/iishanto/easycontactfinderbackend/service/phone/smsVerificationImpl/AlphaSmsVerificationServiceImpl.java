package com.iishanto.easycontactfinderbackend.service.phone.smsVerificationImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iishanto.easycontactfinderbackend.model.PhoneVerification;
import com.iishanto.easycontactfinderbackend.service.phone.SmsVerificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;
@Component
public class AlphaSmsVerificationServiceImpl implements SmsVerificationService {
    String base;
    String key;
    String senderid;
    RestClient restClient;
    public AlphaSmsVerificationServiceImpl(@Value("${sms.alphasms.endpoint}") String base, @Value("${sms.alphasms.key}") String key, @Value("${sms.alphasms.senderid}") String senderid, RestClient restClient){
        this.base=base;
        this.key=key;
        this.restClient=restClient;
        this.senderid=senderid;
    }
    @Override
    public PhoneVerification sendAndGetVerificationCode(String phone) throws Exception{
        try{
            System.out.println("Sending verification code");
            String verificationCode= getCode();
            PhoneVerification phoneVerification=new PhoneVerification();
            phoneVerification.setCode(verificationCode);
            String response=restClient.get().uri(getRequest(phone,"Your contact buddy verification code is: "+verificationCode)).retrieve().body(String.class);
            JsonNode jsonNode=new ObjectMapper().convertValue(response,JsonNode.class);
            if(jsonNode.get("error_message")!=null) throw new Exception("Failure sending verification code");
            System.out.println("Code sent");
            return phoneVerification;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


    private String getCode(){
        return UUID.randomUUID().toString().substring(0,5);
    }

    private String getRequest(String phone,String message){
        UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(base).queryParam("api_key",key).queryParam("number",phone).queryParam("senderid",senderid).queryParam("message",message);
        return builder.build().toUriString();
    }
}
