

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class BibliotecaDemo {

    private static final String USUARIOS_CSV_FILE = "usuarios.csv";
    private static final String PRESTAMOS_CSV_FILE = "prestamos.csv";
    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Libro> libros = new ArrayList<>();
    private static List<Revista> revistas = new ArrayList<>();
    private static List<Prestamo> prestamos = new ArrayList<>();

    public static void main(String[] args) {
        cargarDatosPrueba();

        Usuario usuarioLogueado = null;

        while (true) {
            if (usuarioLogueado == null) {
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesión");
                System.out.println("0. Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 1) {
                    registrarUsuario();
                } else if (opcion == 2) {
                    usuarioLogueado = iniciarSesion();
                } else if (opcion == 0) {
                    break;
                }
            } else {
                if (usuarioLogueado.getTipoCliente().equals("Gratis")) {
                    System.out.println("1. Seleccionar libros");
                    System.out.println("2. Solicitar préstamo");
                    System.out.println("3. Cambiar tipo de cliente");
                    System.out.println("0. Cerrar sesión");

                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion == 1) {
                        seleccionarLibros(usuarioLogueado);
                    } else if (opcion == 2) {
                        solicitarPrestamo(usuarioLogueado);
                    } else if (opcion == 3) {
                        cambiarTipoCliente(usuarioLogueado);
                    } else if (opcion == 0) {
                        System.out.println("Sesión cerrada.");
                        usuarioLogueado = null;
                    }

                } else {
                    System.out.println("1. Seleccionar libros");
                    System.out.println("2. Solicitar préstamo");
                    System.out.println("3. Canjear cupón descuento");
                    System.out.println("0. Cerrar sesión");

                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion == 1) {
                        seleccionarLibros(usuarioLogueado);
                    } else if (opcion == 2) {
                        solicitarPrestamo(usuarioLogueado);
                    } else if (opcion == 3) {
                        canjearCupon(usuarioLogueado);
                    } else if (opcion == 0) {
                        System.out.println("Sesión cerrada.");
                        usuarioLogueado = null;
                    }
                }
            }
        }

        scanner.close();
    }

    public static void cargarDatosPrueba() {
        // Implementar la carga de datos iniciales
        // Agregar algunos usuarios de prueba
        Usuario usuario1 = new Usuario("usuario1", "contraseña1", "Gratis");
        Usuario usuario2 = new Usuario("usuario2", "contraseña2", "Premium");

        usuarios.add(usuario1);
        usuarios.add(usuario2);

        // Agregar algunos libros de prueba
        Libro libro1 = new Libro("Libro 1", "Autor 1", 10, 20.0);
        Libro libro2 = new Libro("Libro 2", "Autor 2", 5, 15.0);

        libros.add(libro1);
        libros.add(libro2);

        // Agregar algunas revistas de prueba
        Revista revista1 = new Revista("Revista 1", 8, 10.0);
        Revista revista2 = new Revista("Revista 2", 12, 8.0);

        revistas.add(revista1);
        revistas.add(revista2);


    }

    public static void registrarUsuario() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        System.out.print("Seleccione tipo de cliente (Gratis/Premium): ");
        String tipoCliente = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario(nombre, contrasena, tipoCliente);
        usuarios.add(nuevoUsuario);
        System.out.println("Registro exitoso!");
    }

    public static Usuario iniciarSesion() {
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre) && u.getContraseña().equals(contrasena)) {
                System.out.println("Inicio de sesión exitoso.");
                return u;
            }
        }

        System.out.println("Credenciales inválidas");
        return null;
    }

    public static void seleccionarLibros(Usuario usuario) {
        List<Libro> librosDisponibles = obtenerLibrosDisponibles();

        if (librosDisponibles.isEmpty()) {
            System.out.println("No hay libros disponibles para seleccionar.");
            return;
        }

        List<Libro> librosSeleccionados = new ArrayList<>();
        int maxLibros = (usuario.getTipoCliente().equals("Premium")) ? 5 : 3;

        System.out.println("Seleccione libros (máximo " + maxLibros + "): ");
        for (int i = 0; i < maxLibros; i++) {
            mostrarLibros(librosDisponibles);

            System.out.print("Ingrese el número del libro a seleccionar (0 para finalizar): ");
            int seleccion = scanner.nextInt();

            if (seleccion == 0) {
                break;
            }

            if (seleccion > 0 && seleccion <= librosDisponibles.size()) {
                librosSeleccionados.add(librosDisponibles.get(seleccion - 1));
                librosDisponibles.remove(seleccion - 1);
            } else {
                System.out.println("Número de libro no válido.");
            }
        }

        if (librosSeleccionados.isEmpty()) {
            System.out.println("No se ha seleccionado ningún libro. No es posible procesar el préstamo.");
        } else {
            // Mostrar resumen de libros seleccionados (puedes implementarlo)
        }
    }

    // ... (Resto del código)
        // Mostrar resumen de libros seleccionados (puedes implementarlo)


    public static void solicitarPrestamo(Usuario usuario) {
        if (!usuario.getTipoCliente().equals("Premium")) {
            System.out.print("Ingrese días de préstamo (máximo 30): ");
            int dias = scanner.nextInt();

            if (dias > 30) {
                System.out.println("Máximo 30 días para cliente gratis");
                return;
            }
        } else {
            System.out.print("Ingrese días de préstamo (máximo 50): ");
            int dias = scanner.nextInt();

            if (dias > 50) {
                System.out.println("Máximo 50 días para cliente premium");
                return;
            }

            System.out.print("Ingrese dirección de envío: ");
            String direccionEnvio = scanner.nextLine();

            System.out.print("¿Desea entrega temprana? (true/false): ");
            boolean entregaTemprana = scanner.nextBoolean();

            // Resto de la lógica para el préstamo
            List<Material> materiales = new ArrayList<>();
            Prestamo prestamo = new Prestamo(usuario, materiales, dias, new Date(), direccionEnvio, entregaTemprana);

            prestamo.imprimirListado();
            usuario.agregarPrestamo(prestamo);
        }



    }

    public static void cambiarTipoCliente(Usuario usuario) {
        System.out.print("Ingrese el nuevo tipo de cliente (Gratis/Premium): ");
        String nuevoTipo = scanner.nextLine();
        usuario.cambiarTipoCliente(nuevoTipo);
        System.out.println("Tipo de cliente cambiado exitosamente.");
    }

    public static void canjearCupon(Usuario usuario) {
        System.out.print("Ingrese el código del cupón: ");
        String codigoCupon = scanner.nextLine();

        if (validarCupon(codigoCupon)) {
            System.out.println("Cupón válido. Se han aplicado 15 días adicionales a la membresía Premium.");
            // Lógica para aplicar el cupón
        } else {
            System.out.println("Cupón no válido.");
        }
    }

    private static List<Libro> obtenerLibrosDisponibles() {
        // Lógica para obtener la lista de libros disponibles (puedes implementarlo)
        return new ArrayList<>();
    }

    private static void mostrarLibros(List<Libro> libros) {
        System.out.println("Libros disponibles:");
        for (int i = 0; i < libros.size(); i++) {
            System.out.println((i + 1) + ". " + libros.get(i).getTitulo());
        }
    }

    private static boolean validarCupon(String codigoCupon) {
        // Lógica para validar el cupón (puedes implementarlo)
        return false;
    }

}
