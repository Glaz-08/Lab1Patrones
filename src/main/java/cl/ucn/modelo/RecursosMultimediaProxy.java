package cl.ucn.modelo;

// Clase Proxy 
public class RecursosMultimediaProxy extends RecursosMultimedia {

    private RecursosMultimedia recursoReal;
    private Usuario usuario;

    
    public RecursosMultimediaProxy(RecursosMultimedia recursoReal, Usuario usuario) {
        this.recursoReal = recursoReal;
        this.usuario = usuario;
    }

    @Override
    public void cargar() {
        if (usuario.isTienePermiso()) {
            recursoReal.cargar();
        } else {
            System.out.println("Acceso denegado: El usuario " + usuario.getRut() + " no tiene permisos para cargar este archivo.");
        }
    }

    @Override
    public void mostrar() {
        if (usuario.isTienePermiso()) {
            recursoReal.mostrar();
        } else {
            System.out.println("Acceso denegado: El usuario " + usuario.getRut() + " no tiene permisos para visualizar este archivo.");
        }
    }
}
