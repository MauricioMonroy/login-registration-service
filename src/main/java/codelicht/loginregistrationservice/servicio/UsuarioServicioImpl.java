package codelicht.loginregistrationservice.servicio;

import codelicht.loginregistrationservice.entidad.Rol;
import codelicht.loginregistrationservice.entidad.Usuario;
import codelicht.loginregistrationservice.modelodto.UsuarioDto;
import codelicht.loginregistrationservice.repositorio.RolRepositorio;
import codelicht.loginregistrationservice.repositorio.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que implementa los métodos de la interfaz IUsuarioServicio.
 */
@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final RolRepositorio rolRepositorio;
    private final PasswordEncoder passwordEncoder;

    // Inyección de dependencias por constructor
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio,
                               RolRepositorio rolRepositorio,
                               PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    // Método que guarda un usuario en la base de datos
    @Override
    public void guardarUsuario(UsuarioDto usuarioDto) {
        // Verificar si el usuario ya existe
        if (usuarioRepositorio.findByEmail(usuarioDto.getEmail()) != null) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese email");
        }
        Usuario usuario = new Usuario();
        String nombreCompleto = usuarioDto.getNombreUsuario() + " " + usuarioDto.getApellidoUsuario();
        usuario.setUsername(nombreCompleto.trim().replaceAll(" +", " "));
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        // Se asigna el rol de usuario
        Rol rol = rolRepositorio.findByNombre("ROLE_ADMIN");
        if (rol == null) {
            rol = comprobarExistenciaRol();
        }
        usuario.setRoles(List.of(rol));
        usuarioRepositorio.save(usuario);
    }

    // Método que busca un usuario por su correo electrónico
    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    // Método que busca todos los usuarios en la base de datos
    @Override
    public List<UsuarioDto> buscarTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(this::mapeoUsuarioDto)
                .collect(Collectors.toList());
    }

    // Método que mapea un usuario a un UsuarioDto
    private UsuarioDto mapeoUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        String[] str = usuario.getUsername().split(" ", 2);
        usuarioDto.setNombreUsuario(str.length > 0 ? str[0] : "");
        usuarioDto.setApellidoUsuario(str.length > 1 ? str[1] : "");
        usuarioDto.setEmail(usuario.getEmail());
        return usuarioDto;
    }

    // Método que comprueba la existencia de un rol
    private Rol comprobarExistenciaRol() {
        Rol rol = new Rol();
        rol.setNombre("ROLE_ADMIN");
        return rolRepositorio.save(rol);
    }
}
