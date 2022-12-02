package co.universidad.sistemauniversidad.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.universidad.sistemauniversidad.entidades.Asignatura;
import co.universidad.sistemauniversidad.entidades.Semestre;

public class AsignaturaSemestreDTO {

  private long id;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private Asignatura asignatura;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private Semestre semestre;

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
