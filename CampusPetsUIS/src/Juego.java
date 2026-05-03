import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Juego {

    private Usuario usuario;
    private PantallaJuego pantalla;
    private Timer timerDegradacion;
    private Timer timerMensajes;
    private boolean juegoActivo;

    private static final String[] MENSAJES_UIS = {
        "¿Sabías que en la UIS hay varios animales sin hogar que necesitan ayuda?",
        "Puedes reportar animales en situación de calle al bienestar universitario UIS.",
        "Adoptar un animal es un acto de amor. ¡Considera adoptar!",
        "Los animales del campus UIS merecen cuidado y atención.",
        "¡Cuida a tu mascota virtual como cuidarías a un animal real!"
    };
    private int indiceMensaje = 0;

    public Juego() {
        iniciarRegistro();
    }

    private void iniciarRegistro() {
        // Pedir nombre del usuario
        String nombre = JOptionPane.showInputDialog(null,
                "¡Bienvenido a Campus Pets UIS! 🐾\nIngresa tu nombre:",
                "Registro", JOptionPane.QUESTION_MESSAGE);

        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = "Estudiante";
        }

        // Pedir programa académico
        String[] programas = {"Ingeniería de Sistemas", "Ingeniería Civil",
            "Ingeniería Mecánica", "Medicina", "Derecho", "Otro"};
        String programa = (String) JOptionPane.showInputDialog(null,
                "Selecciona tu programa académico:",
                "Registro", JOptionPane.QUESTION_MESSAGE,
                null, programas, programas[0]);

        if (programa == null) {
            programa = "Otro";
        }

        usuario = new Usuario(nombre, programa);
        usuario.registrarPerfil();

        // Elegir tipo de mascota
        String[] tipos = {"Gato", "Perro"};
        String tipoElegido = (String) JOptionPane.showInputDialog(null,
                "¡Hola " + nombre + "! ¿Qué mascota quieres adoptar?",
                "Selección de mascota", JOptionPane.QUESTION_MESSAGE,
                null, tipos, tipos[0]);

        if (tipoElegido == null) {
            tipoElegido = "Gato";
        }

        // Pedir nombre de la mascota
        String nombreMascota = JOptionPane.showInputDialog(null,
                "¿Cómo se llamará tu " + tipoElegido + "?",
                "Nombre de mascota", JOptionPane.QUESTION_MESSAGE);

        if (nombreMascota == null || nombreMascota.trim().isEmpty()) {
            nombreMascota = tipoElegido.equals("Gato") ? "Michi" : "Firulais";
        }

        // Crear mascota según elección
        Mascota mascota;
        if (tipoElegido.equals("Gato")) {
            mascota = new Gato(nombreMascota);
        } else {
            mascota = new Perro(nombreMascota);
        }

        usuario.asignarMascota(mascota);

        // Crear pantalla
        pantalla = new PantallaJuego(this);
        pantalla.configurarInfoUsuario(usuario.getNombre(), usuario.getProgramaAcademico());
        pantalla.configurarInfoMascota(mascota.getNombre(), mascota.getEspecie());
        pantalla.actualizarBarras();
        pantalla.setVisible(true);

        juegoActivo = true;
        iniciarTimers();
    }

    private void iniciarTimers() {
        // Timer degradacion cada 10 segundos
        timerDegradacion = new Timer();
        timerDegradacion.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (juegoActivo) {
                    usuario.getMascota().degradarAtributos();
                    SwingUtilities.invokeLater(() -> {
                        pantalla.actualizarBarras();
                        verificarEstadoCritico();
                    });
                }
            }
        }, 10000, 10000);

        // Timer mensajes educativos cada 15 segundos
        timerMensajes = new Timer();
        timerMensajes.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    pantalla.mostrarMensaje(MENSAJES_UIS[indiceMensaje]);
                    indiceMensaje = (indiceMensaje + 1) % MENSAJES_UIS.length;
                });
            }
        }, 15000, 15000);
    }

    public void verificarEstadoCritico() {
        Mascota m = usuario.getMascota();
        if (m.getHambre() == 0 && m.getEnergia() == 0 && m.getFelicidad() == 0) {
            juegoActivo = false;
            timerDegradacion.cancel();
            timerMensajes.cancel();

            int opcion = JOptionPane.showConfirmDialog(pantalla,
                    "⚠️ " + m.getNombre() + " está en estado crítico!\n"
                    + "¿Deseas recuperarla al 50%?",
                    "Estado Crítico", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                m.setHambre(50);
                m.setEnergia(50);
                m.setFelicidad(50);
                juegoActivo = true;
                iniciarTimers();
                pantalla.actualizarBarras();
                pantalla.mostrarMensaje("¡" + m.getNombre() + " se ha recuperado!");
            } else {
                reiniciarJuego();
            }
        }
    }

    public void reiniciarJuego() {
        pantalla.dispose();
        new Juego();
    }

    // Acciones de los botones
    public void accionComer() {
        usuario.getMascota().comer();
    }

    public void accionJugar() {
        usuario.getMascota().jugar();
    }

    public void accionDormir() {
        usuario.getMascota().dormir();
    }

    public void accionSonido() {
        usuario.getMascota().hacerSonido();
    }

    // Getter
    public Usuario getUsuario() {
        return usuario;
    }

    // Main - punto de entrada del juego
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Juego());
    }
}