package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PantallaAnimales extends JPanel {

    private final JButton botonVolver;

    public PantallaAnimales() {
        setLayout(new GridBagLayout());
        setBackground(EstiloUI.FONDO);

        JPanel tarjeta = EstiloUI.tarjeta();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel imagen = EstiloUI.imagen("conoce_animales.png", 520, 210);
        JTextArea texto = crearTextoInformativo();
        botonVolver = EstiloUI.boton("Volver al menú", EstiloUI.VERDE);

        JPanel filaImagen = new JPanel(new BorderLayout());
        filaImagen.setOpaque(false);
        filaImagen.add(imagen, BorderLayout.CENTER);

        JPanel filaBoton = new JPanel(new BorderLayout());
        filaBoton.setOpaque(false);
        filaBoton.add(botonVolver, BorderLayout.CENTER);

        tarjeta.add(filaImagen);
        tarjeta.add(Box.createVerticalStrut(22));
        tarjeta.add(texto);
        tarjeta.add(Box.createVerticalStrut(24));
        tarjeta.add(filaBoton);

        add(tarjeta, new GridBagConstraints());
    }

    private JTextArea crearTextoInformativo() {
        JTextArea area = new JTextArea(
                "En el campus de la Universidad Industrial de Santander viven muchos animales que necesitan cuidado y atención. "
                + "Muchos de ellos dependen de la bondad de estudiantes y personal para sobrevivir.\n\n"
                + "• Los animales sin hogar enfrentan hambre, frío y soledad cada día  \n"
                + "• Un pequeño gesto de cuidado puede marcar una gran diferencia  \n"
                + "• La concientización es el primer paso para generar cambio");
        area.setEditable(false);
        area.setFocusable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(false);
        area.setForeground(EstiloUI.TEXTO);
        area.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        area.setPreferredSize(new Dimension(560, 180));
        return area;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }
}
