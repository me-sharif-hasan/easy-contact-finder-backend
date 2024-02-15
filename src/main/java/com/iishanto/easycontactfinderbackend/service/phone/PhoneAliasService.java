package com.iishanto.easycontactfinderbackend.service.phone;

import com.iishanto.easycontactfinderbackend.dto.phone.alias.PhoneAliasDto;
import com.iishanto.easycontactfinderbackend.dto.request.ContactAliasCollectionDto;
import com.iishanto.easycontactfinderbackend.dto.request.ContactAliasDto;
import com.iishanto.easycontactfinderbackend.model.Phone;
import com.iishanto.easycontactfinderbackend.model.PhoneAlias;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.repository.PhoneAliasRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PhoneAliasService {
    private PhoneAliasRepository phoneAliasRepository;
    ModelMapper modelMapper;
    public List<PhoneAlias> savePhoneAliases(ContactAliasCollectionDto aliasCollectionDto, User owner) throws Exception{
        try{
            User person=modelMapper.map(aliasCollectionDto.getContact(),User.class);
            if (person==null||person.getId()==null) throw new Exception("Invalid friend id");
            List <PhoneAlias> phoneAliases=new ArrayList<>();
            for (PhoneAliasDto phoneAliasDto:aliasCollectionDto.getAliases()){
                PhoneAlias phoneAlias=modelMapper.map(phoneAliasDto,PhoneAlias.class);
                try {
                    phoneAlias.setPerson(person);
                    phoneAlias.setAliasOwner(owner);
                    Phone phone=null;
                    if (phoneAliasDto.getAliasTarget()!=null) phone=modelMapper.map(phoneAliasDto.getAliasTarget(),Phone.class);
                    phoneAlias.setAliasTarget(phone);
                    phoneAliasRepository.save(phoneAlias);
                    phoneAliases.add(phoneAlias);
                }catch (Exception e){
                    //do nothing
                }
            }
            return phoneAliases;
        }catch (Exception e){
            throw e;
        }
    }

    public List<PhoneAlias> findByOwner(User user) {
        return phoneAliasRepository.findAllByAliasOwner(user);
    }
}
