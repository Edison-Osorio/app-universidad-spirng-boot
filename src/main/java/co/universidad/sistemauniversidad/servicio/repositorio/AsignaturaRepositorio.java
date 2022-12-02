package co.universidad.sistemauniversidad.servicio.repositorio;

// @author eosorio
import co.universidad.sistemauniversidad.entidades.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepositorio extends JpaRepository<Asignatura, Long> {

}
