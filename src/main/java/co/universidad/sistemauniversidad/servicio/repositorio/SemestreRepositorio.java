package co.universidad.sistemauniversidad.servicio.repositorio;

// @author eosorio
import co.universidad.sistemauniversidad.entidades.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepositorio extends JpaRepository<Semestre, Long> {

}
