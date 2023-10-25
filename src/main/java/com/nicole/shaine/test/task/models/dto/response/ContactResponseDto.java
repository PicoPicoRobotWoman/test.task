package com.nicole.shaine.test.task.models.dto.response;

import com.nicole.shaine.test.task.models.enums.ContactType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContactResponseDto {

    private ContactType contactType;
    private String value;

}
