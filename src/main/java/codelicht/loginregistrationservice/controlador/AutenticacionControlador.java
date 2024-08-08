package codelicht.loginregistrationservice.controlador;

import codelicht.loginregistrationservice.modelodto.UsuarioDto;
import codelicht.loginregistrationservice.servicio.IUsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controlador que maneja las peticiones relacionadas con la autenticación.
 */
@Controller
public class AutenticacionControlador {

    private final IUsuarioServicio iUsuarioServicio;

    // Inyección de dependencias por constructor
    public AutenticacionControlador(IUsuarioServicio iUsuarioServicio) {
        this.iUsuarioServicio = iUsuarioServicio;
    }

    // Método que muestra la página de inicio
    @GetMapping("/index")
    public String home() {
        return "index";
    }

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
        return "registro";
    }

    // Método que guarda un usuario en la base de datos
    @PostMapping("/registro/guardar")
    public String registrarUsuario(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto,
                                   BindingResult result,
                                   Model model) {
        try {
            iUsuarioServicio.guardarUsuario(usuarioDto);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.usuario", e.getMessage());
        }

        if (result.hasErrors()) {
            model.addAttribute("usuario", usuarioDto);
            return "/registro";
        }

        return "redirect:/registro?success";
    }

    // Método que muestra la lista de usuarios
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        List<UsuarioDto> usuarios = iUsuarioServicio.buscarTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
}
