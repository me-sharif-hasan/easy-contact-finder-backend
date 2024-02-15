package com.iishanto.easycontactfinderbackend.repository;

import com.iishanto.easycontactfinderbackend.model.PhoneAlias;
import com.iishanto.easycontactfinderbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneAliasRepository extends JpaRepository<PhoneAlias,Long> {
    List <PhoneAlias> findAllByAliasOwner(User user);
}
