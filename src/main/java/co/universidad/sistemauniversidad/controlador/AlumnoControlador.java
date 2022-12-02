package co.universidad.sistemauniversidad.controlador;

// @author eosorio
import co.universidad.sistemauniversidad.dto.AlumnoDTO;
import co.universidad.sistemauniversidad.dto.AlumnoRespuesta;
import co.universidad.sistemauniversidad.servicio.AlumnoServicio;
import co.universidad.sistemauniversidad.utilerias.AppConstantes;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = {"*"})
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio alumnoServicio;

    @GetMapping
    public AlumnoRespuesta listarAlumnos(
            @RequestParam(value = "pageNumber", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return alumnoServicio.obtenerTodosLosAlumnos(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> listarAlumnoPorId(@PathVariable long id) {
        return ResponseEntity.ok(alumnoServicio.obtenerAlumnoPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> guardarAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return new ResponseEntity<>(alumnoServicio.crearAlumnoDTO(alumnoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> actualizarAlumno(@PathVariable long id, @Valid @RequestBody AlumnoDTO alumnoDTO) {
        AlumnoDTO alumnoRespuesta = alumnoServicio.actualizarAlumno(id, alumnoDTO);
        return new ResponseEntity<>(alumnoRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable long id) {
        AlumnoDTO alumno = alumnoServicio.obtenerAlumnoPorId(id);
        alumnoServicio.eliminarAlumno(id);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

}
