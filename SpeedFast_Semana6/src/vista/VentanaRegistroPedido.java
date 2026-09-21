package vista;

import modelo.ControladorDeEnvios;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private JTextField campoId;
    private JTextField campoDireccion;
    private JTextField campoDistancia;
    private JComboBox<String> comboTipo;

    private ControladorDeEnvios controlador;

    public VentanaRegistroPedido(ControladorDeEnvios controlador) {

        this.controlador = controlador;

        setTitle("Registrar Pedido");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel etiquetaId = new JLabel("ID:");
        JLabel etiquetaDireccion = new JLabel("Dirección:");
        JLabel etiquetaDistancia = new JLabel("Distancia (km):");
        JLabel etiquetaTipo = new JLabel("Tipo:");

        campoId = new JTextField();
        campoDireccion = new JTextField();
        campoDistancia = new JTextField();

        comboTipo = new JComboBox<>();
        comboTipo.addItem("Comida");
        comboTipo.addItem("Encomienda");
        comboTipo.addItem("Express");

        JButton botonGuardar = new JButton("Guardar");

        panel.add(etiquetaId);
        panel.add(campoId);

        panel.add(etiquetaDireccion);
        panel.add(campoDireccion);

        panel.add(etiquetaDistancia);
        panel.add(campoDistancia);

        panel.add(etiquetaTipo);
        panel.add(comboTipo);

        panel.add(new JLabel());
        panel.add(botonGuardar);

        add(panel);

        botonGuardar.addActionListener(e -> guardarPedido());
    }

    private void guardarPedido() {

        String idTexto = campoId.getText().trim();
        String direccion = campoDireccion.getText().trim();
        String distanciaTexto = campoDistancia.getText().trim();
        String tipo = (String) comboTipo.getSelectedItem();

        if (idTexto.isEmpty() || direccion.isEmpty() || distanciaTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos."
            );

            return;
        }

        try {

            int id = Integer.parseInt(idTexto);
            double distancia = Double.parseDouble(distanciaTexto);

            if (distancia <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "La distancia debe ser mayor que 0."
                );
                return;
            }

            Pedido pedido;

            if (tipo.equals("Comida")) {

                String restaurante = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el nombre del restaurante:"
                );

                if (restaurante == null || restaurante.trim().isEmpty()) {
                    return;
                }

                pedido = new PedidoComida(
                        id,
                        direccion,
                        distancia,
                        restaurante
                );

            } else if (tipo.equals("Encomienda")) {

                String pesoTexto = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el peso de la encomienda en kg:"
                );

                if (pesoTexto == null || pesoTexto.trim().isEmpty()) {
                    return;
                }

                double peso = Double.parseDouble(pesoTexto);

                if (peso <= 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "El peso debe ser mayor que 0."
                    );
                    return;
                }

                pedido = new PedidoEncomienda(
                        id,
                        direccion,
                        distancia,
                        peso
                );

            } else {

                int respuesta = JOptionPane.showConfirmDialog(
                        this,
                        "¿El pedido tiene prioridad express?",
                        "Prioridad",
                        JOptionPane.YES_NO_OPTION
                );

                boolean prioridad = respuesta == JOptionPane.YES_OPTION;

                pedido = new PedidoExpress(
                        id,
                        direccion,
                        distancia,
                        prioridad
                );
            }

            controlador.agregarPedido(pedido);

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido registrado correctamente."
            );

            campoId.setText("");
            campoDireccion.setText("");
            campoDistancia.setText("");

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID, distancia y peso deben ser valores numéricos."
            );
        }
    }
}
