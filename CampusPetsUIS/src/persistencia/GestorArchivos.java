package persistencia;

import logica.Gato;
import logica.Mascota;
import logica.Perro;
import logica.Usuario;

import java.io.*;

public class GestorArchivos {

    private static final String ARCHIVO = "datos_juego.txt";

    // ===== GUARDAR =====
    public static void guardarDatos(Usuario usuario) {
        try {
            FileWriter fw = new FileWriter(ARCHIVO);
            BufferedWriter bw = new BufferedWriter(fw);

            Mascota m = usuario.getMascota();

            // Formato: nombre;programa;tipoMascota;nombreMascota;hambre;energia;felicidad
            String linea = usuario.getNombre() + ";"
                    + usuario.getProgramaAcademico() + ";"
                    + m.getEspecie() + ";"
                    + m.getNombre() + ";"
                    + m.getHambre() + ";"
                    + m.getEnergia() + ";"
                    + m.getFelicidad();

            bw.write(linea);
            bw.close();
            fw.close();

            System.out.println("Datos guardados correctamente en " + ARCHIVO);

        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    // ===== CARGAR =====
    public static Usuario cargarDatos() {
        File archivo = new File(ARCHIVO);

        // Si no existe el archivo, retorna null
        if (!archivo.exists()) {
            System.out.println("No hay datos guardados previos.");
            return null;
        }

        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();
            br.close();
            fr.close();

            if (linea == null || linea.trim().isEmpty()) {
                return null;
            }

            // Separar por ";"
            String[] datos = linea.split(";");

            String nombreUsuario = datos[0];
            String programa = datos[1];
            String tipoMascota = datos[2];
            String nombreMascota = datos[3];
            int hambre = Integer.parseInt(datos[4]);
            int energia = Integer.parseInt(datos[5]);
            int felicidad = Integer.parseInt(datos[6]);

            // Reconstruir objetos
            Usuario usuario = new Usuario(nombreUsuario, programa);

            Mascota mascota;
            if (tipoMascota.equals("Gato")) {
                mascota = new Gato(nombreMascota);
            } else {
                mascota = new Perro(nombreMascota);
            }

            mascota.setHambre(hambre);
            mascota.setEnergia(energia);
            mascota.setFelicidad(felicidad);

            usuario.asignarMascota(mascota);

            System.out.println("Datos cargados correctamente.");
            return usuario;

        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
            return null;
        }
    }

    // ===== ELIMINAR =====
    public static boolean eliminarDatos() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            archivo.delete();
            System.out.println("Datos eliminados correctamente.");
            return true;
        }
        System.out.println("No hay datos que eliminar.");
        return false;
    }

    // ===== VERIFICAR SI EXISTE =====
    public static boolean existenDatos() {
        return new File(ARCHIVO).exists();
    }
}