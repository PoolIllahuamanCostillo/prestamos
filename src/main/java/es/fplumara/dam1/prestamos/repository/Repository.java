package es.fplumara.dam1.prestamos.repository;

import es.fplumara.dam1.prestamos.model.Identificable;
import es.fplumara.dam1.prestamos.model.Prestamo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identificable> {

    Prestamo crearPrestamo(String idMaterial, String profesor, LocalDate fecha);

    public void save(T elemento);

    public Optional<T> findById(String id);

    public List<T> listAll();

    public void delete(String id);

    public void devolverMaterial(String idMaterial);
}
