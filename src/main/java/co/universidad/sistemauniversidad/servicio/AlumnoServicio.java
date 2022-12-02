package co.universidad.sistemauniversidad.servicio;

// @author eosorio
import co.universidad.sistemauniversidad.dto.AlumnoDTO;
import co.universidad.sistemauniversidad.dto.AlumnoRespuesta;

public interface AlumnoServicio {

    public AlumnoDTO crearAlumnoDTO(AlumnoDTO alumnoDTO);

    public AlumnoRespuesta obtenerTodosLosAlumnos(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

    public AlumnoDTO obtenerAlumnoPorId(Long alumnoId);

    public AlumnoDTO actualizarAlumno(Long alumnoId, AlumnoDTO alumnoDTO);

    public void eliminarAlumno(Long alumnoId);

}
