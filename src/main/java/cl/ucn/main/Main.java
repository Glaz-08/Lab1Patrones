package cl.ucn.main;

import cl.ucn.modelo.*;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();
        EntityManager em = emf.createEntityManager();

        // Patrón Strategy: Buscamos primero en la base de datos, si no, en el CSV
        BusquedaUsuarioStrategy busquedaBaseDatos = new BusquedaBaseDatosStrategy(em);
        BusquedaUsuarioStrategy busquedaCSV = new BusquedaCSVStrategy("usuarios.csv");

        int rut = 89830291;
        Optional<Usuario> usuarioOpt = busquedaBaseDatos.buscarUsuario(rut);

        if (usuarioOpt.isEmpty()) {
            usuarioOpt = busquedaCSV.buscarUsuario(rut);
        }

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            System.out.println("El usuario: " + usuario.getRut() + " existe!");

            // Usamos el proxy para controlar el acceso a recursos protegidos
            RecursosMultimedia recurso = usuario.getRecursosMultimedia();
            RecursosMultimediaProxy proxy = new RecursosMultimediaProxy(recurso, usuario);
            proxy.cargar();
            proxy.mostrar();

        } else {
            System.out.println("El usuario no existe!");
        }

        em.close();
        EntityManagerFactorySingleton.close();
    }
}


