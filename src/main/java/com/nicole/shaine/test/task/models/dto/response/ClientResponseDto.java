package com.nicole.shaine.test.task.models.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientResponseDto {

    private Long id;
    private String name;
    private Set<ContactResponseDto> contacts;

}
