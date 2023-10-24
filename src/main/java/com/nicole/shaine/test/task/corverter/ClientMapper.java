package com.nicole.shaine.test.task.corverter;

import com.nicole.shaine.test.task.models.dto.response.ClientResponseDto;
import com.nicole.shaine.test.task.models.entitys.Client;

import com.nicole.shaine.test.task.models.entitys.Email;
import com.nicole.shaine.test.task.models.entitys.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "emails", target = "emails", qualifiedByName = "mapEmails")
    @Mapping(source = "phones", target = "phones", qualifiedByName = "mapPhones")
    ClientResponseDto entityToDto(Client entity);

    @Named("mapEmails")
    default Set<String> mapEmails(Set<Email> emails) {
        return emails.stream()
            .map(Email::getAddress)
            .collect(Collectors.toSet());
    }

    @Named("mapPhones")
    default Set<String> mapPhones(Set<Phone> phones) {
        return phones.stream()
            .map(Phone::getValue)
            .collect(Collectors.toSet());
    }

}
