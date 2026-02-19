package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PrestamoService implements Repository<Prestamo> {

    // Atributos:
    private Repository<Material> materialRepository;
    private Repository<Prestamo> prestamoRepository;

    // Constructor:

    public PrestamoService(Repository<Material> materialRepository, Repository<Prestamo> prestamoRepository) {
        this.materialRepository = materialRepository;
        this.prestamoRepository = prestamoRepository;
    }

    // crearPrestamo(String idMaterial, String profesor, LocalDate fecha)
    @Override
    public Prestamo crearPrestamo(String idMaterial, String profesor, LocalDate fecha){

    // Si algún parámetro es null/vacío (o fecha null) → IllegalArgumentException
        if (idMaterial == null || idMaterial.isBlank() ){
            throw new IllegalArgumentException("idMaterial es nulo");
        }

        if (profesor == null || profesor.isBlank() ){
            throw new IllegalArgumentException("Profesor es nulo");
        }

        if (fecha == null) {
            throw new IllegalArgumentException("Fecha es nula");
        }

    // Si no existe material con ese id → NoEncontradoException
        Optional<Material> comprobarMaterial = materialRepository.findById(idMaterial);

        if (comprobarMaterial.isEmpty()) {
            throw new NoEncontradoException("Material no disponible");
        }

        Material material = comprobarMaterial.get();

    // Si existe pero su estado no es DISPONIBLE → MaterialNoDisponibleException
        if (!(material.getEstado() == EstadoMaterial.DISPONIBLE)){
            throw new MaterialNoDisponibleException("Material no disponible");
        }

    //Si OK:
        // crea un Prestamo (id por ejemplo con UUID)
        String prestamoId = UUID.randomUUID().toString();
        Prestamo prestamo = new Prestamo(prestamoId, idMaterial, profesor, fecha);

        // guarda el préstamo con PrestamoRepository
        this.prestamoRepository.save(prestamo);

        // cambia el estado del material a PRESTADO y guarda el material actualizado
        material.setEstado(EstadoMaterial.PRESTADO);
        this.materialRepository.save(material);

        return prestamo;
    }

    @Override
    public void save(Prestamo elemento) {

    }

    @Override
    public Optional<Prestamo> findById(String id) {
        return Optional.empty();
    }

    // listarPrestamos()
    @Override
    public List<Prestamo> listAll() {
        // Devuelve List<Prestamo> con todos los préstamos
        return prestamoRepository.listAll();
    }

    @Override
    public void delete(String id) {
        if (id == null) return;

    }
}
