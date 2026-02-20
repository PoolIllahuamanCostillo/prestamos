package es.fplumara.dam1.prestamos.service;


import es.fplumara.dam1.prestamos.exception.DuplicadoException;
import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Identificable;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MaterialService implements Repository<Material> {

    // Atributos:
    private Repository<Material> materialRepository;

    // Constructor:
    public MaterialService(Repository<Material> materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Prestamo crearPrestamo(String idMaterial, String profesor, LocalDate fecha) {
        return null;
    }

    // registrarMaterial(Material m)
    @Override
    public void save(Material elemento) {

    // Si ya existe un material con el mismo id → DuplicadoException
        Optional<Material> existente = materialRepository.findById(elemento.getId());

    // Verificar si el material existe:
        if (existente.isPresent()) {
            throw new DuplicadoException("Material duplicado");
        }

    // Si m es null o id es null/vacío → IllegalArgumentException
        if (elemento == null || elemento.getId() == null || elemento.getId().isBlank()){
            throw new IllegalArgumentException("Material o ID inválido");

        }
    }

    @Override
    public Optional<Material> findById(String id) {
        return Optional.empty();
    }

    // Devuelve List<Material> con todos los materiales
    @Override
    public List<Material> listAll() {
        return materialRepository.listAll();
    }


    // darDeBaja(String idMaterial)
    @Override
    public void delete(String id) {
        Optional<Material> comprobarMaterial = materialRepository.findById(id);

    //  - Si no existe el material → NoEncontradoException
        if (comprobarMaterial.isEmpty()) {
            throw new NoEncontradoException("Material no existe");
        }

        Material material = comprobarMaterial.get();

    // - Si ya está en BAJA → MaterialNoDisponibleException
        if (material.getEstado() == EstadoMaterial.BAJA) {
            throw new MaterialNoDisponibleException("El material ya está dado de baja");
        }

    // - Si existe y no está en BAJA: cambia estado a BAJA y guarda el cambio:
    /* NOTA:
        No hace falta un if explícito para "si existe y no está en BAJA"
          - Porque esa es la condición natural que queda después de controlar los otros dos casos:
              -No existe → excepción
              - Existe y está en BAJA → excepción
        Si no ha caído en ninguno de esos dos if, necesariamente es “existe y no está en BAJA”,
        y ahí ya puedes proceder directamente a cambiar el estado.

     */
        material.setEstado(EstadoMaterial.BAJA);
        this.materialRepository.save(material);
    }

    @Override
    public void devolverMaterial(String idMaterial) {

    }
}
