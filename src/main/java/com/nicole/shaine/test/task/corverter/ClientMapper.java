package com.nicole.shaine.test.task.corverter;

import com.nicole.shaine.test.task.models.dto.request.ClientRequestDto;
import com.nicole.shaine.test.task.models.dto.response.ClientResponseDto;
import com.nicole.shaine.test.task.models.entitys.Client;

import com.nicole.shaine.test.task.models.entitys.Email;
import com.nicole.shaine.test.task.models.entitys.Phone;
import com.nicole.shaine.test.task.models.enums.Gender;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "emails", target = "emails", qualifiedByName = "mapEmails")
    @Mapping(source = "phones", target = "phones", qualifiedByName = "mapPhones")
    ClientResponseDto entityToDto(Client entity);

    @Mapping(source = "emails", target = "emails", qualifiedByName = "mapStrEmails")
    @Mapping(source = "phones", target = "phones", qualifiedByName = "mapStrPhones")
    Client DtoToCEntity(ClientRequestDto clientRequestDto);

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

    @Named("mapStrEmails")
    default Set<Email> mapStrEmails(Set<String> emails) {
        return emails.stream()
            .map(str -> {
                Email email = new Email();
                email.setAddress(str);
                return email;
            })
            .collect(Collectors.toSet());
    }

    @Named("mapStrPhones")
    default Set<Phone> mapStrPhones(Set<String> phones) {
        return phones.stream()
            .map(str -> {
                Phone phone = new Phone();
                phone.setValue(str);
                return phone;
            })
            .collect(Collectors.toSet());
    }

}
