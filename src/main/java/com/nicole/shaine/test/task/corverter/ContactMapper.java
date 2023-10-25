package com.nicole.shaine.test.task.corverter;

import com.nicole.shaine.test.task.models.dto.request.ContactRequestDto;
import com.nicole.shaine.test.task.models.dto.response.ContactResponseDto;
import com.nicole.shaine.test.task.models.entitys.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(source = "value", target = "value")
    Contact DtoToContact(ContactRequestDto contactRequestDto);

    ContactResponseDto contactToDto(Contact contact);

}
