package com.portal.centro.API.generic;

import java.util.List;
import java.util.Optional;

public abstract class ICRUDService <K, N, R>{

    public abstract List<R> getAll(String username);
    public abstract Optional<R> getById(Long id);
    public abstract R add(K model);
    public abstract void delete(Long id);
    public abstract R update(K model, Long id);
}