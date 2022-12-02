package co.universidad.sistemauniversidad.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "asignatura_semestre_alumno")
public class AsignaturaSemestre {

  @Id
  @Column(name = "asignatura_semestre_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "asignatura_id")
  // @JsonIgnoreProperties({"hibernateLazyInitializer"})
  private Asignatura asignatura;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "semestre_id")
  //@JsonIgnoreProperties({"hibernateLazyInitializer"})
  private Semestre semestre;

  @Column(name = "alumno_id")
  private long alumnoId;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Asignatura getAsignatura() {
    return this.asignatura;
  }

  public void setAsignatura(Asignatura asignatura) {
    this.asignatura = asignatura;
  }

  public Semestre getSemestre() {
    return this.semestre;
  }

  public void setSemestre(Semestre semestre) {
    this.semestre = semestre;
  }

  public long getAlumnoId() {
    return this.alumnoId;
  }

  public void setAlumnoId(long alumnoId) {
    this.alumnoId = alumnoId;
  }

}
