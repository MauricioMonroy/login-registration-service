package codelicht.loginregistrationservice.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa la respuesta de la autenticación
 * Contiene el token de autenticación y el tiempo de expiración
 */
@Getter
@Setter
public class LoginResponse {
    private String token;
    private long expiresIn;

    public String getToken() {
        return token;
    }
}
