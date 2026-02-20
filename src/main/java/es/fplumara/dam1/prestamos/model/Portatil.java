package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public class Portatil extends Material {
    private int ramGB;


    public Portatil(String id, String nombre, EstadoMaterial estado, int ramGB) {
        super(id, nombre, estado);
        this.ramGB = ramGB;
    }

    @Override
    public String getTipo(){
        return "Portatil";
    }


    /*  @Override
    public String toString() {
        return "Portatil{id='" + getId() + "', nombre='" + getNombre() + "', estado=" + getEstado()
                + ", ramGB=" + ramGB + ", etiquetas=" + getEtiquetas() + "}";
    } */

}


/** Explicación personal:
 *
 * Propósito de las clases abstractas
 *  Imagina que entras en un concesionario de coches.
 *  Te acercas a uno de los vendedores, y tienes la siguiente conversación:
 *
 *       - Hola buenas, querría comprar un coche
         + Por supuesto, que quiere comprar ¿una Berlina, un Cupé, un Sedán?
         - No no, yo querría comprar un coche “como concepto”

 - Dependiendo de como pilles el día al vendedor, te va a explicar más o menos amablemente
   que tu no puedes comprar un coche “como concepto”.
 - Puedes comprar un subtipo particular de coche.
 - Pero un coche “como concepto” es una abstracción. Es una idea que recoge las características comunes de todos los tipos de coches.
   Y las ideas no se puede comprar 🤷 (y luego te echaría del concesionario).
 - En este caso, el Coche es una clase abstracta.
 - Es una idea que representa todo lo que tienen en común los coches. Y no se puede instanciar.
 - Luego se especializa en subclases, como Berlina, Cupè, Sedán, que son especializaciones de Coche, y sí puedes instanciar.
 * */
