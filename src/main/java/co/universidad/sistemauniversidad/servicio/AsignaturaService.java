package co.universidad.sistemauniversidad.servicio;

import co.universidad.sistemauniversidad.dto.AsignaturaDTO;
import co.universidad.sistemauniversidad.dto.AsignaturaRespuesta;

public interface AsignaturaService {

  public AsignaturaDTO crearAsignaturaDTO(Long carreraId,AsignaturaDTO asignaturaDTO);

  public AsignaturaRespuesta obtenerTodosLosAsignaturas(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

  public AsignaturaDTO obtenerAsignaturaPorId(Long asignaturaId);

  public AsignaturaDTO actualizarAsignatura(Long asignaturaId, AsignaturaDTO asignaturaDTO);

  public void eliminarAsignatura(Long asignaturaId);


}
