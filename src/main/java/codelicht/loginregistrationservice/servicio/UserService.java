package codelicht.loginregistrationservice.servicio;

import codelicht.loginregistrationservice.entidad.User;
import codelicht.loginregistrationservice.repositorio.UserRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la entidad User
 * Proporciona métodos para obtener todos los usuarios
 */
@Service
public class UserService {
    private final UserRepositorio userRepositorio;

    public UserService(UserRepositorio userRepositorio) {
        this.userRepositorio = userRepositorio;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepositorio.findAll().forEach(users::add);

        return users;
    }
}
