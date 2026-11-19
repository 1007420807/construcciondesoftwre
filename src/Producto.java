/**
 * Representa un producto del inventario.
 *
 * Aplica encapsulamiento: los atributos son privados y se accede a ellos
 * mediante getters/setters que validan los datos.
 *
 * El descuento se expresa como PORCENTAJE (0 a 100). Ejemplo: 15 = 15 %.
 */
public class Producto {

    /** Si tras reabastecer quedan menos de estas unidades, se advierte. */
    private static final int UMBRAL_STOCK_BAJO = 5;

    // ----------------------------- Atributos -----------------------------
    private String nombre;
    private double precio;
    private int cantidad;
    private double descuento; // porcentaje 0-100

    // --------------------------- Constructores ---------------------------

    /** Constructor por defecto: "Genérico", 10000, 10, 0. */
    public Producto() {
        this("Genérico", 10000, 10, 0);
    }

    /** Constructor con parámetros. Lanza IllegalArgumentException si algún dato es inválido. */
    public Producto(String nombre, double precio, int cantidad, double descuento) {
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setDescuento(descuento);
    }

    // ------------------------------ Métodos ------------------------------

    /**
     * Vende unidades del producto: verifica existencias, calcula el subtotal,
     * aplica el descuento y actualiza el inventario.
     *
     * @param unidades unidades a vender (debe ser mayor que 0)
     * @return total a pagar con descuento aplicado, o 0 si la venta no se pudo realizar
     */
    public double vender(int unidades) {
        if (unidades <= 0) {
            System.out.println("[ERROR] La cantidad a vender debe ser mayor que 0.");
            return 0;
        }
        if (unidades > cantidad) {
            System.out.printf("[ERROR] Existencias insuficientes de '%s': solicitadas %d, disponibles %d.%n",
                    nombre, unidades, cantidad);
            return 0;
        }

        double subtotal = precio * unidades;
        double valorDescuento = subtotal * (descuento / 100);
        double total = subtotal - valorDescuento;

        cantidad -= unidades; // actualizar inventario

        System.out.printf("[VENTA] %d x %s | Subtotal: $%,.2f | Descuento (%.1f%%): -$%,.2f | Total: $%,.2f%n",
                unidades, nombre, subtotal, descuento, valorDescuento, total);
        System.out.printf("        Existencias restantes: %d%n", cantidad);
        return total;
    }

    /**
     * Reabastece el inventario: valida que la cantidad sea positiva, aumenta
     * las existencias y advierte si quedan menos de 5 unidades.
     *
     * @param unidades unidades a agregar (debe ser mayor que 0)
     * @return true si el reabastecimiento se realizó, false en caso contrario
     */
    public boolean reabastecer(int unidades) {
        if (unidades <= 0) {
            System.out.println("[ERROR] La cantidad a reabastecer debe ser positiva.");
            return false;
        }

        cantidad += unidades;
        System.out.printf("[REABASTECIMIENTO] +%d unidad(es) de %s. Existencias actuales: %d%n",
                unidades, nombre, cantidad);

        if (cantidad < UMBRAL_STOCK_BAJO) {
            System.out.printf("[ADVERTENCIA] Quedan menos de %d unidades de '%s' (%d). Conviene reabastecer más.%n",
                    UMBRAL_STOCK_BAJO, nombre, cantidad);
        }
        return true;
    }

    // --------------------------- Getters/Setters --------------------------

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public double getPrecio() {
        return precio;
    }

    public final void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public final void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        this.cantidad = cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public final void setDescuento(double descuento) {
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100 (%).");
        }
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return String.format("Producto{nombre='%s', precio=$%,.2f, cantidad=%d, descuento=%.1f%%}",
                nombre, precio, cantidad, descuento);
    }
}
