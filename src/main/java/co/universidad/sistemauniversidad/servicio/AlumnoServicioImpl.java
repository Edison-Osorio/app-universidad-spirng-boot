package co.universidad.sistemauniversidad.servicio;

// @author eosorio
import co.universidad.sistemauniversidad.dto.AlumnoDTO;
import co.universidad.sistemauniversidad.dto.AlumnoRespuesta;
import co.universidad.sistemauniversidad.entidades.Alumno;
import co.universidad.sistemauniversidad.excepciones.ResourceNotFoundException;
import co.universidad.sistemauniversidad.servicio.repositorio.AlumnoRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    @Override
    public AlumnoDTO crearAlumnoDTO(AlumnoDTO alumnoDTO) {
        Alumno alumno = mapearEntidad(alumnoDTO);
        Alumno nuevoAlumno = alumnoRepositorio.save(alumno);
        AlumnoDTO alumnoRespuesta = mapearDTO(nuevoAlumno);
        return alumnoRespuesta;
    }

    @Override
    public AlumnoRespuesta obtenerTodosLosAlumnos(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Alumno> alumnos = alumnoRepositorio.findAll(pageable);
        List<Alumno> listAlumno = alumnos.getContent();
        List<AlumnoDTO> contenido = listAlumno.stream().map(alumno -> mapearDTO(alumno)).collect(Collectors.toList());
        AlumnoRespuesta alumnoRespuesta = new AlumnoRespuesta();
        alumnoRespuesta.setContenido(contenido);
        alumnoRespuesta.setNumeroPagina(alumnos.getNumber());
        alumnoRespuesta.setMedidaPagina(alumnos.getSize());
        alumnoRespuesta.setTotalElementos(alumnos.getTotalElements());
        alumnoRespuesta.setTotalPaginas(alumnos.getTotalPages());
        alumnoRespuesta.setUltimas(alumnos.isLast());

        return alumnoRespuesta;
    }

    @Override
    public AlumnoDTO obtenerAlumnoPorId(Long alumnoId) {
        Alumno alumno = alumnoRepositorio.findById(alumnoId).orElseThrow(() -> new ResourceNotFoundException("Alumno", "id", alumnoId));
        return mapearDTO(alumno);
    }

    @Override
    public AlumnoDTO actualizarAlumno(Long alumnoId, AlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoRepositorio.findById(alumnoId).orElseThrow(() -> new ResourceNotFoundException("Alumno", "id", alumnoId));
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setApellido(alumnoDTO.getApellido());
        alumno.setEmail(alumnoDTO.getEmail());
        alumno.setTelefono(alumnoDTO.getTelefono());
        alumno.setDireccion(alumnoDTO.getDireccion());

        Alumno alumnoRespuesta = alumnoRepositorio.save(alumno);

        return mapearDTO(alumnoRespuesta);
    }

    @Override
    public void eliminarAlumno(Long alumnoId) {
        Alumno alumno = alumnoRepositorio.findById(alumnoId).orElseThrow(() -> new ResourceNotFoundException("Alumno", "id", alumnoId));
        alumnoRepositorio.deleteById(alumnoId);

    }

    // Convertir a DTO
    private AlumnoDTO mapearDTO(Alumno alumno) {
        AlumnoDTO alumnoDTO = modelMapper.map(alumno, AlumnoDTO.class);
        return alumnoDTO;
    }

    // Convertir de DTO a entidad
    private Alumno mapearEntidad(AlumnoDTO alumnoDTO) {
        Alumno alumno = modelMapper.map(alumnoDTO, Alumno.class);
        return alumno;
    }

}
