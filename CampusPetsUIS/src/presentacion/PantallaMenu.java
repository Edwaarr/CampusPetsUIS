package presentacion;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PantallaMenu extends JPanel {

    private final JButton botonEmpezar;
    private final JButton botonAnimales;
    private final JLabel etiquetaUsuario;

    public PantallaMenu() {
        setLayout(new GridBagLayout());
        setBackground(EstiloUI.FONDO);

        JPanel tarjeta = EstiloUI.tarjeta();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel titulo = EstiloUI.imagen("menu.png", 360, 285);
        JLabel subtitulo = EstiloUI.texto("Juego educativo de cuidado animal", 20);
        etiquetaUsuario = EstiloUI.texto("Bienvenido", 16);
        botonEmpezar = EstiloUI.boton("Empezar", EstiloUI.VERDE);
        botonAnimales = EstiloUI.boton("Conoce a los animales", EstiloUI.VERDE);
        configurarBotonAncho(botonEmpezar);
        configurarBotonAncho(botonAnimales);

        JPanel filaTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        filaTitulo.setOpaque(false);
        filaTitulo.add(titulo);

        JPanel filaSubtitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        filaSubtitulo.setOpaque(false);
        filaSubtitulo.add(subtitulo);

        JPanel filaUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaUsuario.setOpaque(false);
        filaUsuario.add(etiquetaUsuario);

        tarjeta.add(filaTitulo);
        tarjeta.add(Box.createVerticalStrut(18));
        tarjeta.add(filaSubtitulo);
        tarjeta.add(Box.createVerticalStrut(14));
        tarjeta.add(filaUsuario);
        tarjeta.add(Box.createVerticalStrut(50));
        tarjeta.add(botonEmpezar);
        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(botonAnimales);

        add(tarjeta, new GridBagConstraints());
    }

    private void configurarBotonAncho(JButton boton) {
        boton.setPreferredSize(new Dimension(630, 86));
        boton.setMaximumSize(new Dimension(630, 86));
        boton.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void configurarUsuario(String nombre, String carrera) {
        etiquetaUsuario.setText("Hola, " + nombre + " | " + carrera);
    }

    public JButton getBotonEmpezar() {
        return botonEmpezar;
    }

    public JButton getBotonAnimales() {
        return botonAnimales;
    }
}
