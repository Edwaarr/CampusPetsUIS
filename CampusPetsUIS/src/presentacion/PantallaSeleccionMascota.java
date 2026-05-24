package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PantallaSeleccionMascota extends JPanel {

    private final JPanel tarjetaGato;
    private final JPanel tarjetaPerro;
    private final JTextField campoNombreMascota;
    private final JButton botonContinuar;
    private final JButton botonVolverMenu;
    private final JLabel mensajeError;
    private String especieSeleccionada = "Gato";

    public PantallaSeleccionMascota() {
        setLayout(new GridBagLayout());
        setBackground(EstiloUI.FONDO);

        JPanel tarjeta = EstiloUI.tarjeta();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel titulo = EstiloUI.titulo("Elige tu mascota", 30);
        tarjetaGato = crearOpcion("GATO", "Gato");
        tarjetaPerro = crearOpcion("PERRO", "Perro");
        campoNombreMascota = EstiloUI.campoTexto();
        botonContinuar = EstiloUI.boton("Continuar", EstiloUI.VERDE);
        botonVolverMenu = EstiloUI.boton("Volver al menú", EstiloUI.VERDE);
        mensajeError = EstiloUI.texto(" ", 13);
        mensajeError.setForeground(EstiloUI.TEXTO);

        JPanel opciones = new JPanel(new GridLayout(1, 2, 18, 0));
        opciones.setOpaque(false);
        opciones.add(tarjetaGato);
        opciones.add(tarjetaPerro);

        JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        filaBotones.setOpaque(false);
        filaBotones.add(botonVolverMenu);
        filaBotones.add(botonContinuar);

        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(24));
        tarjeta.add(opciones);
        tarjeta.add(Box.createVerticalStrut(18));
        tarjeta.add(EstiloUI.texto("Nombre de la mascota", 14));
        tarjeta.add(campoNombreMascota);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(mensajeError);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(filaBotones);

        add(tarjeta, new GridBagConstraints());
        actualizarSeleccion();
    }

    private JPanel crearOpcion(String textoVisible, String especie) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(210, 150));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Color.WHITE);

        JLabel titulo = EstiloUI.titulo(textoVisible, 28);
        JLabel descripcion = EstiloUI.texto(especie.equals("Gato") ? "Cariñoso y curioso" : "Leal y juguetón", 14);
        panel.add(titulo, BorderLayout.CENTER);
        panel.add(descripcion, BorderLayout.SOUTH);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                especieSeleccionada = especie;
                actualizarSeleccion();
            }
        });
        return panel;
    }

    private void actualizarSeleccion() {
        aplicarBorde(tarjetaGato, especieSeleccionada.equals("Gato"));
        aplicarBorde(tarjetaPerro, especieSeleccionada.equals("Perro"));
    }

    private void aplicarBorde(JPanel panel, boolean seleccionada) {
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(seleccionada ? EstiloUI.VERDE : EstiloUI.BORDE, seleccionada ? 3 : 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
    }

    public boolean datosValidos() {
        if (getNombreMascota().isEmpty()) {
            mensajeError.setText("Escribe el nombre de tu mascota.");
            campoNombreMascota.requestFocusInWindow();
            return false;
        }
        mensajeError.setText(" ");
        return true;
    }

    public String getEspecieSeleccionada() {
        return especieSeleccionada;
    }

    public String getNombreMascota() {
        return campoNombreMascota.getText().trim();
    }

    public JButton getBotonContinuar() {
        return botonContinuar;
    }

    public JButton getBotonVolverMenu() {
        return botonVolverMenu;
    }
}
