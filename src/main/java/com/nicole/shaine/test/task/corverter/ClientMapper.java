package com.nicole.shaine.test.task.corverter;

import com.nicole.shaine.test.task.models.dto.request.ClientRequestDto;
import com.nicole.shaine.test.task.models.dto.response.ClientResponseDto;
import com.nicole.shaine.test.task.models.entitys.Client;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "contacts", target = "contacts")
    ClientResponseDto entityToDto(Client entity);

    Client DtoToCEntity(ClientRequestDto clientRequestDto);


}
