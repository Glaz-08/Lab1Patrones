package cl.ucn.modelo;

import java.util.Optional;

public interface BusquedaUsuarioStrategy {
    Optional<Usuario> buscarUsuario(int rut);
}