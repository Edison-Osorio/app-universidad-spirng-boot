package co.universidad.sistemauniversidad.servicio.repositorio;

import co.universidad.sistemauniversidad.entidades.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {

}
