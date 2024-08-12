package codelicht.loginregistrationservice.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para el registro de un usuario
 * Realiza la transferencia de datos entre el controlador y el servicio
 */
@Getter
@Setter
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;

}
