package logica;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import persistencia.GestorArchivos;
import presentacion.DialogoEstadoCritico;

import presentacion.PantallaAnimales;
import presentacion.PantallaJuego;
import presentacion.PantallaMenu;
import presentacion.PantallaRegistro;
import presentacion.PantallaSeleccionMascota;

public class Juego {

    private static final String REGISTRO = "registro";
    private static final String MENU = "menu";
    private static final String ANIMALES = "animales";
    private static final String SELECCION = "seleccion";
    private static final String JUEGO = "juego";

    private final JFrame ventana;
    private final JPanel contenedor;
    private final CardLayout navegacion;
    private final PantallaRegistro pantallaRegistro;
    private final PantallaMenu pantallaMenu;
    private final PantallaAnimales pantallaAnimales;
    private final PantallaSeleccionMascota pantallaSeleccion;
    private final PantallaJuego pantallaJuego;
    private final ArrayList<String> historialAcciones;

    private Usuario usuario;
    private Timer timerDegradacion;
    private boolean estadoCriticoMostrado;

    public Juego() {
        historialAcciones = new ArrayList<>();
        navegacion = new CardLayout();
        contenedor = new JPanel(navegacion);
        ventana = new JFrame("Campus Pets UIS");

        pantallaRegistro = new PantallaRegistro();
        pantallaMenu = new PantallaMenu();
        pantallaAnimales = new PantallaAnimales();
        pantallaSeleccion = new PantallaSeleccionMascota();
        pantallaJuego = new PantallaJuego();

        configurarVentana();
        configurarPantallas();
        configurarEventos();
        iniciarConPartidaGuardada();
    }

