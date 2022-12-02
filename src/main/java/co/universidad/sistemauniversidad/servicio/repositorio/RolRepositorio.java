package co.universidad.sistemauniversidad.servicio.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.universidad.sistemauniversidad.entidades.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{

  public Optional<Rol> findByNombre(String nombre);
  
}
