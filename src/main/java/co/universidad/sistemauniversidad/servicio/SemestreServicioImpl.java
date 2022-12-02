package co.universidad.sistemauniversidad.servicio;

// @author eosorio

import co.universidad.sistemauniversidad.dto.SemestreDTO;
import co.universidad.sistemauniversidad.entidades.Semestre;
import co.universidad.sistemauniversidad.excepciones.ResourceNotFoundException;
import co.universidad.sistemauniversidad.servicio.repositorio.SemestreRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemestreServicioImpl implements SemestreServicio {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SemestreRepositorio semestreRepositorio;

    @Override
    public SemestreDTO crearSemestreDTO(SemestreDTO semestreDTO) {
        
        Semestre semestre = mapearEntidad(semestreDTO);

        Semestre nuevoSemestre = semestreRepositorio.save(semestre);
        SemestreDTO semestreRespuesta = mapearDTO(nuevoSemestre);

        return semestreRespuesta;
    }

    @Override
    public List<SemestreDTO> obtenerTodosLosSemestres() {
        List<Semestre> listadoSemestres = semestreRepositorio.findAll();
        return listadoSemestres.stream().map(semestre -> mapearDTO(semestre)).collect(Collectors.toList());
    }

    @Override
    public SemestreDTO obtenerSemestrePorId(Long semestreId) {

        Semestre semestre = semestreRepositorio.findById(semestreId).orElseThrow(() -> new ResourceNotFoundException("Semestre", "id", semestreId));
        return mapearDTO(semestre);
    }

    @Override
    public SemestreDTO actualizarSemestre(Long semestreId, SemestreDTO semestreDTO) {
        Semestre semestre = semestreRepositorio.findById(semestreId).orElseThrow(() -> new ResourceNotFoundException("Semestre", "id", semestreId));

        semestre.setNombre(semestreDTO.getNombre());

        Semestre semestreActualizado = semestreRepositorio.save(semestre);

        return mapearDTO(semestreActualizado);
    }

    @Override
    public void eliminarSemestre(Long semestreId) {
        Semestre semestre = semestreRepositorio.findById(semestreId).orElseThrow(() -> new ResourceNotFoundException("Semestre", "id", semestreId));
        semestreRepositorio.delete(semestre);
    }

    // Convertir a DTO
    private SemestreDTO mapearDTO(Semestre semestre) {
        SemestreDTO semestreDTO = modelMapper.map(semestre, SemestreDTO.class);
        return semestreDTO;
    }

    // Convertir de DTO a entidad
    private Semestre mapearEntidad(SemestreDTO semestreDTO) {
        Semestre semestre = modelMapper.map(semestreDTO, Semestre.class);
        return semestre;
    }

}
