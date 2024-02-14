package com.iishanto.easycontactfinderbackend.service.phone;

import com.iishanto.easycontactfinderbackend.model.PhoneVerification;
import org.springframework.stereotype.Service;

@Service
public interface SmsVerificationService {
    PhoneVerification sendAndGetVerificationCode(String phone) throws Exception;
}
