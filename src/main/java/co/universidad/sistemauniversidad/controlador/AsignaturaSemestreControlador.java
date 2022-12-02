package co.universidad.sistemauniversidad.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.universidad.sistemauniversidad.dto.AsignaturaSemestreDTO;
import co.universidad.sistemauniversidad.servicio.AsignaturaSemestreServicio;

@RestController
@RequestMapping("/api/asignaturaSemestre")
@CrossOrigin("*")
public class AsignaturaSemestreControlador {

    @Autowired
    private AsignaturaSemestreServicio asignaturaSemestreServicio;

    @GetMapping
    public List<AsignaturaSemestreDTO> listarTodasLasAsignaturasSemestre() {
        return asignaturaSemestreServicio.obtenerTodasLasAsignaturasConSemestres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaSemestreDTO> obtenerAsignaturaSemestrePorId(@PathVariable Long id) {
        return ResponseEntity.ok(asignaturaSemestreServicio.onbtenerAsignaturaSemestrePorId(id));
    }

    @PostMapping
    public ResponseEntity<AsignaturaSemestreDTO> guardarAsignaturaSemestre(@Valid @RequestBody AsignaturaSemestreDTO asignaturaSemestreDTO) {
        return new ResponseEntity<>(asignaturaSemestreServicio.crearAsignaturaSemestreDTO(asignaturaSemestreDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaSemestreDTO> actualizarAsignaturaSemestre(@PathVariable Long id, @RequestBody AsignaturaSemestreDTO asignaturaSemestreDTO) {
        AsignaturaSemestreDTO asignaturaSemestreRespuesta = asignaturaSemestreServicio.actualizarAsignaturaSemestreDTO(id, asignaturaSemestreDTO);

        return new ResponseEntity<AsignaturaSemestreDTO>(asignaturaSemestreRespuesta, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAsignaturaSemestre(@PathVariable Long id) {
        asignaturaSemestreServicio.eliminarAsignaturaSemestre(id);
        return new ResponseEntity<String>("AsignaturaSemestre Eliminada con exito!", HttpStatus.OK);
    }

}
