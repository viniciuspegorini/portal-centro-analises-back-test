package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.EmailCode;
import com.portal.centro.API.model.Equipment;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCodeRepository extends GenericRepository<EmailCode,Long> {

    EmailCode findEmailCodeByCode(String code);

}
