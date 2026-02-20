package es.fplumara.dam1.prestamos.repository.impl;

import es.fplumara.dam1.prestamos.model.Identificable;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.Repository;


import java.time.LocalDate;
import java.util.*;

public class BaseRepository<T extends Identificable> implements Repository<T> {

    private Map<String, T> datos;

    public BaseRepository() {
        datos = new HashMap<>();
    }

    @Override
    public Prestamo crearPrestamo(String idMaterial, String profesor, LocalDate fecha) {
        return null;
    }

    @Override
    public void save(T elemento) {
        datos.put(elemento.getId(), elemento);
    }

    @Override
    public Optional<T> findById(String id) {
        T elemento = datos.get(id);
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<T> listAll() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public void delete(String id) {
        datos.remove(id);
    }

    @Override
    public void devolverMaterial(String idMaterial) {

    }
}

