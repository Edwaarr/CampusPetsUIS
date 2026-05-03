import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaJuego extends JFrame {

    // Componentes visuales
    private JLabel labelNombreMascota;
    private JLabel labelEspecie;
    private JLabel labelImagenMascota;
    private JProgressBar barraHambre;
    private JProgressBar barraEnergia;
    private JProgressBar barraFelicidad;
    private JLabel labelHambre;
    private JLabel labelEnergia;
    private JLabel labelFelicidad;
    private JButton btnComer;
    private JButton btnJugar;
    private JButton btnDormir;
    private JButton btnSonido;
    private JLabel labelMensaje;
    private JLabel labelUsuario;

    // Referencia al juego
    private Juego juego;

    // Constructor
    public PantallaJuego(Juego juego) {
        this.juego = juego;
        inicializarVentana();
        inicializarComponentes();
        configurarLayout();
        configurarEventos();
    }

    private void inicializarVentana() {
        setTitle("Campus Pets UIS 🐾");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(240, 248, 240));
    }

    private void inicializarComponentes() {
        // Info mascota
        labelNombreMascota = new JLabel("", SwingConstants.CENTER);
        labelNombreMascota.setFont(new Font("Arial", Font.BOLD, 22));
        labelNombreMascota.setForeground(new Color(34, 85, 34));

        labelEspecie = new JLabel("", SwingConstants.CENTER);
        labelEspecie.setFont(new Font("Arial", Font.ITALIC, 14));
        labelEspecie.setForeground(new Color(80, 120, 80));

        labelImagenMascota = new JLabel("🐱", SwingConstants.CENTER);
        labelImagenMascota.setFont(new Font("Arial", Font.PLAIN, 80));

        // Barras de progreso
        barraHambre = crearBarra(new Color(255, 160, 50));
        barraEnergia = crearBarra(new Color(50, 180, 255));
        barraFelicidad = crearBarra(new Color(255, 100, 180));

        labelHambre = new JLabel("🍖 Hambre:");
        labelEnergia = new JLabel("⚡ Energía:");
        labelFelicidad = new JLabel("💖 Felicidad:");

        for (JLabel l : new JLabel[]{labelHambre, labelEnergia, labelFelicidad}) {
            l.setFont(new Font("Arial", Font.BOLD, 13));
            l.setForeground(new Color(34, 85, 34));
        }

        // Botones
        btnComer = crearBoton("🍖 Comer", new Color(255, 160, 50));
        btnJugar = crearBoton("🎾 Jugar", new Color(255, 100, 180));
        btnDormir = crearBoton("💤 Dormir", new Color(50, 180, 255));
        btnSonido = crearBoton("🔊 Sonido", new Color(150, 100, 200));

        // Labels info
        labelMensaje = new JLabel(" ", SwingConstants.CENTER);
        labelMensaje.setFont(new Font("Arial", Font.ITALIC, 12));
        labelMensaje.setForeground(new Color(100, 100, 100));

        labelUsuario = new JLabel("", SwingConstants.CENTER);
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
        labelUsuario.setForeground(new Color(120, 120, 120));
    }

    private JProgressBar crearBarra(Color color) {
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(100);
        barra.setStringPainted(true);
        barra.setForeground(color);
        barra.setBackground(new Color(220, 220, 220));
        barra.setPreferredSize(new Dimension(250, 22));
        barra.setFont(new Font("Arial", Font.BOLD, 11));
        return barra;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(120, 40));
        return btn;
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior - info usuario
        JPanel panelTop = new JPanel(new FlowLayout());
        panelTop.setBackground(new Color(34, 85, 34));
        labelUsuario.setForeground(Color.WHITE);
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 13));
        panelTop.add(labelUsuario);
        add(panelTop, BorderLayout.NORTH);

        // Panel centro - mascota
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(240, 248, 240));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        labelNombreMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEspecie.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelImagenMascota.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCentro.add(labelImagenMascota);
        panelCentro.add(Box.createVerticalStrut(5));
        panelCentro.add(labelNombreMascota);
        panelCentro.add(labelEspecie);
        panelCentro.add(Box.createVerticalStrut(15));

        // Barras
        panelCentro.add(crearFilaBarra(labelHambre, barraHambre));
        panelCentro.add(Box.createVerticalStrut(8));
        panelCentro.add(crearFilaBarra(labelEnergia, barraEnergia));
        panelCentro.add(Box.createVerticalStrut(8));
        panelCentro.add(crearFilaBarra(labelFelicidad, barraFelicidad));
        panelCentro.add(Box.createVerticalStrut(10));
        
        labelMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(labelMensaje);

        add(panelCentro, BorderLayout.CENTER);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        panelBotones.setBackground(new Color(220, 240, 220));
        panelBotones.add(btnComer);
        panelBotones.add(btnJugar);
        panelBotones.add(btnDormir);
        panelBotones.add(btnSonido);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearFilaBarra(JLabel label, JProgressBar barra) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        fila.setBackground(new Color(240, 248, 240));
        label.setPreferredSize(new Dimension(100, 25));
        fila.add(label);
        fila.add(barra);
        return fila;
    }

    private void configurarEventos() {
        btnComer.addActionListener(e -> {
            juego.accionComer();
            actualizarBarras();
            mostrarMensaje("¡" + juego.getUsuario().getMascota().getNombre() + " ha comido!");
        });

        btnJugar.addActionListener(e -> {
            juego.accionJugar();
            actualizarBarras();
            mostrarMensaje("¡" + juego.getUsuario().getMascota().getNombre() + " ha jugado!");
        });

        btnDormir.addActionListener(e -> {
            juego.accionDormir();
            actualizarBarras();
            mostrarMensaje("¡" + juego.getUsuario().getMascota().getNombre() + " está durmiendo...");
        });

        btnSonido.addActionListener(e -> {
            juego.accionSonido();
            mostrarMensaje(juego.getUsuario().getMascota().getNombre() + " hizo un sonido!");
        });
    }

    // Métodos públicos que Juego llama
    public void actualizarBarras() {
        Mascota m = juego.getUsuario().getMascota();
        barraHambre.setValue(m.getHambre());
        barraEnergia.setValue(m.getEnergia());
        barraFelicidad.setValue(m.getFelicidad());
        barraHambre.setString(m.getHambre() + "%");
        barraEnergia.setString(m.getEnergia() + "%");
        barraFelicidad.setString(m.getFelicidad() + "%");
    }

    public void mostrarMensaje(String mensaje) {
        labelMensaje.setText(mensaje);
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public void configurarInfoMascota(String nombre, String especie) {
        labelNombreMascota.setText(nombre);
        labelEspecie.setText("(" + especie + ")");
        labelImagenMascota.setText(especie.equals("Gato") ? "🐱" : "🐶");
    }

    public void configurarInfoUsuario(String nombre, String programa) {
        labelUsuario.setText("👤 " + nombre + " | " + programa);
    }
}