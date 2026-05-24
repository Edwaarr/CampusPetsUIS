package presentacion;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogoEstadoCritico extends JDialog {

    public DialogoEstadoCritico(JFrame padre, String nombreMascota, Runnable recuperar, Runnable reiniciar, Runnable volverMenu) {
        super(padre, "Estado crítico", Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setSize(620, 250);
        setLocationRelativeTo(padre);

        JPanel contenido = new JPanel(new BorderLayout(12, 18));
        contenido.setBackground(EstiloUI.FONDO);
        contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 28, 24, 28));

        JLabel titulo = EstiloUI.titulo("Estado crítico", 26);
        JLabel mensaje = EstiloUI.texto("<html><div style='width:500px;text-align:center'>"
                + nombreMascota + " necesita atención inmediata. Puedes recuperarlo, reiniciar la partida o volver al menú."
                + "</div></html>", 15);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));
        botones.setOpaque(false);
        JButton botonRecuperar = EstiloUI.boton("Recuperar", EstiloUI.VERDE);
        JButton botonReiniciar = EstiloUI.boton("Reiniciar", EstiloUI.VERDE);
        JButton botonMenu = EstiloUI.boton("Volver al menú", EstiloUI.VERDE);

        botonRecuperar.addActionListener(e -> {
            dispose();
            recuperar.run();
        });
        botonReiniciar.addActionListener(e -> {
            dispose();
            reiniciar.run();
        });
        botonMenu.addActionListener(e -> {
            dispose();
            volverMenu.run();
        });

        botones.add(botonRecuperar);
        botones.add(botonReiniciar);
        botones.add(botonMenu);
        contenido.add(titulo, BorderLayout.NORTH);
        contenido.add(mensaje, BorderLayout.CENTER);
        contenido.add(botones, BorderLayout.SOUTH);
        setContentPane(contenido);
    }
}
