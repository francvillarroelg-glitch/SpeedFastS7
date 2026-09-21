package modelo;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

    private List<Pedido> pedidos;

    public ZonaDeCarga() {
        pedidos = new ArrayList<>();
    }

    public synchronized void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);

        System.out.println(
                "Pedido #" + pedido.getNumeroPedido()
                + " agregado a la zona de carga."
        );
    }

    public synchronized Pedido retirarPedido() {
        if (pedidos.isEmpty()) {
            return null;
        }

        return pedidos.remove(0);
    }
}
