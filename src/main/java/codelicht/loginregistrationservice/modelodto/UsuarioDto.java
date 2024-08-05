package codelicht.loginregistrationservice.modelodto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un objeto de transferencia de datos de Usuario
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    @NotEmpty
    private String nombreUsuario;
    @NotEmpty
    private String apellidoUsuario;
    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "No es un email válido")
    private String email;
    @NotEmpty
    private String password;
}
