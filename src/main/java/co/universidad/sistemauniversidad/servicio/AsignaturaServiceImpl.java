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

import co.universidad.sistemauniversidad.dto.AsignaturaDTO;
import co.universidad.sistemauniversidad.dto.AsignaturaRespuesta;
import co.universidad.sistemauniversidad.entidades.Asignatura;
import co.universidad.sistemauniversidad.entidades.Carrera;
import co.universidad.sistemauniversidad.excepciones.ResourceNotFoundException;
import co.universidad.sistemauniversidad.servicio.repositorio.AsignaturaRepositorio;
import co.universidad.sistemauniversidad.servicio.repositorio.CarreraRespositorio;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AsignaturaRepositorio asignaturaRepositorio;

    @Autowired
    private CarreraRespositorio carreraRespositorio;

    @Override
    public AsignaturaRespuesta obtenerTodosLosAsignaturas(int numeroDePagina, int medidaDePagina, String ordenarPor,
                                                          String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
                : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Asignatura> asignaturas = asignaturaRepositorio.findAll(pageable);
        List<Asignatura> listAsignaturas = asignaturas.getContent();
        List<AsignaturaDTO> contenido = listAsignaturas.stream().map(asignatura -> mapearDTO(asignatura))
                .collect(Collectors.toList());

        AsignaturaRespuesta asignaturaRespuesta = new AsignaturaRespuesta();

        asignaturaRespuesta.setContenido(contenido);
        asignaturaRespuesta.setNumeroPagina(asignaturas.getNumber());
        asignaturaRespuesta.setMedidaPagina(asignaturas.getSize());
        asignaturaRespuesta.setTotalElementos(asignaturas.getTotalElements());
        asignaturaRespuesta.setTotalPaginas(asignaturas.getTotalPages());
        asignaturaRespuesta.setUltimas(asignaturas.isLast());
        return asignaturaRespuesta;
    }

    @Override
    public AsignaturaDTO crearAsignaturaDTO(Long carreraId, AsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = mapearEntidad(asignaturaDTO);
        Carrera carrera = carreraRespositorio.findById(carreraId).orElseThrow(() -> new ResourceNotFoundException("Carrera", "id", carreraId));
        asignatura.setCarreras(carrera);
        Asignatura nuevaAsignatura = asignaturaRepositorio.save(asignatura);

        AsignaturaDTO asignaturaRespuesta = mapearDTO(nuevaAsignatura);
        return asignaturaRespuesta;
    }

    @Override
    public AsignaturaDTO obtenerAsignaturaPorId(Long asignaturaId) {
        Asignatura asignatura = asignaturaRepositorio.findById(asignaturaId)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura", "id", asignaturaId));
        return mapearDTO(asignatura);
    }

    @Override
    public AsignaturaDTO actualizarAsignatura(Long asignaturaId, AsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = asignaturaRepositorio.findById(asignaturaId)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura", "id", asignaturaId));
        asignatura.setNombre(asignaturaDTO.getNombre());
        asignatura.setDescripcion(asignaturaDTO.getDescripcion());
        // asignatura.setCodigoGrado(asignaturaDTO.getCodigoGrado());
        Asignatura asignaturaActualizada = asignaturaRepositorio.save(asignatura);

        return mapearDTO(asignaturaActualizada);
    }

    @Override
    public void eliminarAsignatura(Long asignaturaId) {
        Asignatura asignatura = asignaturaRepositorio.findById(asignaturaId)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura", "id", asignaturaId));
        asignaturaRepositorio.delete(asignatura);
    }

    // Convertir a DTO
    private AsignaturaDTO mapearDTO(Asignatura asignatura) {
        AsignaturaDTO asignaturaDTO = modelMapper.map(asignatura, AsignaturaDTO.class);
        return asignaturaDTO;
    }

    // Convertir de DTO a entidad
    private Asignatura mapearEntidad(AsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = modelMapper.map(asignaturaDTO, Asignatura.class);
        return asignatura;
    }

}
