package com.portal.centro.API.generic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class ICRUDService <K, N, R>{

    public abstract List<R> getAll(String username);
    public abstract Optional<R> getById(UUID id);
    public abstract R add(K model);
    public abstract void delete(UUID id);
    public abstract R update(K model, UUID id);
}