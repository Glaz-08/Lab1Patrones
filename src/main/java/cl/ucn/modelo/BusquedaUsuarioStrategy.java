package cl.ucn.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class BusquedaCSVStrategy implements BusquedaUsuarioStrategy {

    private String rutaArchivo;

    public BusquedaCSVStrategy(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public Optional<Usuario> buscarUsuario(int rut) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                int rutArchivo = Integer.parseInt(campos[0]);
                if (rutArchivo == rut) {
                    Usuario usuario = new Usuario();
                    usuario.setRut(rutArchivo);
                    usuario.setNombre(campos[1]);
                    usuario.setTienePermiso(Boolean.parseBoolean(campos[2]));
                    // Aquí podrías asignar el recurso multimedia si fuera necesario
                    return Optional.of(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
