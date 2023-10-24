package com.nicole.shaine.test.task.webbApp.controllers;

import com.nicole.shaine.test.task.corverter.ClientMapper;
import com.nicole.shaine.test.task.models.Exceptions.impl.EmailExistException;
import com.nicole.shaine.test.task.models.Exceptions.impl.PhoneExistException;
import com.nicole.shaine.test.task.models.dto.request.ClientRequestDto;
import com.nicole.shaine.test.task.models.dto.response.ClientResponseDto;

import com.nicole.shaine.test.task.models.enums.Gender;
import com.nicole.shaine.test.task.service.abs.ClientService;
import com.nicole.shaine.test.task.service.abs.EmailService;
import com.nicole.shaine.test.task.service.abs.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;
    private final PhoneService phoneService;
    private final EmailService emailService;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService,
                            PhoneService phoneService,
                            EmailService emailService,
                            ClientMapper clientMapper) {

        this.clientService = clientService;
        this.phoneService = phoneService;
        this.emailService = emailService;

        this.clientMapper = clientMapper;

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<ClientResponseDto> getClients() {

        return clientService.getAll().stream().map(clientMapper::entityToDto).collect(Collectors.toSet());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientRequestDto clientRequestDto) {

        if( phoneService.isExistByValues(clientRequestDto.getPhones())) {
            throw new PhoneExistException(String.format("уже существуют пользователи с такими номерами(ом): %s!", String.join(", ", clientRequestDto.getPhones())));
        }

        if( emailService.isExistByAddresses(clientRequestDto.getEmails())) {
            throw new EmailExistException(String.format("уже существуют пользователи с такими почтовым(и)  адресом(и): %s!", String.join(", ", clientRequestDto.getEmails())));
        }

        clientService.create(clientMapper.DtoToCEntity(clientRequestDto));

    }


}
