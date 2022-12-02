package co.universidad.sistemauniversidad.dto;

// @author eosorio
import javax.validation.constraints.*;

import co.universidad.sistemauniversidad.entidades.Carrera;

public class AlumnoDTO {

    @NotNull
    private long id;

    @NotEmpty
    @Size(min = 4, max = 30, message = "El nombre del alumno debe tener como minimo 4 caracteres y como maximo 30 caracteres")
    private String nombre;

    @NotEmpty
    @Size(min = 4, max = 50, message = "Los apellidos del alumno debe tener como minimo 4 caracteres y como maximo 50 caracteres")
    private String apellido;

    @NotEmpty
    @Size(min = 4, max = 50, message = "El email del alumno debe tener como minimo 4 caracteres y como maximo 50 caracteres")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, max = 12, message = "El telefono del alumno debe tener como minimo 6 caracteres y como maximo 12 caracteres")
    private String telefono;

    @NotEmpty
    @Size(min = 4, max = 30, message = "La direccion del alumno debe tener como minimo 4 caracteres y como maximo 30 caracteres")
    private String direccion;

private Carrera carrera;


    public AlumnoDTO() {

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Carrera getCarrera() {
        return this.carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }


}
