public class CabinaPeaje implements Runnable {

    public static int totalRecaudadoGlobal = 0;

    private String nombre;
    private int vehiculosAtendidos;

    public CabinaPeaje(String nombre) {
        this.nombre = nombre;
        this.vehiculosAtendidos = 0;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                int tiempo = 500 + (int) (Math.random() * 1001);
                Thread.sleep(tiempo);

                vehiculosAtendidos++;
                totalRecaudadoGlobal += 50;

                System.out.println(
                    nombre + " atendio al vehiculo " + i +
                    " | Tiempo: " + tiempo + " ms"
                );

            } catch (InterruptedException e) {
                System.out.println("El hilo de " + nombre + " fue interrumpido.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(nombre + " termino de atender sus 10 vehiculos.");
    }

    public static void main(String[] args) {

        CabinaPeaje cabina1 = new CabinaPeaje("Santiago");
        CabinaPeaje cabina2 = new CabinaPeaje("Santiago Cabina 2");
        CabinaPeaje cabina3 = new CabinaPeaje("Cabina 3");

        Thread hilo1 = new Thread(cabina1);
        Thread hilo2 = new Thread(cabina2);
        Thread hilo3 = new Thread(cabina3);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.out.println("El programa principal fue interrumpido.");
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println();
        System.out.println("       REPORTE DEL PEAJE");
        System.out.println();
        System.out.println("Nombre: Santiago");
        System.out.println();

        System.out.println(
            "Santiago: " + cabina1.vehiculosAtendidos +
            " vehiculos atendidos"
        );

        System.out.println(
            "Santiago Cabina 2: " + cabina2.vehiculosAtendidos +
            " vehiculos atendidos"
        );

        System.out.println(
            "Santiago Cabina 3: " + cabina3.vehiculosAtendidos +
            " vehiculos atendidos"
        );

        System.out.println();
        System.out.println("Total de vehiculos: " +
            (cabina1.vehiculosAtendidos +
             cabina2.vehiculosAtendidos +
             cabina3.vehiculosAtendidos));

        System.out.println("Total recaudado: $" +
            totalRecaudadoGlobal);

        System.out.println();
    }
}
