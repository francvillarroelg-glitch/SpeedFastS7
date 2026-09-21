package modelo;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    protected int numeroPedido;
    protected String direccion;
    protected double distancia;
    protected String repartidor;
    protected boolean despachado;
    protected boolean cancelado;
    protected EstadoPedido estado;

    public Pedido(int numeroPedido, String direccion, double distancia) {
        this.numeroPedido = numeroPedido;
        this.direccion = direccion;
        this.distancia = distancia;
        this.repartidor = "Sin asignar";
        this.despachado = false;
        this.cancelado = false;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public abstract int calcularTiempoEntrega();

    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
        System.out.println("Repartidor asignado manualmente: " + nombre);
    }

    public void mostrarResumen() {
        System.out.println("Pedido #" + numeroPedido);
        System.out.println("Dirección: " + direccion);
        System.out.println("Distancia: " + distancia + " km");
        System.out.println("Repartidor asignado: " + repartidor);
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " minutos");
    }

    @Override
    public void despachar() {
        if (cancelado) {
            System.out.println("No se puede despachar el pedido porque está cancelado.");
            return;
        }

        if (repartidor.equals("Sin asignar")) {
            System.out.println("No se puede despachar: falta asignar repartidor.");
            return;
        }

        despachado = true;
        estado = EstadoPedido.EN_REPARTO;
        System.out.println("Pedido despachado correctamente.");
    }

    @Override
    public void cancelar() {
        if (despachado) {
            System.out.println("No se puede cancelar un pedido que ya fue despachado.");
            return;
        }

        if (cancelado) {
            System.out.println("El pedido ya estaba cancelado.");
            return;
        }

        cancelado = true;
        System.out.println("Pedido cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {
        System.out.println(
                "Pedido #" + numeroPedido +
                " - " + getTipoPedido() +
                " - repartidor: " + repartidor
        );
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getDistancia() {
        return distancia;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public boolean isDespachado() {
        return despachado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido #" + numeroPedido +
                " - Dirección: " + direccion +
                " - Estado: " + estado;
    }

    public abstract String getTipoPedido();
}
