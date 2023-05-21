package com.portal.centro.API.generic.crud;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public abstract class GenericService<T, ID> {

    public final GenericRepository<T, ID> genericRepository;

    public GenericService(GenericRepository<T, ID> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public T save(T requestBody) throws Exception {
        return genericRepository.save(requestBody);
    }

    public String deleteById(ID id) {
        genericRepository.deleteById(id);
        return "Registro deletado com sucesso.";
    }

    public List<T> getAll() {
        return genericRepository.findAll();
    }

    public List<T> page(PageRequest pageRequest) {
        return genericRepository.findAll(pageRequest).getContent();
    }

    public List<T> search(Specification specification, PageRequest pageRequest) {
        return genericRepository.findAll(specification, pageRequest).getContent();
    }

    public T findOneById(ID id) { return genericRepository.findById(id).orElseThrow(); }

}
