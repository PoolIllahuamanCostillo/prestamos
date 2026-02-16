package es.fplumara.dam1.prestamos.repository.impl;

import es.fplumara.dam1.prestamos.model.Identificable;


import java.util.List;
import java.util.Map;

public class BaseRepository<T extends Identificable> {

    private Map<String, T> datos;

    // Clases:
    public void save(T elemento) {

    }

    public Map<String, T> getDatos() {
        return datos;
    }

    public List<T> listAll(){
        return List.of();
    }

    public void delete(String id) {

    }
}
