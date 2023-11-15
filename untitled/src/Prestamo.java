import java.util.Date;
import java.util.List;

class Prestamo {
    private Usuario usuario;
    private List<Material> materiales;
    private int diasEntrega;
    private Date fechaSolicitud;
    private String direccionEnvio;
    private boolean entregaTemprana;

    public Prestamo(Usuario usuario, List<Material> materiales, int diasEntrega, Date fechaSolicitud, String direccionEnvio, boolean entregaTemprana) {
        this.usuario = usuario;
        this.materiales = materiales;
        this.diasEntrega = diasEntrega;
        this.fechaSolicitud = fechaSolicitud;
        this.direccionEnvio = direccionEnvio;
        this.entregaTemprana = entregaTemprana;
    }

    public void imprimirListado() {
        System.out.println("Listado de préstamo:");
        for (Material material : materiales) {
            System.out.println("- " + material.titulo);
        }
    }


}

