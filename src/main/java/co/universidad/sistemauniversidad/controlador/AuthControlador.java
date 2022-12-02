package co.universidad.sistemauniversidad.controlador;

// @author eosorio

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import co.universidad.sistemauniversidad.dto.UsuarioDTO;
import co.universidad.sistemauniversidad.dto.UsuarioRespuesta;
import co.universidad.sistemauniversidad.entidades.UsuarioRol;
import co.universidad.sistemauniversidad.seguridad.JWTAuthResponseDTO;
import co.universidad.sistemauniversidad.seguridad.JwtUtils;
import co.universidad.sistemauniversidad.servicio.UsuarioService;
import co.universidad.sistemauniversidad.servicio.impl.UserDetailsServiceImpl;
import co.universidad.sistemauniversidad.utilerias.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import co.universidad.sistemauniversidad.dto.LoginDTO;
import co.universidad.sistemauniversidad.dto.RegistroDTO;
import co.universidad.sistemauniversidad.entidades.Rol;
import co.universidad.sistemauniversidad.entidades.Usuario;
import co.universidad.sistemauniversidad.servicio.repositorio.RolRepositorio;
import co.universidad.sistemauniversidad.servicio.repositorio.UsuarioRepositorio;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"*"})
public class AuthControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @GetMapping("/list-admin")
    public UsuarioRespuesta obtenemosTodosLosUsuarios(
            @RequestParam(value = "pageNumber", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return usuarioService.obetenerTodosLosUsuarios(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    //@PostAuthorize("hasAnyRole('ADMIN','ALUMNO')")
    @GetMapping("/actual-usuario/{user}")
    public ResponseEntity<Usuario> obtenerUsuarioActual(@PathVariable(name = "user") String user) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorEmail(user));
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(),
                        loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginDTO.getUsernameOrEmail());

        //Obtenemos el token de jwtProvider
        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registarUsuario(@RequestBody RegistroDTO registroDTO) throws Exception {
        if (usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        Usuario usuario = new Usuario();

        usuario.setId(registroDTO.getId());
        usuario.setNombre(registroDTO.getNombre());
        usuario.setApellido(registroDTO.getApellido());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setTelefono(registroDTO.getTelefono());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Rol roles = rolRepositorio.findByNombre(registroDTO.getRol()).get();

       // usuario.setRoles(Collections.singleton(roles));

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(roles);

usuarioRoles.add(usuarioRol);
        usuarioService.guardarUsuario(usuario, usuarioRoles);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);

    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable(name = "userId") long userId, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioActualizado = usuarioService.actializarUsuario(userId, usuarioDTO);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PutMapping("/update-password-user/{userId}")
    public ResponseEntity<Usuario> actualizarContraseñaUsuario(@PathVariable(name = "userId") Long userId, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioConContraseñaActualizada = usuarioService.actializarContraseñaUsuario(userId, usuario);
        return new ResponseEntity<>(usuarioConContraseñaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable(name = "userId") Long userId) {
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(userId);

        usuarioService.eliminarUsuario(userId);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

}
