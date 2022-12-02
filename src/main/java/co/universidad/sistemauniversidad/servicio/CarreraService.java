package co.universidad.sistemauniversidad.servicio;


import co.universidad.sistemauniversidad.dto.CarreraDTO;
import co.universidad.sistemauniversidad.dto.CarreraRespuesta;

public interface CarreraService {

  public CarreraDTO crearCarreraDTO(CarreraDTO carreraDTO);

  public CarreraRespuesta obtenerTodosLosCarreras(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

  public CarreraDTO obtenerCarreraPorId(Long carreraId);

  public CarreraDTO actualizarCarrera(Long carreraId, CarreraDTO carreraDTO);

  public void eliminarCarrera(Long carreraId);
  
}
