package codelicht.loginregistrationservice.servicio;

import codelicht.loginregistrationservice.dtos.RegisterUserDto;
import codelicht.loginregistrationservice.entidad.Role;
import codelicht.loginregistrationservice.entidad.RoleEnum;
import codelicht.loginregistrationservice.entidad.User;
import codelicht.loginregistrationservice.repositorio.RoleRepositorio;
import codelicht.loginregistrationservice.repositorio.UserRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la entidad User
 * Proporciona métodos para obtener todos los usuarios
 */
@Service
public class UserService {
    private final UserRepositorio userRepositorio;
    private final RoleRepositorio roleRepositorio;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositorio userRepositorio,
                       RoleRepositorio roleRepositorio,
                       PasswordEncoder passwordEncoder) {
        this.userRepositorio = userRepositorio;
        this.roleRepositorio = roleRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepositorio.findAll().forEach(users::add);

        return users;
    }

    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepositorio.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepositorio.save(user);

    }
}
