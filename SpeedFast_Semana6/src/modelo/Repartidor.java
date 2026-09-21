package modelo;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {

        while (true) {

            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            pedido.setEstado(EstadoPedido.EN_REPARTO);

            System.out.println(
                    nombre + " retiró el pedido #" + pedido.getNumeroPedido()
            );

            System.out.println(
                    nombre + " está entregando el pedido #" + pedido.getNumeroPedido()
            );

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            pedido.setEstado(EstadoPedido.ENTREGADO);

            System.out.println(
                    nombre + " entregó el pedido #" + pedido.getNumeroPedido()
            );
        }
    }
}
