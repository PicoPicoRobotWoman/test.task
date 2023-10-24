package com.nicole.shaine.test.task.models.dto.response;

import com.nicole.shaine.test.task.models.enums.Gender;
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
    private Gender gender;
    private Set<String> emails;
    private Set<String> phones;

}
