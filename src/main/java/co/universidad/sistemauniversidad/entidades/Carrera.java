package co.universidad.sistemauniversidad.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "carreras", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "nombre" })
})
public class Carrera {

  @Id
  @Column(name = "carrera_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty
  @Size(min = 4, max = 30, message = "El nombre del grado debe tener como minimo 4 caracteres y como maximo 30 caracteres")
  private String nombre;

  private String descripcion;

  @JsonBackReference
  @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Alumno> alumnos = new HashSet<>();

  // @JsonBackReference
  @OneToMany(mappedBy = "carreras", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Asignatura> asignaturas = new HashSet<>();

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

  public String getDescripcion() {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Set<Alumno> getAlumnos() {
    return this.alumnos;
  }

  public void setAlumnos(Set<Alumno> alumnos) {
    this.alumnos = alumnos;
  }

  public Set<Asignatura> getAsignaturas() {
    return this.asignaturas;
  }

  public void setAsignaturas(Set<Asignatura> asignaturas) {
    this.asignaturas = asignaturas;
  }

}
