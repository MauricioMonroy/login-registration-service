package codelicht.loginregistrationservice.controlador;

import codelicht.loginregistrationservice.entidad.Usuario;
import codelicht.loginregistrationservice.modelodto.UsuarioDto;
import codelicht.loginregistrationservice.servicio.IUsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las peticiones relacionadas con la autenticación.
 */
@RestController
@RequestMapping("/auth-api")
public class AutenticacionControlador {

    private final IUsuarioServicio iUsuarioServicio;

    // Inyección de dependencias por constructor
    public AutenticacionControlador(IUsuarioServicio iUsuarioServicio) {
        this.iUsuarioServicio = iUsuarioServicio;
    }

    // Método que muestra la página de inicio
    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    // Método para manejar la petición de inicio de sesión
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Método que muestra el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
        // Se crea un objeto de tipo UsuarioDto
        UsuarioDto usuario = new UsuarioDto();
        model.addAttribute("usuario", usuario);
        return "registrar";
    }

    // Método que guarda un usuario en la base de datos
    @PostMapping("/registro/guardar")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto,
                                   BindingResult result,
                                   Model model) {
        Usuario usuarioExistente = iUsuarioServicio.buscarUsuarioPorEmail(usuarioDto.getEmail());
        if (usuarioExistente != null && usuarioExistente.getEmail() != null && !usuarioExistente.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Ya existe un usuario registrado con ese email");
        }
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuarioDto);
            return "/auth-api/registro";
        }

        iUsuarioServicio.guardarUsuario(usuarioDto);
        return "redirect:/registro?success";
    }

    // Método que muestra la lista de usuarios
    @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model) {
        List<UsuarioDto> usuarios = iUsuarioServicio.buscarTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
}
