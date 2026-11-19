# Gestión de Inventarios (Java · POO)

Proyecto en Java que aplica Programación Orientada a Objetos para modelar un producto de inventario con la clase `Producto`.

## Estructura

```
gestion-inventarios/
├── src/
│   ├── Producto.java   # Clase con atributos privados, constructores y métodos
│   └── Main.java       # Programa de demostración
├── .gitignore
└── README.md
```

## Clase `Producto`

**Atributos privados**

| Atributo    | Tipo     | Descripción                     |
|-------------|----------|---------------------------------|
| `nombre`    | `String` | Nombre del producto             |
| `precio`    | `double` | Precio por unidad               |
| `cantidad`  | `int`    | Unidades en existencia          |
| `descuento` | `double` | Descuento en porcentaje (0–100) |

**Constructores**

- Por defecto: `"Genérico"`, `10000`, `10`, `0`.
- Con parámetros: `nombre`, `precio`, `cantidad`, `descuento`.

**Métodos**

- `vender(int unidades)`: verifica existencias, calcula el subtotal, aplica el descuento y actualiza el inventario. Devuelve el total a pagar (o `0` si no se pudo vender).
- `reabastecer(int unidades)`: valida que la cantidad sea positiva, aumenta las existencias y advierte si quedan menos de 5 unidades.

Los setters validan los datos (nombre no vacío, precio y cantidad no negativos, descuento entre 0 y 100) y lanzan `IllegalArgumentException` si son inválidos.

## Cómo compilar y ejecutar

Requiere JDK 11 o superior.

```bash
cd src
javac -encoding UTF-8 -d ../out Producto.java Main.java
java -cp ../out Main
```

## Conceptos de POO aplicados

- **Encapsulamiento:** atributos privados con getters y setters.
- **Sobrecarga de constructores:** constructor por defecto y con parámetros.
- **Validación de datos** dentro de la propia clase.
- **Reutilización de código:** el constructor por defecto delega con `this(...)`.
