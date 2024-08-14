package codelicht.loginregistrationservice.arranque;

import codelicht.loginregistrationservice.entidad.Role;
import codelicht.loginregistrationservice.entidad.RoleEnum;
import codelicht.loginregistrationservice.repositorio.RoleRepositorio;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Clase para crear los roles en la base de datos
 * Implementa ApplicationListener para ser ejecutado al iniciar la aplicación
 */
@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepositorio roleRepositorio;

    public RoleSeeder(RoleRepositorio roleRepositorio) {
        this.roleRepositorio = roleRepositorio;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN};
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Rol de usuario",
                RoleEnum.ADMIN, "Rol de administrador",
                RoleEnum.SUPER_ADMIN, "Rol de super administrador"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = this.roleRepositorio.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleCreated = new Role();

                roleCreated.setName(roleName);
                roleCreated.setDescription(roleDescriptionMap.get(roleName));

                roleRepositorio.save(roleCreated);
            });
        });
    }
}
