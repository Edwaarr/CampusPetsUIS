package presentacion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public final class EstiloUI {

    public static final Color FONDO = new Color(0xE8F5E9);
    public static final Color PANEL = Color.WHITE;
    public static final Color TEXTO = new Color(0x333333);
    public static final Color VERDE = new Color(0x43A047);
    public static final Color VERDE_HOVER = new Color(0x388E3C);
    public static final Color VERDE_PRESIONADO = new Color(0x2E7D32);
    public static final Color VERDE_OSCURO = VERDE_PRESIONADO;
    public static final Color NARANJA = VERDE;
    public static final Color AZUL = VERDE;
    public static final Color ROSA = VERDE;
    public static final Color BORDE = new Color(0xC8E6C9);

    private EstiloUI() {
    }

    public static JLabel titulo(String texto, int tamano) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, tamano));
        label.setForeground(TEXTO);
        return label;
    }

    public static JLabel texto(String texto, int tamano) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, tamano));
        label.setForeground(TEXTO);
        return label;
    }

    public static JButton boton(String texto, Color color) {
        return new BotonRedondeado(texto);
    }

    public static JTextField campoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campo.setForeground(TEXTO);
        campo.setHorizontalAlignment(SwingConstants.CENTER);
        campo.setPreferredSize(new Dimension(320, 42));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        return campo;
    }

    public static JProgressBar barra(Color color) {
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(100);
        barra.setStringPainted(true);
        barra.setForeground(VERDE);
        barra.setBackground(new Color(0xE0E0E0));
        barra.setPreferredSize(new Dimension(330, 30));
        barra.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return barra;
    }

    public static JPanel tarjeta() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE, 1),
                BorderFactory.createEmptyBorder(24, 28, 24, 28)));
        return panel;
    }

    public static JLabel imagen(String nombreArchivo, int ancho, int alto) {
        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(ancho, alto));
        label.setIcon(cargarImagen(nombreArchivo, ancho, alto));
        return label;
    }

    public static ImageIcon cargarImagen(String nombreArchivo, int ancho, int alto) {
        URL recurso = EstiloUI.class.getResource("/imagenes/" + nombreArchivo);
        if (recurso == null) {
            recurso = EstiloUI.class.getResource("/imagenes/" + nombreArchivo + ".jpeg");
        }

        ImageIcon icono = recurso != null ? new ImageIcon(recurso) : cargarDesdeArchivo(nombreArchivo);
        if (icono == null || icono.getIconWidth() <= 0) {
            return null;
        }

        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }

    private static ImageIcon cargarDesdeArchivo(String nombreArchivo) {
        String[] rutas = {
            "src/imagenes/" + nombreArchivo,
            "src/imagenes/" + nombreArchivo + ".jpeg"
        };

        for (String ruta : rutas) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                return new ImageIcon(archivo.getPath());
            }
        }
        return null;
    }
}