    private void configurarVentana() {
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setMinimumSize(new Dimension(760, 560));
        ventana.setSize(860, 640);
        ventana.setLocationRelativeTo(null);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardarPartida();
            }
        });
    }

    private void configurarPantallas() {
        contenedor.add(pantallaRegistro, REGISTRO);
        contenedor.add(pantallaMenu, MENU);
        contenedor.add(pantallaAnimales, ANIMALES);
        contenedor.add(pantallaSeleccion, SELECCION);
        contenedor.add(pantallaJuego, JUEGO);
        ventana.setContentPane(contenedor);
        ventana.setVisible(true);
    }

    private void configurarEventos() {
        pantallaRegistro.getBotonContinuar().addActionListener(e -> registrarUsuario());
        pantallaMenu.getBotonEmpezar().addActionListener(e -> continuarDesdeMenu());
        pantallaMenu.getBotonAnimales().addActionListener(e -> mostrar(ANIMALES));
        pantallaAnimales.getBotonVolver().addActionListener(e -> mostrar(MENU));
        pantallaSeleccion.getBotonVolverMenu().addActionListener(e -> mostrar(MENU));
        pantallaSeleccion.getBotonContinuar().addActionListener(e -> crearMascota());

        pantallaJuego.getBotonVolverMenu().addActionListener(e -> volverAlMenu());
        pantallaJuego.getBotonComer().addActionListener(e -> ejecutarAccion("Comer", PantallaJuego.ESTADO_COMIENDO, () -> usuario.getMascota().comer()));
        pantallaJuego.getBotonJugar().addActionListener(e -> ejecutarAccion("Jugar", PantallaJuego.ESTADO_FELIZ, () -> usuario.getMascota().jugar()));
        pantallaJuego.getBotonDormir().addActionListener(e -> ejecutarAccion("Dormir", PantallaJuego.ESTADO_DURMIENDO, () -> usuario.getMascota().dormir()));
    }

    private void iniciarConPartidaGuardada() {
        if (!GestorArchivos.existenDatos()) {
            mostrar(REGISTRO);
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(
                ventana,
                "Se encontró una partida guardada.\n¿Deseas continuarla?",
                "Partida guardada",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            Usuario usuarioCargado = GestorArchivos.cargarDatos();
            if (usuarioCargado != null && usuarioCargado.getMascota() != null) {
                usuario = usuarioCargado;
                pantallaMenu.configurarUsuario(usuario.getNombre(), usuario.getProgramaAcademico());
                pantallaJuego.configurarUsuario(usuario.getNombre(), usuario.getProgramaAcademico());
                mostrarJuego();
                if (!usuario.getMascota().estaEnEstadoCritico()) {
                    iniciarTimerDegradacion();
                }
                return;
            }

            JOptionPane.showMessageDialog(
                    ventana,
                    "No fue posible cargar la partida guardada.",
                    "Error de carga",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            GestorArchivos.eliminarDatos();
        }

        mostrar(REGISTRO);
    }

    private void registrarUsuario() {
        if (!pantallaRegistro.datosValidos()) {
            return;
        }

        usuario = new Usuario(pantallaRegistro.getNombreUsuario(), pantallaRegistro.getCarrera());
        pantallaMenu.configurarUsuario(usuario.getNombre(), usuario.getProgramaAcademico());
        mostrar(MENU);
    }

    private void continuarDesdeMenu() {
        if (usuario != null && usuario.getMascota() != null) {
            mostrarJuego();
            if (!usuario.getMascota().estaEnEstadoCritico()) {
                iniciarTimerDegradacion();
            }
            return;
        }
        mostrar(SELECCION);
    }

    private void crearMascota() {
        if (!pantallaSeleccion.datosValidos()) {
            return;
        }

        Mascota mascota = pantallaSeleccion.getEspecieSeleccionada().equals("Gato")
                ? new Gato(pantallaSeleccion.getNombreMascota())
                : new Perro(pantallaSeleccion.getNombreMascota());

        usuario.asignarMascota(mascota);
        pantallaJuego.configurarUsuario(usuario.getNombre(), usuario.getProgramaAcademico());
        pantallaJuego.actualizar(mascota);
        pantallaJuego.mostrarMensaje("Tu " + mascota.getEspecie().toLowerCase() + " ya está listo para jugar.");
        mostrarJuego();
        iniciarTimerDegradacion();
    }

    private void ejecutarAccion(String nombreAccion, String estadoVisual, AccionMascota accion) {
        if (usuario == null || usuario.getMascota() == null || estadoCriticoMostrado) {
            return;
        }

        Mascota mascota = usuario.getMascota();
        try {
            accion.ejecutar();
            historialAcciones.add(nombreAccion + " - " + mascota.verEstado());
            pantallaJuego.mostrarMensaje(mensajeAccion(nombreAccion, mascota));
            actualizarJuego(estadoVisual);
        } catch (MascotaException ex) {
            pantallaJuego.mostrarMensaje(ex.getMessage());
            actualizarJuego();
        }
    }

    private String mensajeAccion(String accion, Mascota mascota) {
        if (accion.equals("Comer")) {
            return mascota.getNombre() + " comió y se siente mejor.";
        }
        if (accion.equals("Jugar")) {
            return mascota.getNombre() + " jugó contigo.";
        }
        return mascota.getNombre() + " descansó un rato.";
    }

    private void iniciarTimerDegradacion() {
        detenerTimer();
        timerDegradacion = new Timer(5000, e -> {
            usuario.getMascota().degradarAtributos();
            pantallaJuego.mostrarMensaje("El tiempo pasa: cuida sus barras.");
            actualizarJuego();
        });
        timerDegradacion.start();
    }

    private void actualizarJuego() {
        actualizarJuego(null);
    }

    private void actualizarJuego(String estadoVisual) {
        Mascota mascota = usuario.getMascota();
        if (estadoVisual == null) {
            pantallaJuego.actualizar(mascota);
        } else {
            pantallaJuego.actualizar(mascota, estadoVisual);
        }
        if (mascota.estaEnEstadoCritico()) {
            mostrarEstadoCritico();
        }
    }

    private void mostrarEstadoCritico() {
        if (estadoCriticoMostrado) {
            return;
        }

        estadoCriticoMostrado = true;
        detenerTimer();
        Mascota mascota = usuario.getMascota();
        DialogoEstadoCritico dialogo = new DialogoEstadoCritico(
                ventana,
                mascota.getNombre(),
                this::recuperarMascota,
                this::reiniciarJuego,
                this::volverAlMenuDesdeDialogo);
        dialogo.setVisible(true);
    }

    private void recuperarMascota() {
        Mascota mascota = usuario.getMascota();
        mascota.recuperar();
        estadoCriticoMostrado = false;
        pantallaJuego.mostrarMensaje(mascota.getNombre() + " se recuperó al 60%.");
        actualizarJuego();
        iniciarTimerDegradacion();
    }

    private void volverAlMenu() {
        detenerTimer();
        mostrar(MENU);
    }

    private void volverAlMenuDesdeDialogo() {
        detenerTimer();
        estadoCriticoMostrado = false;
        mostrar(MENU);
    }

    private void mostrarJuego() {
        pantallaJuego.actualizar(usuario.getMascota());
        mostrar(JUEGO);
        if (usuario.getMascota().estaEnEstadoCritico()) {
            mostrarEstadoCritico();
        }
    }

    public void reiniciarJuego() {
        detenerTimer();
        estadoCriticoMostrado = false;
        usuario = null;
        historialAcciones.clear();
        GestorArchivos.eliminarDatos();
        mostrar(REGISTRO);
    }

    private void detenerTimer() {
        if (timerDegradacion != null) {
            timerDegradacion.stop();
        }
    }

    private void guardarPartida() {
        if (usuario != null && usuario.getMascota() != null) {
            GestorArchivos.guardarDatos(usuario);
        }
    }

    private void mostrar(String pantalla) {
        navegacion.show(contenedor, pantalla);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ArrayList<String> getHistorialAcciones() {
        return historialAcciones;
    }

    @FunctionalInterface
    private interface AccionMascota {
        void ejecutar() throws MascotaException;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo cargar el estilo del sistema.");
            }
            new Juego();
        });
    }
}
