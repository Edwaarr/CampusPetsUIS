package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import logica.Mascota;

public class PantallaJuego extends JPanel {

    public static final String ESTADO_FELIZ = "feliz";
    public static final String ESTADO_TRISTE = "triste";
    public static final String ESTADO_COMIENDO = "comiendo";
    public static final String ESTADO_DURMIENDO = "durmiendo";

    private final JLabel etiquetaUsuario;
    private final JLabel etiquetaMascota;
    private final JLabel etiquetaImagenMascota;
    private final JLabel etiquetaEstadoMascota;
    private final JLabel etiquetaMensaje;
    private final JLabel valorHambre;
    private final JLabel valorEnergia;
    private final JLabel valorFelicidad;
    private final JProgressBar barraHambre;
    private final JProgressBar barraEnergia;
    private final JProgressBar barraFelicidad;
    private final JButton botonComer;
    private final JButton botonJugar;
    private final JButton botonDormir;
    private final JButton botonVolverMenu;

    public PantallaJuego() {
        setLayout(new BorderLayout(28, 28));
        setBackground(EstiloUI.FONDO);
        setBorder(BorderFactory.createEmptyBorder(26, 32, 28, 32));

        etiquetaUsuario = EstiloUI.texto(" ", 14);
        etiquetaMascota = EstiloUI.titulo("Mascota", 28);
        etiquetaImagenMascota = new JLabel("", JLabel.CENTER);
        etiquetaImagenMascota.setPreferredSize(new Dimension(360, 300));
        etiquetaEstadoMascota = EstiloUI.texto("Feliz", 28);
        etiquetaMensaje = EstiloUI.titulo("¡Bienvenido! Cuida bien de tu mascota", 24);
        valorHambre = crearValor();
        valorEnergia = crearValor();
        valorFelicidad = crearValor();
        barraHambre = crearBarraJuego();
        barraEnergia = crearBarraJuego();
        barraFelicidad = crearBarraJuego();
        botonComer = EstiloUI.boton("Comer", EstiloUI.VERDE);
        botonJugar = EstiloUI.boton("Jugar", EstiloUI.VERDE);
        botonDormir = EstiloUI.boton("Dormir", EstiloUI.VERDE);
        botonVolverMenu = EstiloUI.boton("Menú", EstiloUI.VERDE);

        add(crearCabecera(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearCabecera() {
        JPanel panel = new PanelRedondeado(18);
        panel.setLayout(new BorderLayout());
        panel.setBackground(new java.awt.Color(0xD9F4DF));
        panel.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));
        panel.add(etiquetaMensaje, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearCentro() {
        JPanel panel = new JPanel(new BorderLayout(28, 0));
        panel.setOpaque(false);
        panel.add(crearTarjetaMascota(), BorderLayout.CENTER);
        panel.add(crearTarjetaIndicadores(), BorderLayout.EAST);
        return panel;
    }

    private JPanel crearTarjetaMascota() {
        JPanel tarjeta = new PanelRedondeado(24);
        tarjeta.setLayout(new GridBagLayout());
        tarjeta.setBackground(EstiloUI.PANEL);
        tarjeta.setBorder(BorderFactory.createEmptyBorder(36, 42, 36, 42));

        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaImagenMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaEstadoMascota.setAlignmentX(Component.CENTER_ALIGNMENT);

        contenido.add(etiquetaUsuario);
        contenido.add(Box.createVerticalStrut(10));
        contenido.add(etiquetaMascota);
        contenido.add(Box.createVerticalStrut(22));
        contenido.add(etiquetaImagenMascota);
        contenido.add(Box.createVerticalStrut(14));
        contenido.add(etiquetaEstadoMascota);

        tarjeta.add(contenido, new GridBagConstraints());
        return tarjeta;
    }

    private JPanel crearTarjetaIndicadores() {
        JPanel tarjeta = new PanelRedondeado(24);
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(EstiloUI.PANEL);
        tarjeta.setBorder(BorderFactory.createEmptyBorder(34, 30, 34, 30));
        tarjeta.setPreferredSize(new Dimension(375, 0));

        JLabel titulo = EstiloUI.titulo("Indicadores", 30);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(28));
        tarjeta.add(filaIndicador("Hambre", valorHambre, barraHambre));
        tarjeta.add(Box.createVerticalStrut(30));
        tarjeta.add(filaIndicador("Energía", valorEnergia, barraEnergia));
        tarjeta.add(Box.createVerticalStrut(30));
        tarjeta.add(filaIndicador("Felicidad", valorFelicidad, barraFelicidad));
        return tarjeta;
    }

    private JPanel filaIndicador(String texto, JLabel valor, JProgressBar barra) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel filaTexto = new JPanel(new BorderLayout());
        filaTexto.setOpaque(false);
        JLabel nombre = EstiloUI.texto(texto, 18);
        nombre.setHorizontalAlignment(SwingConstants.LEFT);
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        filaTexto.add(nombre, BorderLayout.WEST);
        filaTexto.add(valor, BorderLayout.EAST);

        panel.add(filaTexto);
        panel.add(Box.createVerticalStrut(10));
        panel.add(barra);
        return panel;
    }

    private JLabel crearValor() {
        JLabel label = EstiloUI.texto("100%", 18);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(EstiloUI.VERDE_PRESIONADO);
        return label;
    }

    private JProgressBar crearBarraJuego() {
        JProgressBar barra = EstiloUI.barra(EstiloUI.VERDE_BARRA);
        barra.setStringPainted(false);
        barra.setPreferredSize(new Dimension(315, 30));
        barra.setMaximumSize(new Dimension(315, 30));
        return barra;
    }

    private JPanel crearBotones() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 28, 0));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(0, 100));
        panel.add(botonComer);
        panel.add(botonJugar);
        panel.add(botonDormir);
        panel.add(botonVolverMenu);
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
        actualizarBarra(barraHambre, valorHambre, mascota.getHambre());
        actualizarBarra(barraEnergia, valorEnergia, mascota.getEnergia());
        actualizarBarra(barraFelicidad, valorFelicidad, mascota.getFelicidad());
        String estadoResuelto = resolverEstadoVisual(mascota, estadoVisual);
        actualizarImagen(mascota, estadoResuelto);
        etiquetaEstadoMascota.setText(textoEstado(estadoResuelto));
    }

    private void actualizarBarra(JProgressBar barra, JLabel valorTexto, int valor) {
        barra.setValue(valor);
        valorTexto.setText(valor + "%");
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
        etiquetaImagenMascota.setIcon(EstiloUI.cargarImagen(nombreArchivo, 360, 300));
        etiquetaImagenMascota.setText(etiquetaImagenMascota.getIcon() == null ? mascota.getEspecie() : "");
    }

    private String textoEstado(String estadoVisual) {
        if (ESTADO_COMIENDO.equals(estadoVisual)) {
            return "Comiendo";
        }
        if (ESTADO_DURMIENDO.equals(estadoVisual)) {
            return "Durmiendo";
        }
        if (ESTADO_TRISTE.equals(estadoVisual)) {
            return "Triste";
        }
        return "Feliz";
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
