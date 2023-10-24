package com.nicole.shaine.test.task.service.abs;

import com.nicole.shaine.test.task.models.entitys.Phone;

import java.util.Set;

public interface PhoneService extends AbstractService<Long, Phone> {


    Boolean isExistByValues(Set<String> values);

}
