package modelo;

public class PedidoComida extends Pedido {

    private String restaurante;

    public PedidoComida(int numeroPedido, String direccion,
                        double distancia, String restaurante) {
        super(numeroPedido, direccion, distancia);
        this.restaurante = restaurante;
    }

    @Override
    public void asignarRepartidor() {
        if (distancia <= 5) {
            repartidor = "Luis Díaz";
        } else {
            repartidor = "Camila Rojas";
        }

        System.out.println("Repartidor asignado automáticamente: " + repartidor);
    }

    @Override
    public int calcularTiempoEntrega() {
        return 15 + (int) (distancia * 3);
    }

    @Override
    public String getTipoPedido() {
        return "PedidoComida";
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[Pedido Comida]");
        super.mostrarResumen();
        System.out.println("Restaurante: " + restaurante);
    }
}
