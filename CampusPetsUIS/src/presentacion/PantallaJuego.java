package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import logica.Mascota;

public class PantallaJuego extends JPanel {

    public static final String ESTADO_FELIZ = "feliz";
    public static final String ESTADO_TRISTE = "triste";
    public static final String ESTADO_COMIENDO = "comiendo";
    public static final String ESTADO_DURMIENDO = "durmiendo";

    private final JLabel etiquetaUsuario;
    private final JLabel etiquetaMascota;
    private final JLabel etiquetaImagenMascota;
    private final JLabel etiquetaMensaje;
    private final JProgressBar barraHambre;
    private final JProgressBar barraEnergia;
    private final JProgressBar barraFelicidad;
    private final JButton botonComer;
    private final JButton botonJugar;
    private final JButton botonDormir;
    private final JButton botonVolverMenu;

    public PantallaJuego() {
        setLayout(new BorderLayout(14, 14));
        setBackground(EstiloUI.FONDO);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 18, 22));

        etiquetaUsuario = EstiloUI.texto(" ", 14);
        etiquetaMascota = EstiloUI.titulo("Mascota", 30);
        etiquetaImagenMascota = new JLabel("", JLabel.CENTER);
        etiquetaImagenMascota.setPreferredSize(new java.awt.Dimension(260, 220));
        etiquetaMensaje = EstiloUI.texto("Cuida a tu mascota.", 15);
        barraHambre = EstiloUI.barra(EstiloUI.VERDE);
        barraEnergia = EstiloUI.barra(EstiloUI.VERDE);
        barraFelicidad = EstiloUI.barra(EstiloUI.VERDE);
        botonComer = EstiloUI.boton("Comer", EstiloUI.VERDE);
        botonJugar = EstiloUI.boton("Jugar", EstiloUI.VERDE);
        botonDormir = EstiloUI.boton("Dormir", EstiloUI.VERDE);
        botonVolverMenu = EstiloUI.boton("Volver al menú", EstiloUI.VERDE);

        add(crearCabecera(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearCabecera() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(etiquetaUsuario, BorderLayout.NORTH);
        panel.add(etiquetaMascota, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearCentro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        JPanel tarjeta = EstiloUI.tarjeta();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        etiquetaImagenMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.add(etiquetaImagenMascota);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(filaEstado("Hambre", barraHambre));
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(filaEstado("Energía", barraEnergia));
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(filaEstado("Felicidad", barraFelicidad));
        tarjeta.add(Box.createVerticalStrut(18));
        tarjeta.add(etiquetaMensaje);

        panel.add(tarjeta, new GridBagConstraints());
        return panel;
    }

    private JPanel filaEstado(String texto, JProgressBar barra) {
        JPanel fila = new JPanel(new GridLayout(1, 2, 18, 0));
        fila.setOpaque(false);
        JLabel label = EstiloUI.texto(texto, 16);
        label.setHorizontalAlignment(JLabel.RIGHT);
        fila.add(label);
        fila.add(barra);
        return fila;
    }

    private JPanel crearBotones() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 16, 0));
        panel.setOpaque(false);
        panel.add(botonVolverMenu);
        panel.add(botonComer);
        panel.add(botonJugar);
        panel.add(botonDormir);
        return panel;
    }

    public void configurarUsuario(String nombre, String carrera) {
        etiquetaUsuario.setText(nombre + " | " + carrera);
    }

    public void actualizar(Mascota mascota) {
        actualizar(mascota, estadoAutomatico(mascota));
    }

    public void actualizar(Mascota mascota, String estadoVisual) {
        etiquetaMascota.setText(mascota.getNombre() + " - " + mascota.getEspecie());
        actualizarBarra(barraHambre, mascota.getHambre());
        actualizarBarra(barraEnergia, mascota.getEnergia());
        actualizarBarra(barraFelicidad, mascota.getFelicidad());
        actualizarImagen(mascota, resolverEstadoVisual(mascota, estadoVisual));
    }

    private void actualizarBarra(JProgressBar barra, int valor) {
        barra.setValue(valor);
        barra.setString(valor + "%");
    }

    private String estadoAutomatico(Mascota mascota) {
        if (mascota.getHambre() < 30 || mascota.getEnergia() < 30 || mascota.getFelicidad() < 30) {
            return ESTADO_TRISTE;
        }
        return ESTADO_FELIZ;
    }

    private String resolverEstadoVisual(Mascota mascota, String estadoVisual) {
        if (mascota.getHambre() < 30 || mascota.getEnergia() < 30 || mascota.getFelicidad() < 30) {
            return ESTADO_TRISTE;
        }
        return estadoVisual;
    }

    private void actualizarImagen(Mascota mascota, String estadoVisual) {
        String especie = mascota.getEspecie().equalsIgnoreCase("Perro") ? "perro" : "gato";
        String nombreArchivo = especie + "_" + estadoVisual + ".png";
        etiquetaImagenMascota.setIcon(EstiloUI.cargarImagen(nombreArchivo, 260, 220));
        etiquetaImagenMascota.setText(etiquetaImagenMascota.getIcon() == null ? mascota.getEspecie() : "");
    }

    public void mostrarMensaje(String mensaje) {
        etiquetaMensaje.setText(mensaje);
    }

    public JButton getBotonComer() {
        return botonComer;
    }

    public JButton getBotonJugar() {
        return botonJugar;
    }

    public JButton getBotonDormir() {
        return botonDormir;
    }

    public JButton getBotonVolverMenu() {
        return botonVolverMenu;
    }
}
