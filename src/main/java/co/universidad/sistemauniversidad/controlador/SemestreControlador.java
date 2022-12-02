package co.universidad.sistemauniversidad.controlador;

// @author eosorio
import co.universidad.sistemauniversidad.dto.SemestreDTO;
import co.universidad.sistemauniversidad.servicio.SemestreServicio;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/semestres")
@CrossOrigin("*")
public class SemestreControlador {

    @Autowired
    private SemestreServicio semestreServicio;

    @GetMapping
    public List<SemestreDTO> listadoSemestre() {
        return semestreServicio.obtenerTodosLosSemestres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemestreDTO> obtenerSemestrePorId(@PathVariable long id) {
        return ResponseEntity.ok(semestreServicio.obtenerSemestrePorId(id));
    }

    @PostMapping
    public ResponseEntity<SemestreDTO> guardarSemestre(@Valid @RequestBody SemestreDTO semestreDTO) {

        return new ResponseEntity<>(semestreServicio.crearSemestreDTO(semestreDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemestreDTO> acutalizarSemestre(@PathVariable long id, @Valid @RequestBody SemestreDTO semestreDTO) {
        SemestreDTO semestreRespuesta = semestreServicio.actualizarSemestre(id, semestreDTO);
        return new ResponseEntity<>(semestreRespuesta, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSemestre(@PathVariable long id) {
        semestreServicio.eliminarSemestre(id);
        return new ResponseEntity<>("Semestre eliminado con exito !", HttpStatus.OK);
    }

}
