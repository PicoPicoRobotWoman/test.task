package com.nicole.shaine.test.task.webbApp.controllers;

import com.nicole.shaine.test.task.corverter.ClientMapper;
import com.nicole.shaine.test.task.models.dto.response.ClientResponseDto;

import com.nicole.shaine.test.task.service.abs.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService,
                            ClientMapper clientMapper) {

        this.clientService = clientService;
        this.clientMapper = clientMapper;

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<ClientResponseDto> getClients() {

        return clientService.getAll().stream().map(clientMapper::entityToDto).collect(Collectors.toSet());

    }

}
