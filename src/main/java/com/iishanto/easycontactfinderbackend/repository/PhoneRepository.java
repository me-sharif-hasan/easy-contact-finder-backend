package com.iishanto.easycontactfinderbackend.repository;

import com.iishanto.easycontactfinderbackend.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone,Long> {
    Optional<Phone> findPhoneByNumber(String number);
}
