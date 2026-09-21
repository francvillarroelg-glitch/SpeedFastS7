package vista;

import modelo.ControladorDeEnvios;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private ControladorDeEnvios controlador;

    public VentanaPrincipal(ControladorDeEnvios controlador) {

        this.controlador = controlador;

        setTitle("SpeedFast - Sistema de Entregas");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("SISTEMA DE ENTREGAS SPEEDFAST", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton botonRegistrar = new JButton("Registrar pedido");
        JButton botonListar = new JButton("Listar pedidos");
        JButton botonAsignar = new JButton("Asignar repartidor / Iniciar entrega");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonListar);
        panelBotones.add(botonAsignar);

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);

        botonRegistrar.addActionListener(e -> {
            new VentanaRegistroPedido(controlador).setVisible(true);
        });

        botonListar.addActionListener(e -> {
            new VentanaListaPedidos(controlador).setVisible(true);
        });

        botonAsignar.addActionListener(e -> {
            new VentanaListaPedidos(controlador).setVisible(true);
        });
    }
}
