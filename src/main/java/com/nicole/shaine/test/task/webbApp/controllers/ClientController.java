package com.nicole.shaine.test.task.webbApp.controllers;

import com.nicole.shaine.test.task.models.entitys.Client;
import com.nicole.shaine.test.task.service.abs.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients() {

        return clientService.getAll();

    }

}
