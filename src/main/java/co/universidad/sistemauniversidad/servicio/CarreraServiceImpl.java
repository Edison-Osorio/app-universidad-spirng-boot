package co.universidad.sistemauniversidad.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.universidad.sistemauniversidad.dto.CarreraDTO;
import co.universidad.sistemauniversidad.dto.CarreraRespuesta;
import co.universidad.sistemauniversidad.entidades.Carrera;
import co.universidad.sistemauniversidad.excepciones.ResourceNotFoundException;
import co.universidad.sistemauniversidad.servicio.repositorio.CarreraRespositorio;

@Service
public class CarreraServiceImpl implements CarreraService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private CarreraRespositorio carreraRespositorio;

  @Override
  public CarreraDTO crearCarreraDTO(CarreraDTO carreraDTO) {

    Carrera carrera = mapearEntidad(carreraDTO);
    Carrera nuevaCarrera = carreraRespositorio.save(carrera);

    CarreraDTO carreraRespuesta = mapearDTO(nuevaCarrera);

    return carreraRespuesta;
  }

  @Override
  public CarreraRespuesta obtenerTodosLosCarreras(int numeroDePagina, int medidaDePagina, String ordenarPor,
      String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
        : Sort.by(ordenarPor).descending();
    Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

    Page<Carrera> carreras = carreraRespositorio.findAll(pageable);
    List<Carrera> listCarrera = carreras.getContent();
    List<CarreraDTO> contenido = listCarrera.stream().map(carrera -> mapearDTO(carrera)).collect(Collectors.toList());

    CarreraRespuesta carreraRespuesta = new CarreraRespuesta();
    carreraRespuesta.setContenido(contenido);
    carreraRespuesta.setNumeroPagina(carreras.getNumber());
    carreraRespuesta.setMedidaPagina(carreras.getSize());
    carreraRespuesta.setTotalElementos(carreras.getTotalElements());
    carreraRespuesta.setTotalPaginas(carreras.getTotalPages());
    carreraRespuesta.setUltimas(carreras.isLast());

    return carreraRespuesta;
  }

  @Override
  public CarreraDTO obtenerCarreraPorId(Long carreraId) {
    Carrera carrera = carreraRespositorio.findById(carreraId)
        .orElseThrow(() -> new ResourceNotFoundException("Carrera", "id", carreraId));
    return mapearDTO(carrera);
  }

  @Override
  public CarreraDTO actualizarCarrera(Long carreraId, CarreraDTO carreraDTO) {
    Carrera carrera = carreraRespositorio.findById(carreraId)
        .orElseThrow(() -> new ResourceNotFoundException("Carrera", "id", carreraId));
    carrera.setNombre(carreraDTO.getNombre());

    return mapearDTO(carrera);
  }

  @Override
  public void eliminarCarrera(Long carreraId) {
    Carrera carrera = carreraRespositorio.findById(carreraId)
        .orElseThrow(() -> new ResourceNotFoundException("Carrera", "id", carreraId));
    carreraRespositorio.delete(carrera);
  }

  // Convertimos a DTO
  private CarreraDTO mapearDTO(Carrera carrera) {
    CarreraDTO carreraDTO = modelMapper.map(carrera, CarreraDTO.class);
    return carreraDTO;
  }

  // Convestimos de DTO a entidad
  private Carrera mapearEntidad(CarreraDTO carreraDTO) {
    Carrera carrera = modelMapper.map(carreraDTO, Carrera.class);
    return carrera;
  }

}
