package com.iishanto.easycontactfinderbackend.repository;

import ch.qos.logback.classic.LoggerContext;
import com.iishanto.easycontactfinderbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long> {
    User findByEmail(String email);
}
