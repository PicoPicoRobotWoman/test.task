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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Клиенты", description = "Управление клиентами и их информацией")
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
    @Operation(summary = "Получить список всех клиентов",
        description = "Этот эндпоинт возвращает полный список клиентов из базы данных. "
            + "Вы можете использовать этот эндпоинт для получения общей информации о клиентах.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список клиентов успешно получен"),
        @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    public Set<ClientResponseDto> getClients() {

        return clientService.getAll().stream().map(clientMapper::entityToDto).collect(Collectors.toSet());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить информацию о клиенте по ID",
        description = "Этот эндпоинт возвращает информацию о клиенте по указанному ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешно найден клиент"),
        @ApiResponse(responseCode = "404", description = "Клиент с указанным ID не найден")
    })
    public ClientResponseDto getClient(
        @Parameter(description = "Идентификатор клиента")
        @PathVariable(value = "id") Long id) {

        if(!clientService.isExistById(id)) {
            throw new ClientNonExistException(String.format("не существует пользователя с id: %s!", id));
        }

        return clientMapper.entityToDto(clientService.getById(id).get());

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать нового клиента",
        description = "Этот эндпоинт создает нового клиента на основе переданных данных.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Клиент успешно создан"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
    })
    public void createClient(
        @Parameter(description = "Данные нового клиента")
        @RequestBody ClientRequestDto clientRequestDto
    ) {

        clientService.create(clientMapper.DtoToCEntity(clientRequestDto));

    }

    @PostMapping("/{id}/contact")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Создать контакт для клиента",
        description = "Этот эндпоинт создает новый контакт для клиента с указанным ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Контакт успешно создан"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
        @ApiResponse(responseCode = "404", description = "Клиент не найден"),
        @ApiResponse(responseCode = "409", description = "Контакт уже существует")
    })
    public void createClient(
        @Parameter(description = "ID клиента, для которого создается контакт")
        @PathVariable(value = "id") Long id,
        @Parameter(description = "Данные нового контакта")
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
    @Operation(
        summary = "Получить контакты клиента",
        description = "Этот эндпоинт возвращает контакты клиента с указанным ID. Можно фильтровать контакты по типу."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список контактов успешно получен"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса"),
        @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    public Set<ContactResponseDto> getContacts(
        @Parameter(description = "ID клиента, для которого запрашиваются контакты")
        @PathVariable(value = "id") Long id,
        @Parameter(description = "Тип контакта для фильтрации")
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
