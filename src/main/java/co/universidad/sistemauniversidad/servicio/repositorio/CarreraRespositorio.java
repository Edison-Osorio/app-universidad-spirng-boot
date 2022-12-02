package co.universidad.sistemauniversidad.servicio.repositorio;

// @author eosorio
import co.universidad.sistemauniversidad.entidades.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraRespositorio extends JpaRepository<Carrera, Long> {

}
