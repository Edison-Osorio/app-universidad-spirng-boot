package co.universidad.sistemauniversidad.servicio.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.universidad.sistemauniversidad.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
  
  public Usuario findByEmail(String email);

  public Usuario findByUsername(String username);

  public Optional<Usuario> findByUsernameOrEmail(String username, String email);

  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String email);
}
