package main;

import modelo.ControladorDeEnvios;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;
import modelo.ZonaDeCarga;
import modelo.Repartidor;
import vista.VentanaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ControladorDeEnvios controlador = new ControladorDeEnvios();

            // Pedidos de ejemplo del proyecto anterior
            PedidoComida pedido1 = new PedidoComida(
                    101,
                    "Av. Providencia 1234",
                    4,
                    "Restaurante Sabores"
            );

            PedidoEncomienda pedido2 = new PedidoEncomienda(
                    102,
                    "Av. Santa Rosa 567",
                    7,
                    3.5
            );

            PedidoExpress pedido3 = new PedidoExpress(
                    103,
                    "Av. Las Condes 890",
                    5,
                    true
            );

            PedidoComida pedido4 = new PedidoComida(
                    104,
                    "Av. Grecia 1450",
                    6,
                    "Restaurant Central"
            );

            PedidoEncomienda pedido5 = new PedidoEncomienda(
                    105,
                    "Av. Macul 2200",
                    8,
                    2.0
            );

            controlador.agregarPedido(pedido1);
            controlador.agregarPedido(pedido2);
            controlador.agregarPedido(pedido3);
            controlador.agregarPedido(pedido4);
            controlador.agregarPedido(pedido5);

            new VentanaPrincipal(controlador).setVisible(true);
        });
    }
}
