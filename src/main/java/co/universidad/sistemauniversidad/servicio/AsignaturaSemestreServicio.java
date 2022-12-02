package co.universidad.sistemauniversidad.servicio;

import java.util.List;

import co.universidad.sistemauniversidad.dto.AsignaturaSemestreDTO;

public interface AsignaturaSemestreServicio {

  public List<AsignaturaSemestreDTO> obtenerTodasLasAsignaturasConSemestres();

  public AsignaturaSemestreDTO onbtenerAsignaturaSemestrePorId(Long alumnoId);

  public AsignaturaSemestreDTO crearAsignaturaSemestreDTO(AsignaturaSemestreDTO asignaturaSemestreDTO);

  public AsignaturaSemestreDTO actualizarAsignaturaSemestreDTO(Long id, AsignaturaSemestreDTO asignaturaSemestreDTO);

  public void eliminarAsignaturaSemestre(Long id);

}
