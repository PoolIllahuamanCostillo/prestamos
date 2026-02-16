package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public abstract class Material implements Identificable {

    //Atributos:
    private String id;
    private String nombre;
    private EstadoMaterial estado;
    private Set<String> etiquetas;

    // Constructor:
    public Material(){

    }

    public Material(String id, String nombre, EstadoMaterial estado, Set<String> etiquetas) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.etiquetas = etiquetas;
    }

    public Material(String nombre, EstadoMaterial estado, Set<String> etiquetas) {
        this.nombre = nombre;
        this.estado = estado;
        this.etiquetas = etiquetas;
    }

    // Getters and Setters:

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoMaterial getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }

    public Set<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public abstract String getTipo();

    // toString:


    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                ", etiquetas=" + etiquetas +
                '}';
    }
}
