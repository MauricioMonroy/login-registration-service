package codelicht.loginregistrationservice.servicio;

import codelicht.loginregistrationservice.entidad.Usuario;
import codelicht.loginregistrationservice.modelodto.UsuarioDto;

import java.util.List;

/**
 * Interfaz que define los métodos que se pueden realizar sobre un usuario.
 */
public interface IUsuarioServicio {
    void guardarUsuario(UsuarioDto usuarioDto);

    Usuario buscarUsuarioPorEmail(String email);

    List<UsuarioDto> buscarTodosLosUsuarios();
}
