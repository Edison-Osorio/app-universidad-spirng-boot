package co.universidad.sistemauniversidad.servicio;

import co.universidad.sistemauniversidad.dto.UsuarioDTO;
import co.universidad.sistemauniversidad.dto.UsuarioRespuesta;
import co.universidad.sistemauniversidad.entidades.Usuario;
import co.universidad.sistemauniversidad.entidades.UsuarioRol;
import co.universidad.sistemauniversidad.excepciones.ResourceNotFoundException;
import co.universidad.sistemauniversidad.servicio.repositorio.RolRepositorio;
import co.universidad.sistemauniversidad.servicio.repositorio.UsuarioRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {

        Usuario usuario = usuarioRepositorio.findByEmail(email);
        return usuario;
    }

    @Override
    public UsuarioRespuesta obetenerTodosLosUsuarios(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);

        Page<Usuario> usuarios = usuarioRepositorio.findAll(pageable);
        List<Usuario> listUsuario = usuarios.getContent();
       // List<UsuarioDTO> contenido = listUsuario.stream().map(usuario -> usuario).collect(Collectors.toList());
        UsuarioRespuesta usuarioRespuesta = new UsuarioRespuesta();
        usuarioRespuesta.setContenido(listUsuario);
        usuarioRespuesta.setNumeroPagina(usuarios.getNumber());
        usuarioRespuesta.setMedidaPagina(usuarios.getSize());
        usuarioRespuesta.setTotalElementos(usuarios.getTotalElements());
        usuarioRespuesta.setTotalPaginas(usuarios.getTotalPages());
        usuarioRespuesta.setUltimas(usuarios.isLast());
        return usuarioRespuesta;

    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
        return mapearDTO(usuario);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepositorio.findByUsername(usuario.getUsername());
        if (usuarioLocal != null) {
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        } else {
            for (UsuarioRol usuarioRol : usuarioRoles) {
                rolRepositorio.save(usuarioRol.getRol());
            }
            usuario.getRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepositorio.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public UsuarioDTO actializarUsuario(Long usuarioId, UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());

        Usuario usuarioActualizado = usuarioRepositorio.save(usuario);

        return mapearDTO(usuarioActualizado);
    }

    @Override
    public Usuario actializarContraseñaUsuario(Long usuarioId, Usuario user) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
        usuario.setPassword(passwordEncoder.encode(user.getPassword()));

        Usuario usuarioConContraseñaActualizada = usuarioRepositorio.save(usuario);
        return user;
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
        usuarioRepositorio.delete(usuario);
    }

    // Convertir a DTO
    private UsuarioDTO mapearDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    // Convertir de DTO a entidad
    private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return usuario;
    }
}
