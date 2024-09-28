package codelicht.loginregistrationservice.arranque;

import codelicht.loginregistrationservice.dtos.RegisterUserDto;
import codelicht.loginregistrationservice.entidad.Role;
import codelicht.loginregistrationservice.entidad.RoleEnum;
import codelicht.loginregistrationservice.entidad.User;
import codelicht.loginregistrationservice.repositorio.RoleRepositorio;
import codelicht.loginregistrationservice.repositorio.UserRepositorio;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Clase para crear el superadministrador en la base de datos
 * Implementa ApplicationListener para ser ejecutado al iniciar la aplicación
 */
@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepositorio roleRepositorio;
    private final UserRepositorio userRepositorio;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(RoleRepositorio roleRepositorio,
                       UserRepositorio userRepositorio,
                       PasswordEncoder passwordEncoder) {
        this.roleRepositorio = roleRepositorio;
        this.userRepositorio = userRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setFullName("Super Admin");
        userDto.setEmail("super.admin@correo.com");
        userDto.setPassword("superadmin");

        Optional<Role> optionalRole = roleRepositorio.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepositorio.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());

        userRepositorio.save(user);
    }

}
