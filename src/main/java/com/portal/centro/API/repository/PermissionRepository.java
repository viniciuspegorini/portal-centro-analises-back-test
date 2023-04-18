package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends GenericRepository<Permission,Long> {

}

