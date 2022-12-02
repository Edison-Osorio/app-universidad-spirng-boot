package co.universidad.sistemauniversidad.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.universidad.sistemauniversidad.dto.AsignaturaSemestreDTO;
import co.universidad.sistemauniversidad.entidades.AsignaturaSemestre;
import co.universidad.sistemauniversidad.excepciones.ResourceNotFoundException;
import co.universidad.sistemauniversidad.servicio.repositorio.AsignaturaSemestreRepositorio;

@Service
public class AsignaturaSemestreServicioImpl implements AsignaturaSemestreServicio {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private AsignaturaSemestreRepositorio asignaturaSemestreRepositorio;

  @Override
  public List<AsignaturaSemestreDTO> obtenerTodasLasAsignaturasConSemestres() {
    List<AsignaturaSemestre> listAsignaturaSemestres = asignaturaSemestreRepositorio.findAll();
    return listAsignaturaSemestres.stream().map(asignaturaSemestre -> mapearDTO(asignaturaSemestre))
        .collect(Collectors.toList());
  }

  @Override
  public AsignaturaSemestreDTO onbtenerAsignaturaSemestrePorId(Long alumnoId) {
    AsignaturaSemestre asignaturaSemestre = asignaturaSemestreRepositorio.findById(alumnoId)
        .orElseThrow(() -> new ResourceNotFoundException("AsignaturaSemestre", "id", alumnoId));
    return mapearDTO(asignaturaSemestre);
  }

  @Override
  public AsignaturaSemestreDTO crearAsignaturaSemestreDTO(AsignaturaSemestreDTO asignaturaSemestreDTO) {
    AsignaturaSemestre asignaturaSemestre = mapearEntidad(asignaturaSemestreDTO);
    AsignaturaSemestre nuevoAsignaturaSemestre = asignaturaSemestreRepositorio.save(asignaturaSemestre);
    AsignaturaSemestreDTO asignaturaSemestreRespuesta = mapearDTO(nuevoAsignaturaSemestre);

    return asignaturaSemestreRespuesta;
  }

  @Override
  public AsignaturaSemestreDTO actualizarAsignaturaSemestreDTO(Long id, AsignaturaSemestreDTO asignaturaSemestreDTO) {
    AsignaturaSemestre asignaturaSemestre = asignaturaSemestreRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("AsignaturaSemestre", "id", id));

    asignaturaSemestre.setAsignatura(asignaturaSemestreDTO.getAsignatura());
    asignaturaSemestre.setSemestre(asignaturaSemestreDTO.getSemestre());

    AsignaturaSemestre asignaturaSemestreActualizado = asignaturaSemestreRepositorio.save(asignaturaSemestre);

    return mapearDTO(asignaturaSemestreActualizado);
  }

  @Override
  public void eliminarAsignaturaSemestre(Long id) {
    AsignaturaSemestre asignaturaSemestre = asignaturaSemestreRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("AsignaturaSemestre", "id", id));

    asignaturaSemestreRepositorio.delete(asignaturaSemestre);
  }

  private AsignaturaSemestreDTO mapearDTO(AsignaturaSemestre asignaturaSemestre) {
    AsignaturaSemestreDTO asignaturaSemestreDTO = modelMapper.map(asignaturaSemestre, AsignaturaSemestreDTO.class);
    return asignaturaSemestreDTO;
  }

  private AsignaturaSemestre mapearEntidad(AsignaturaSemestreDTO asignaturaSemestreDTO) {
    AsignaturaSemestre asignaturaSemestre = modelMapper.map(asignaturaSemestreDTO, AsignaturaSemestre.class);

    return asignaturaSemestre;
  }

}
