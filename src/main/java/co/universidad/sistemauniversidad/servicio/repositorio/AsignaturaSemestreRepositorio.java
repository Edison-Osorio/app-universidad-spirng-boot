package co.universidad.sistemauniversidad.servicio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import co.universidad.sistemauniversidad.entidades.AsignaturaSemestre;

public interface AsignaturaSemestreRepositorio extends JpaRepository<AsignaturaSemestre, Long> {
  
}
