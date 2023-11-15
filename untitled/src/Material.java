abstract class Material {
    protected String titulo;
    protected int cantidad;
    protected double precio;
    public Material(String titulo, int cantidad, double precio) {
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}

class Libro extends Material {
    private String autor;

    public Libro(String titulo, String autor, int cantidad, double precio) {
        super(titulo, cantidad, precio);
        this.autor = autor;
    }
    public String getAutor() {
        return this.autor;
    }
    public String getTitulo() {
        return this.titulo;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    public double getPrecio() {
        return (int) (int) this.precio;
    }
}

class Revista extends Material {
    public Revista(String titulo, int cantidad, double precio) {
        super(titulo, cantidad, precio);
    }
    public String getTitulo() {
        return this.titulo;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    public double getPrecio() {
        return (int) (int) this.precio;
    }
}