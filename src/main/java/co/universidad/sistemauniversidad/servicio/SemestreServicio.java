package co.universidad.sistemauniversidad.servicio;

// @author eosorio
import co.universidad.sistemauniversidad.dto.SemestreDTO;
import java.util.List;

public interface SemestreServicio {

    public SemestreDTO crearSemestreDTO(SemestreDTO alumnoDTO);

    public List<SemestreDTO> obtenerTodosLosSemestres();

    public SemestreDTO obtenerSemestrePorId(Long alumnoId);

    public SemestreDTO actualizarSemestre(Long alumnoId, SemestreDTO alumnoDTO);

    public void eliminarSemestre(Long alumnoId);

}
