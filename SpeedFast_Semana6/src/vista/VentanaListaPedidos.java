package vista;

import modelo.ControladorDeEnvios;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private ControladorDeEnvios controlador;

    public VentanaListaPedidos(ControladorDeEnvios controlador) {

        this.controlador = controlador;

        setTitle("Lista de Pedidos");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Dirección", "Tipo", "Repartidor", "Estado"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton botonActualizar = new JButton("Actualizar");
        JButton botonAsignar = new JButton("Asignar repartidor / Iniciar entrega");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonActualizar);
        panelBotones.add(botonAsignar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        botonActualizar.addActionListener(e -> cargarPedidos());

        botonAsignar.addActionListener(e -> asignarYDespachar());

        cargarPedidos();
    }

    private void cargarPedidos() {

        modeloTabla.setRowCount(0);

        for (Pedido pedido : controlador.getHistorial()) {

            Object[] fila = {
                    pedido.getNumeroPedido(),
                    pedido.getDireccion(),
                    pedido.getTipoPedido(),
                    pedido.getRepartidor(),
                    pedido.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void asignarYDespachar() {

        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un pedido de la tabla."
            );
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        Pedido pedidoSeleccionado = null;

        for (Pedido pedido : controlador.getHistorial()) {

            if (pedido.getNumeroPedido() == id) {
                pedidoSeleccionado = pedido;
                break;
            }
        }

        if (pedidoSeleccionado == null) {
            return;
        }

        if (pedidoSeleccionado.isCancelado()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El pedido está cancelado."
            );
            return;
        }

        pedidoSeleccionado.asignarRepartidor();
        controlador.despacharPedido(pedidoSeleccionado);

        JOptionPane.showMessageDialog(
                this,
                "Repartidor asignado: "
                        + pedidoSeleccionado.getRepartidor()
                        + "\nEntrega iniciada."
        );

        cargarPedidos();
    }
}
