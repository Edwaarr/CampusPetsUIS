package presentacion;

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

        JLabel imagenMenu = EstiloUI.imagen("menu.png", 360, 190);
        etiquetaUsuario = EstiloUI.texto("Bienvenido", 16);
        botonEmpezar = EstiloUI.boton("Empezar", EstiloUI.VERDE);
        botonAnimales = EstiloUI.boton("Conoce a los animales", EstiloUI.VERDE);

        JPanel filaImagen = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        filaImagen.setOpaque(false);
        filaImagen.add(imagenMenu);

        JPanel filaUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaUsuario.setOpaque(false);
        filaUsuario.add(etiquetaUsuario);

        JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        filaBotones.setOpaque(false);
        filaBotones.add(botonEmpezar);
        filaBotones.add(botonAnimales);

        tarjeta.add(filaImagen);
        tarjeta.add(Box.createVerticalStrut(18));
        tarjeta.add(filaUsuario);
        tarjeta.add(Box.createVerticalStrut(28));
        tarjeta.add(filaBotones);

        add(tarjeta, new GridBagConstraints());
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
