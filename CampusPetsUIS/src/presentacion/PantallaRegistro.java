package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PantallaRegistro extends JPanel {

    private final JTextField campoNombre;
    private final JTextField campoCarrera;
    private final JButton botonContinuar;
    private final JLabel mensajeError;

    public PantallaRegistro() {
        setLayout(new GridBagLayout());
        setBackground(EstiloUI.FONDO);

        JPanel tarjeta = EstiloUI.tarjeta();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel titulo = EstiloUI.titulo("CAMPUS PETS UIS", 42);
        JLabel subtitulo = EstiloUI.texto("Bienvenido al juego educativo de cuidado animal", 18);
        campoNombre = EstiloUI.campoTexto();
        campoCarrera = EstiloUI.campoTexto();
        botonContinuar = EstiloUI.boton("Continuar", EstiloUI.VERDE);
        botonContinuar.setPreferredSize(new Dimension(505, 64));
        botonContinuar.setMaximumSize(new Dimension(505, 64));
        mensajeError = EstiloUI.texto(" ", 13);
        mensajeError.setForeground(EstiloUI.ROSA);

        agregarCentrado(tarjeta, titulo);
        tarjeta.add(Box.createVerticalStrut(26));
        agregarCentrado(tarjeta, subtitulo);
        tarjeta.add(Box.createVerticalStrut(42));
        tarjeta.add(etiquetaCampo("Nombre del usuario"));
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(campoNombre);
        tarjeta.add(Box.createVerticalStrut(28));
        tarjeta.add(etiquetaCampo("Programa académico (opcional)"));
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(campoCarrera);
        tarjeta.add(Box.createVerticalStrut(18));
        agregarCentrado(tarjeta, mensajeError);
        tarjeta.add(Box.createVerticalStrut(22));

        JPanel filaBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        filaBoton.setOpaque(false);
        filaBoton.add(botonContinuar);
        tarjeta.add(filaBoton);

        add(tarjeta, new GridBagConstraints());
    }

    private JLabel etiquetaCampo(String texto) {
        JLabel label = EstiloUI.texto(texto, 14);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 17));
        label.setPreferredSize(new Dimension(505, 24));
        label.setMaximumSize(new Dimension(505, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void agregarCentrado(JPanel panel, Component componente) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setOpaque(false);
        fila.add(componente, BorderLayout.CENTER);
        panel.add(fila);
    }

    public boolean datosValidos() {
        if (getNombreUsuario().isEmpty()) {
            mensajeError.setText("El nombre es obligatorio.");
            campoNombre.requestFocusInWindow();
            return false;
        }
        mensajeError.setText(" ");
        return true;
    }

    public String getNombreUsuario() {
        return campoNombre.getText().trim();
    }

    public String getCarrera() {
        String carrera = campoCarrera.getText().trim();
        return carrera.isEmpty() ? "Sin carrera registrada" : carrera;
    }

    public JButton getBotonContinuar() {
        return botonContinuar;
    }
}
