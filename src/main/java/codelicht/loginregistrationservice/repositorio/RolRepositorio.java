package codelicht.loginregistrationservice.repositorio;

import codelicht.loginregistrationservice.entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
