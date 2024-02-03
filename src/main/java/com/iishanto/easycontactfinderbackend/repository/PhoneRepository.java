package com.iishanto.easycontactfinderbackend.repository;

import com.iishanto.easycontactfinderbackend.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone,Long> {
}
