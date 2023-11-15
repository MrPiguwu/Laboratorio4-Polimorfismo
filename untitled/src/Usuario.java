import java.util.ArrayList;
import java.util.List;

class Usuario {
    private String nombre;
    private String contraseña;
    private String tipoCliente;
    private List<Prestamo> prestamos;

    public Usuario(String nombre, String contraseña, String tipoCliente) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.tipoCliente = tipoCliente;
        this.prestamos = new ArrayList<>();


    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void cambiarTipoCliente(String nuevoTipo) {
        this.tipoCliente = nuevoTipo;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }


}

