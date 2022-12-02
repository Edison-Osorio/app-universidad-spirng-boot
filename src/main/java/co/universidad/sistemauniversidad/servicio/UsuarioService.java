package co.universidad.sistemauniversidad.servicio;

import co.universidad.sistemauniversidad.dto.UsuarioDTO;
import co.universidad.sistemauniversidad.dto.UsuarioRespuesta;
import co.universidad.sistemauniversidad.entidades.Usuario;
import co.universidad.sistemauniversidad.entidades.UsuarioRol;

import java.util.List;
import java.util.Set;

public interface UsuarioService {
    public Usuario obtenerUsuarioPorEmail(String email);

    public UsuarioRespuesta obetenerTodosLosUsuarios(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);

    public UsuarioDTO obtenerUsuarioPorId(Long usuarioId);

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles)throws Exception;

    public UsuarioDTO actializarUsuario(Long usuarioId, UsuarioDTO usuarioDTO);

    public Usuario actializarContraseñaUsuario(Long usuarioId, Usuario usuario);

    public void eliminarUsuario(Long usuarioId);
}
