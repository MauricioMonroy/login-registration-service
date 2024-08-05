package codelicht.loginregistrationservice.repositorio;

import codelicht.loginregistrationservice.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que define los métodos que se pueden realizar sobre un usuario.
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
