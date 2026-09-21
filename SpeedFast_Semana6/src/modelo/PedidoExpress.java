package modelo;

public class PedidoExpress extends Pedido {

    private boolean prioridad;

    public PedidoExpress(int numeroPedido, String direccion,
                         double distancia, boolean prioridad) {
        super(numeroPedido, direccion, distancia);
        this.prioridad = prioridad;
    }

    @Override
    public void asignarRepartidor() {
        if (prioridad) {
            repartidor = "Sofía Martínez";
        } else {
            repartidor = "Andrés Silva";
        }

        System.out.println("Repartidor asignado automáticamente: " + repartidor);
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = 10 + (int) (distancia * 2);

        if (prioridad) {
            tiempo -= 5;
        }

        return tiempo;
    }

    @Override
    public String getTipoPedido() {
        return "PedidoExpress";
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[Pedido Express]");
        super.mostrarResumen();
        System.out.println("Prioridad: " + (prioridad ? "Sí" : "No"));
    }
}
