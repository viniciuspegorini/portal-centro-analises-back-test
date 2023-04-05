package com.portal.centro.API.service;

import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Equipment;
import com.portal.centro.API.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService extends GenericService<Equipment, Long> {

    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        super(equipmentRepository);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Equipment save(Equipment requestBody) throws Exception {
        return super.save(requestBody);
    }
}

