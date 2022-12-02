package co.universidad.sistemauniversidad.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table(name = "asignaturas")
public class Asignatura {

  @Id
  @Column(name = "asignatura_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String nombre;

  private String descripcion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "carrera_id")
  @JsonProperty(access = Access.WRITE_ONLY)
  private Carrera carreras;


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

  public Carrera getCarreras() {
    return this.carreras;
  }

  public void setCarreras(Carrera carreras) {
    this.carreras = carreras;
  }


}
