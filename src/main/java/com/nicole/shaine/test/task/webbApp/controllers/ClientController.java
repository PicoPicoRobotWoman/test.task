package com.nicole.shaine.test.task.webbApp.controllers;

import com.nicole.shaine.test.task.corverter.ClientMapper;
import com.nicole.shaine.test.task.corverter.ContactMapper;
import com.nicole.shaine.test.task.models.Exceptions.impl.ClientNonExistException;
import com.nicole.shaine.test.task.models.Exceptions.impl.ContactExistException;
import com.nicole.shaine.test.task.models.Exceptions.impl.RequestBodyParamException;
import com.nicole.shaine.test.task.models.Exceptions.impl.RequestPathVariableException;
import com.nicole.shaine.test.task.models.dto.request.ClientRequestDto;
import com.nicole.shaine.test.task.models.dto.request.ContactRequestDto;
import com.nicole.shaine.test.task.models.dto.response.ClientResponseDto;

import com.nicole.shaine.test.task.models.dto.response.ContactResponseDto;
import com.nicole.shaine.test.task.models.entitys.Contact;
import com.nicole.shaine.test.task.models.enums.ContactType;
import com.nicole.shaine.test.task.service.abs.ClientService;
import com.nicole.shaine.test.task.service.abs.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;
    private final ContactService contactService;

    private final ClientMapper clientMapper;
    private final ContactMapper contactMapper;

    @Autowired
    public ClientController(ClientService clientService,
                            ContactService contactService,
                            ClientMapper clientMapper,
                            ContactMapper contactMapper) {

        this.clientService = clientService;
        this.contactService = contactService;

        this.clientMapper = clientMapper;
        this.contactMapper = contactMapper;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Set<ClientResponseDto> getClients() {

        return clientService.getAll().stream().map(clientMapper::entityToDto).collect(Collectors.toSet());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponseDto getClient(@PathVariable(value = "id") Long id) {

        if(!clientService.isExistById(id)) {
            throw new ClientNonExistException(String.format("не существует пользователя с id: %s!", id));
        }

        return clientMapper.entityToDto(clientService.getById(id).get());

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientRequestDto clientRequestDto) {

        clientService.create(clientMapper.DtoToCEntity(clientRequestDto));

    }

    @PostMapping("/{id}/contact")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@PathVariable(value = "id") Long id,
                             @RequestBody ContactRequestDto contactRequestDto) {

        boolean isCorrectId = id == null || !clientService.isExistById(id);
        if (isCorrectId) {
            throw new RequestPathVariableException(String.format("не корректно указан id: %s!", id));
        }

        if (contactRequestDto.getContactType() == null) {
            throw new RequestBodyParamException(String.format("не корректно указан contactType: %s!", contactRequestDto.getContactType()));
        }

        if (contactService.isExistByValues(Stream.of(contactRequestDto.getValue()).collect(Collectors.toSet()))) {
            throw new ContactExistException(String.format("контакт уже существует: %s!", contactRequestDto.getValue()));
        }

        Contact contact = contactMapper.DtoToContact(contactRequestDto);
        contact.setClient(clientService.getById(id).get());
        contactService.create(contact);

    }

    @GetMapping("/{id}/contact")
    @ResponseStatus(HttpStatus.OK)
    public Set<ContactResponseDto> getContacts(@PathVariable(value = "id") Long id,
                                               @RequestParam(name = "contactType", required = false) ContactType contactType) {

        boolean isCorrectId = id == null || !clientService.isExistById(id);
        if (isCorrectId) {
            throw new RequestPathVariableException(String.format("не корректно указан id: %s!", id));
        }

        return contactService
            .getByClientIdAndContactType(id, contactType)
            .stream().map(contactMapper::contactToDto)
            .collect(Collectors.toSet());

    }



}
