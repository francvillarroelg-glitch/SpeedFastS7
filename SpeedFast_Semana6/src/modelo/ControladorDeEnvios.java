package modelo;

import java.util.ArrayList;

public class ControladorDeEnvios {

    private ArrayList<Pedido> historial;

    public ControladorDeEnvios() {
        historial = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        historial.add(pedido);
    }

    public void despacharPedido(Pedido pedido) {
        pedido.despachar();
    }

    public void cancelarPedido(Pedido pedido) {
        pedido.cancelar();
    }

    public ArrayList<Pedido> getHistorial() {
        return historial;
    }

    public void mostrarHistorial() {

        System.out.println("\n========== HISTORIAL DE ENTREGAS ==========");

        if (historial.isEmpty()) {
            System.out.println("No existen entregas registradas.");
            return;
        }

        for (Pedido pedido : historial) {
            if (pedido.isDespachado() && !pedido.isCancelado()) {
                pedido.verHistorial();
            }
        }

        System.out.println("============================================");
    }
}