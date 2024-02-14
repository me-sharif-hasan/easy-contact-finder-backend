package com.iishanto.easycontactfinderbackend.service.phone;

import com.iishanto.easycontactfinderbackend.dto.request.ContactAliasDto;
import com.iishanto.easycontactfinderbackend.model.Phone;
import com.iishanto.easycontactfinderbackend.model.PhoneAlias;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.repository.PhoneAliasRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public List<PhoneAlias> savePhoneAliases(List<ContactAliasDto> contactAliasDtos, User user) throws Exception{
        try{
            List <PhoneAlias> output=new ArrayList<>();
            for (ContactAliasDto contactAliasDto:contactAliasDtos){
                PhoneAlias phoneAlias=modelMapper.map(contactAliasDto,PhoneAlias.class);
                try{
                    Phone phone=modelMapper.map(contactAliasDto.getAliasTarget(),Phone.class);
                    phoneAlias.setAliasTarget(phone);
                }catch (Exception e){
                    //do nothing
                }
                phoneAlias.setAliasOwner(user);
                output.add(phoneAlias);
                phoneAliasRepository.save(phoneAlias);
            }
            return output;
        }catch (Exception e){
            throw e;
        }
    }

    public List<PhoneAlias> findByOwner(User user) {
        return phoneAliasRepository.findAllByAliasOwner(user);
    }
}
