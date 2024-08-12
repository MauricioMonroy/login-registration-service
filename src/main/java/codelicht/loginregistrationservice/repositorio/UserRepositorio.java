package codelicht.loginregistrationservice.repositorio;

import codelicht.loginregistrationservice.entidad.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad User
 * Extiende de CrudRepository proporcionando los métodos CRUD básicos
 */
@Repository
public interface UserRepositorio extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
