package co.universidad.sistemauniversidad.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import co.universidad.sistemauniversidad.entidades.Carrera;

// @author eosorio

public class AsignaturaDTO {

    private long id;

    private String nombre;

    private String descripcion;

    @JsonBackReference 
    private Carrera carreras;

    public AsignaturaDTO() {

    }

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
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Carrera getCarreras() {
        return this.carreras;
    }

    public void setCarreras(Carrera carrera) {
        this.carreras = carrera;
    }

}
