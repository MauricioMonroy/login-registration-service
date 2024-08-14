package codelicht.loginregistrationservice.servicio;

import codelicht.loginregistrationservice.dtos.LoginUserDto;
import codelicht.loginregistrationservice.dtos.RegisterUserDto;
import codelicht.loginregistrationservice.entidad.Role;
import codelicht.loginregistrationservice.entidad.RoleEnum;
import codelicht.loginregistrationservice.entidad.User;
import codelicht.loginregistrationservice.repositorio.RoleRepositorio;
import codelicht.loginregistrationservice.repositorio.UserRepositorio;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para la autenticación de usuarios
 * Proporciona métodos para registrar y autenticar usuarios
 */
@Service
public class AuthenticationService {
    private final UserRepositorio userRepositorio;
    private final RoleRepositorio RoleRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepositorio userRepositorio,
                                 RoleRepositorio RoleRepositorio,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.userRepositorio = userRepositorio;
        this.RoleRepositorio = RoleRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    // Métodos de registro y autenticación
    public User signUp(RegisterUserDto input) {
        Optional<Role> optionalRole = RoleRepositorio.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepositorio.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepositorio.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
