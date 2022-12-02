package co.universidad.sistemauniversidad.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.universidad.sistemauniversidad.dto.AsignaturaDTO;
import co.universidad.sistemauniversidad.dto.AsignaturaRespuesta;
import co.universidad.sistemauniversidad.servicio.AsignaturaService;
import co.universidad.sistemauniversidad.utilerias.AppConstantes;

@RestController
@RequestMapping("/api/asignaturas")
@CrossOrigin(origins = {"*"})
public class AsignaturaControlador {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public AsignaturaRespuesta listarAsignaturas(
            @RequestParam(value = "pageNumber", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

        return asignaturaService.obtenerTodosLosAsignaturas(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> listarAsignaturasPorId(@PathVariable long id) {
        return ResponseEntity.ok(asignaturaService.obtenerAsignaturaPorId(id));
    }

    @PostMapping("/{carreraId}")
    public ResponseEntity<AsignaturaDTO> guardarAsignatura(@PathVariable(name = "carreraId") Long carreraId, @Valid @RequestBody AsignaturaDTO asignaturaDTO) {
        return new ResponseEntity<>(asignaturaService.crearAsignaturaDTO(carreraId, asignaturaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> actualizarAsignatura(@PathVariable long id,
                                                              @Valid @RequestBody AsignaturaDTO asignaturaDTO) {
        AsignaturaDTO asignaturaRespuesta = asignaturaService.actualizarAsignatura(id, asignaturaDTO);

        return new ResponseEntity<>(asignaturaRespuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAsignatura(@PathVariable long id) {
        AsignaturaDTO asignaturaDTO = asignaturaService.obtenerAsignaturaPorId(id);
        asignaturaService.eliminarAsignatura(id);


        return new ResponseEntity<>(asignaturaDTO, HttpStatus.OK);
    }

}
