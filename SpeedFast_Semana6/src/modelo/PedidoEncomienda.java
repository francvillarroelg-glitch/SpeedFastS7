package modelo;

public class PedidoEncomienda extends Pedido {

    private double peso;

    public PedidoEncomienda(int numeroPedido, String direccion,
                            double distancia, double peso) {
        super(numeroPedido, direccion, distancia);
        this.peso = peso;
    }

    @Override
    public void asignarRepartidor() {
        if (peso <= 5) {
            repartidor = "Daniela Tapia";
        } else {
            repartidor = "Pedro González";
        }

        System.out.println("Repartidor asignado automáticamente: " + repartidor);
    }

    @Override
    public int calcularTiempoEntrega() {
        return 10 + (int) (distancia * 3);
    }

    @Override
    public String getTipoPedido() {
        return "PedidoEncomienda";
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[Pedido Encomienda]");
        super.mostrarResumen();
        System.out.println("Peso: " + peso + " kg");
    }
}
