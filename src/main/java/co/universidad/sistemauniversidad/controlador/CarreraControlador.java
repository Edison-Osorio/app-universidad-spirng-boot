package co.universidad.sistemauniversidad.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.universidad.sistemauniversidad.dto.CarreraDTO;
import co.universidad.sistemauniversidad.dto.CarreraRespuesta;
import co.universidad.sistemauniversidad.servicio.CarreraService;
import co.universidad.sistemauniversidad.utilerias.AppConstantes;

@RestController
@RequestMapping("/api/carreras")
@CrossOrigin("*")
public class CarreraControlador {

  @Autowired
  private CarreraService carreraService;

  @GetMapping
  public CarreraRespuesta listarCarreras(
      @RequestParam(value = "pageNumber", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
      @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
      @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
      @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {

    return carreraService.obtenerTodosLosCarreras(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarreraDTO> listarCarreraPorId(@PathVariable long id) {
    return ResponseEntity.ok(carreraService.obtenerCarreraPorId(id));

  }

  @PostMapping
  public ResponseEntity<CarreraDTO> guardarCarrera(@Valid @RequestBody CarreraDTO carreraDTO) {
    return new ResponseEntity<CarreraDTO>(carreraService.crearCarreraDTO(carreraDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarreraDTO> actualizarCarrera(@PathVariable long id,
      @Valid @RequestBody CarreraDTO carreraDTO) {
    CarreraDTO carreraRespueta = carreraService.actualizarCarrera(id, carreraDTO);
    return new ResponseEntity<CarreraDTO>(carreraRespueta, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarCarrera(@PathVariable long id) {
    carreraService.eliminarCarrera(id);
    return new ResponseEntity<String>("Carrera eliminado con exito!", HttpStatus.OK);
  }

}
