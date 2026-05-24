package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
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

        JLabel titulo = EstiloUI.titulo("Campus Pets UIS", 32);
        JLabel subtitulo = EstiloUI.texto("Registra tu perfil para comenzar", 16);
        campoNombre = EstiloUI.campoTexto();
        campoCarrera = EstiloUI.campoTexto();
        botonContinuar = EstiloUI.boton("Continuar", EstiloUI.VERDE);
        mensajeError = EstiloUI.texto(" ", 13);
        mensajeError.setForeground(EstiloUI.ROSA);

        agregarCentrado(tarjeta, titulo);
        tarjeta.add(Box.createVerticalStrut(8));
        agregarCentrado(tarjeta, subtitulo);
        tarjeta.add(Box.createVerticalStrut(28));
        tarjeta.add(etiquetaCampo("Nombre *"));
        tarjeta.add(campoNombre);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(etiquetaCampo("Carrera"));
        tarjeta.add(campoCarrera);
        tarjeta.add(Box.createVerticalStrut(10));
        agregarCentrado(tarjeta, mensajeError);
        tarjeta.add(Box.createVerticalStrut(10));

        JPanel filaBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        filaBoton.setOpaque(false);
        filaBoton.add(botonContinuar);
        tarjeta.add(filaBoton);

        add(tarjeta, new GridBagConstraints());
    }

    private JLabel etiquetaCampo(String texto) {
        JLabel label = EstiloUI.texto(texto, 14);
        label.setHorizontalAlignment(JLabel.LEFT);
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
