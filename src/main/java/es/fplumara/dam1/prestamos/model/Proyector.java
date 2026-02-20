package es.fplumara.dam1.prestamos.model;

public class Proyector extends Material {
    private int lumens;

    public Proyector(String id, String nombre, EstadoMaterial estado, int lumens) {
        super(id, nombre, estado);
        this.lumens = lumens;
    }

    public int getLumens(){
        return lumens;
    }

    //Getter:
    @Override
    public String getTipo() {
        return "Proyector";
    }
}
