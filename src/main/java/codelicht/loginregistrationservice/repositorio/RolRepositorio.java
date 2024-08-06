package codelicht.loginregistrationservice.repositorio;

import codelicht.loginregistrationservice.entidad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que define los métodos que se pueden realizar sobre un rol.
 */
public interface RolRepositorio extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
