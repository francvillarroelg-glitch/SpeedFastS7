Francisca Villarroel
Analista Programador Computacional
DuocUC Online

# SpeedFast — Sistema de Gestión de Entregas

## Descripción

SpeedFast es un sistema desarrollado en Java para gestionar pedidos y entregas de una empresa de reparto.

El proyecto permite administrar pedidos de distintos tipos, asignar repartidores, procesar entregas mediante concurrencia y almacenar la información utilizando una base de datos MySQL.

El sistema también cuenta con una interfaz gráfica desarrollada con Java Swing.

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- MySQL
- JDBC
- MySQL Connector/J
- Java Swing
- Programación Orientada a Objetos
- Concurrencia con `Thread` y `Runnable`

## Estructura del proyecto

```text
src
├── conexion
│   ├── ConexionBD.java
│   └── PruebaConexion.java
│
├── dao
│   ├── PedidoDAO.java
│   ├── RepartidorDAO.java
│   └── EntregaDAO.java
│
├── modelo
│   ├── ControladorDeEnvios.java
│   ├── Pedido.java
│   ├── PedidoComida.java
│   ├── PedidoEncomienda.java
│   ├── PedidoExpress.java
│   ├── ZonaDeCarga.java
│   └── Repartidor.java
│
├── vista
│   └── VentanaPrincipal.java
│
└── main
    └── Main.java
```

## Funcionalidades

### Gestión de pedidos

El sistema permite crear distintos tipos de pedidos:

- Pedido de comida
- Pedido de encomienda
- Pedido express

Cada pedido contiene información como:

- Número de pedido
- Dirección
- Distancia
- Repartidor
- Estado

### Estados de los pedidos

Los pedidos pueden cambiar de estado durante el proceso de entrega:

```text
PENDIENTE
     ↓
EN_REPARTO
     ↓
ENTREGADO
```

También se contempla la posibilidad de cancelar un pedido cuando corresponde.

### Gestión de repartidores

El sistema permite trabajar con distintos repartidores y asignarlos a los pedidos.

Los repartidores utilizados en el proyecto son procesados mediante `Runnable` y ejecutados utilizando `Thread`.

### Concurrencia

La clase `ZonaDeCarga` administra los pedidos disponibles para ser retirados por los repartidores.

Se utilizan hilos para permitir que varios repartidores procesen pedidos de manera concurrente.

Ejemplo:

```java
Thread hilo1 = new Thread(repartidor1);
Thread hilo2 = new Thread(repartidor2);

hilo1.start();
hilo2.start();
```

La zona de carga utiliza métodos sincronizados para controlar el acceso a los pedidos.

## Base de datos

El proyecto utiliza MySQL mediante JDBC.

La base de datos utilizada es:

```text
speedfast_db
```

El sistema trabaja con las siguientes tablas:

### pedido

Almacena la información de los pedidos.

```text
id
direccion
tipo
estado
```

### repartidor

Almacena los repartidores registrados.

```text
id
nombre
```

### entrega

Registra las entregas realizadas.

```text
id
id_pedido
id_repartidor
fecha
hora
```

## Patrón DAO

El proyecto utiliza clases DAO para separar el acceso a la base de datos de la lógica principal del sistema.

### PedidoDAO

Permite:

- Insertar pedidos.
- Consultar pedidos.
- Buscar el ID de un pedido.

### RepartidorDAO

Permite:

- Insertar repartidores.
- Consultar repartidores.
- Buscar el ID de un repartidor.

### EntregaDAO

Permite:

- Registrar entregas.
- Consultar las entregas almacenadas.

## Conexión a MySQL

La conexión se encuentra centralizada en:

```text
conexion/ConexionBD.java
```

Esta clase utiliza JDBC para conectarse a MySQL.

La conexión utiliza:

```text
Base de datos: speedfast_db
Usuario: root
Servidor: localhost
Puerto: 3306
```

## Ejecución del proyecto

Para ejecutar el proyecto:

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que MySQL esté iniciado.
3. Verificar que exista la base de datos `speedfast_db`.
4. Verificar que el conector JDBC esté configurado.
5. Ejecutar:

```text
main/Main.java
```

Al iniciar el programa se crean los pedidos de ejemplo, se agregan a la zona de carga y se ejecutan los repartidores mediante hilos.

Las entregas realizadas son registradas automáticamente en MySQL.

## Flujo general del sistema

```text
Crear pedidos
      ↓
Agregar pedidos al controlador
      ↓
Agregar pedidos a la zona de carga
      ↓
Crear repartidores
      ↓
Ejecutar hilos
      ↓
Repartidor retira pedido
      ↓
Pedido pasa a EN_REPARTO
      ↓
Se procesa la entrega
      ↓
Pedido pasa a ENTREGADO
      ↓
Se registra la entrega en MySQL
```

## Pruebas realizadas

Se verificó el funcionamiento de:

- Conexión con MySQL.
- Inserción de pedidos.
- Consulta de pedidos.
- Consulta de repartidores.
- Registro de entregas.
- Consulta de entregas.
- Concurrencia mediante hilos.
- Cambio de estados de los pedidos.
- Funcionamiento de la interfaz gráfica.
- Ejecución completa desde `Main`.

## Autores

**Proyecto académico — SpeedFast**

Desarrollado como parte de la asignatura de programación.
