package co.universidad.sistemauniversidad.dto;

import java.util.Set;

// @author eosorio
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import co.universidad.sistemauniversidad.entidades.Asignatura;

public class CarreraDTO {

    private long id;

    @NotEmpty
    @Size(min = 4, max = 40, message = "El nombre de la carrera debe tener como minimos 4 carecteres y como maximo 40 caracteres")
    private String nombre;

    private String descripcion;

    
    private Set<Asignatura> asignaturas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

}
