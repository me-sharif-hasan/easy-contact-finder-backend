package com.iishanto.easycontactfinderbackend.service.user.phone;

import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationCodeSendSuccessfulResponseDto;
import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationRequestReceiverDto;
import com.iishanto.easycontactfinderbackend.model.Phone;
import com.iishanto.easycontactfinderbackend.model.PhoneVerification;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.repository.PhoneRepository;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PhoneService {
    private PhoneRepository phoneRepository;
    private UserRepository userRepository;
    public PhoneVerificationCodeSendSuccessfulResponseDto sendVerificationCode(PhoneVerificationRequestReceiverDto phoneVerificationRequestReceiverDto){
        String phoneNumber=phoneVerificationRequestReceiverDto.getPhone();
        Optional<Phone> phone=phoneRepository.findPhoneByNumber(phoneNumber);
        Phone authPhone=null;
        if(phone.isEmpty()){
            System.out.println("Phone does not exists");
            //attach with current user
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user==null) throw new InvalidRequestStateException("Invalid request, authentication failed");
            Phone newPhone=new Phone();
            newPhone.setNumber(phoneNumber);
            //retrieve other info if needed
            phoneRepository.save(newPhone);
            authPhone=newPhone;
            user.getPhones().add(newPhone);
            userRepository.save(user);
        }else {
            System.out.println("Phone already exists");
            authPhone=phone.get();
        }
        String verificationCode= "12345";//send it using a service will be implemented latter
        PhoneVerification phoneVerification=new PhoneVerification();
        phoneVerification.setCode(verificationCode);
        authPhone.setPhoneVerification(phoneVerification);
        authPhone.getPhoneVerification().setStatus("unverified");
        phoneRepository.save(authPhone);
        return new PhoneVerificationCodeSendSuccessfulResponseDto("success","A code is send in your phone number");
    }
}
