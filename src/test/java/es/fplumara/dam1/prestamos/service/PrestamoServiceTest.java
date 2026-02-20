package es.fplumara.dam1.prestamos.service;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Portatil;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.Repository;
import es.fplumara.dam1.prestamos.repository.impl.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.impl.PrestamoRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PrestamosServiceTest {

    // Mocks de los repositorios:
    @Mock
    private MaterialRepositoryImpl materialRepositoryImpl;

    @Mock
    private PrestamoRepositoryImpl prestamoRepositoryImpl;

    // SUT
    @InjectMocks
    private PrestamoService prestamoService;

    // TODO (alumnos): añadir JUnit 5 y Mockito en el pom.xml y completar:
    //
    // - crearPrestamo_ok_cambiaEstado_y_guarda()
    @Test
    public void crearPrestamoOkCambiaEstadoYGuarda(){
        // Se crea un HeshSet vacío ya que la clase Material pide un Set etiquetas:
        Set<String> etiquetas = new HashSet<>();
        // Se añade una etiqueta con .add
        etiquetas.add("aula1");
        // Se crea un material de tipo portátil
        Material material = new Portatil("M001", "Portátil Aula 1", EstadoMaterial.DISPONIBLE, 16);

        // Se llama al materialRepositoryImpl.findById("M0001")
        // que devuelve un Devuelve Optional.of(material)
        // Es como decirle al mock: "Sí, el material existe y es este mismo objeto"
        when(materialRepositoryImpl.findById("M0001")).thenReturn(Optional.of(material));

        // Se crea un préstamo y llamamos al prestamoService , se guarda en el repositorio y
        // cambia estado del material a PRESTADO para guardar el material actualizado
        Prestamo p  = prestamoService.crearPrestamo(
                "M001",
                "Profesor X",
                LocalDate.now()
        );

        assertNotNull(p); // El préstamo no debe ser null
        // El préstamo debe asociarse al material correcto
        assertEquals("M001", p.getIdMaterial());

        // Después del metodo, el material debe pasar de DISPONIBLE → PRESTADO.
        assertEquals(EstadoMaterial.PRESTADO, material.getEstado());

        // Verificar interacciones del servicio
        verify(materialRepositoryImpl).save(any());

        // Verifica que el servicio guardó el préstamo.
        verify(materialRepositoryImpl).save(material);

    }

    // - crearPrestamo_materialNoExiste_lanzaNoEncontrado()
    // - crearPrestamo_materialNoDisponible_lanzaMaterialNoDisponible()
    // - devolverMaterial_ok_cambiaADisponible()
    //
    // Requisito: usar mocks de repositorios y verify(...)
}
