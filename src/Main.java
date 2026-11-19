/**
 * Programa de demostración del sistema de gestión de inventarios.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("===== GESTIÓN DE INVENTARIOS =====\n");

        // 1) Constructor por defecto
        Producto generico = new Producto();
        System.out.println("Producto por defecto:");
        System.out.println(generico + "\n");

        // 2) Constructor con parámetros
        Producto laptop = new Producto("Laptop", 2_500_000, 8, 15);
        System.out.println("Producto con parámetros:");
        System.out.println(laptop + "\n");

        // 3) Vender: caso exitoso
        System.out.println("--- Venta exitosa ---");
        laptop.vender(3);
        System.out.println();

        // 4) Vender: existencias insuficientes
        System.out.println("--- Venta con existencias insuficientes ---");
        laptop.vender(10);
        System.out.println();

        // 5) Vender: cantidad inválida
        System.out.println("--- Venta con cantidad inválida ---");
        laptop.vender(0);
        System.out.println();

        // 6) Reabastecer: cantidad inválida
        System.out.println("--- Reabastecimiento inválido ---");
        laptop.reabastecer(-5);
        System.out.println();

        // 7) Reabastecer: quedan menos de 5 unidades (advertencia)
        System.out.println("--- Reabastecimiento con stock aún bajo ---");
        laptop.vender(2);          // quedan 3
        laptop.reabastecer(1);     // quedan 4 -> advertencia
        System.out.println();

        // 8) Reabastecer: stock suficiente
        System.out.println("--- Reabastecimiento normal ---");
        laptop.reabastecer(20);
        System.out.println();

        System.out.println("Estado final: " + laptop);
    }
}
