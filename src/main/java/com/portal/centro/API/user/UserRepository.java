package com.portal.centro.API.user;

import com.portal.centro.API.generic.crud.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {

    User findByUsername(String username);

}
