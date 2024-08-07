package codelicht.loginregistrationservice.controlador;

import codelicht.loginregistrationservice.modelodto.UsuarioDto;
import codelicht.loginregistrationservice.servicio.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
    private final AuthenticationManager authenticationManager;

    // Inyección de dependencias por constructor
    public AutenticacionControlador(IUsuarioServicio iUsuarioServicio, AuthenticationManager authenticationManager) {
        this.iUsuarioServicio = iUsuarioServicio;
        this.authenticationManager = authenticationManager;
    }

    // Método que muestra la página de inicio
    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    // Método para manejar la petición de inicio de sesión
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            Authentication authentication = authenticationManager.authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "Inicio de sesión exitoso";
        } catch (Exception e) {
            return "Error en el inicio de sesión: " + e.getMessage();
        }
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
        try {
            iUsuarioServicio.guardarUsuario(usuarioDto);
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.usuario", e.getMessage());
        }

        if (result.hasErrors()) {
            model.addAttribute("usuario", usuarioDto);
            return "registrar";
        }

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
