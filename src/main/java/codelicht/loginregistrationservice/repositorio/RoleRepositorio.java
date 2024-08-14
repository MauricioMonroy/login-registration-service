package codelicht.loginregistrationservice.repositorio;

import codelicht.loginregistrationservice.entidad.Role;
import codelicht.loginregistrationservice.entidad.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Role
 * Extiende de CrudRepository proporcionando los métodos CRUD básicos
 */
@Repository
public interface RoleRepositorio extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
