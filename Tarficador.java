import java.util.Scanner;
public class Tarficador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- ENTRADA DE DATOS ---
        System.out.print("Tipo de vehículo (1=Motocarro, 2=Automóvil, 3=Camioneta): ");
        int V = sc.nextInt();

        System.out.print("Distancia del viaje en km: ");
        double km = sc.nextDouble();

        System.out.print("Hora de inicio del viaje (0-23): ");
        int hora = sc.nextInt();

        System.out.print("¿Es domingo o festivo? (S/N): ");
        char D = sc.next().charAt(0);

        System.out.print("¿Hay lluvia fuerte? (S/N): ");
        char L = sc.next().charAt(0);

        System.out.print("¿Es viaje rural? (S/N): ");
        char R = sc.next().charAt(0);

        System.out.print("Tipo de pasajero (1=Frecuente, 2=Estudiante, 3=Adulto mayor, 4=Ocasional): ");
        int P = sc.nextInt();

        System.out.print("Edad del pasajero: ");
        int edad = sc.nextInt();

        // --- VALIDACIONES OBLIGATORIAS ---
        if (V < 1 || V > 3) {
            System.out.println("Tipo de vehículo no válido");
            return;
        }
        if (km <= 0) {
            System.out.println("Distancia inválida");
            return;
        }
        if (hora < 0 || hora > 23) {
            System.out.println("Hora inválida");
            return;
        }
        if (!(D == 'S' || D == 'N') || !(L == 'S' || L == 'N') || !(R == 'S' || R == 'N')) {
            System.out.println("Respuesta S/N inválida");
            return;
        }
        if (P < 1 || P > 4) {
            System.out.println("Tipo de pasajero no válido");
            return;
        }
        if (edad <= 0 || edad >= 120) {
            System.out.println("Edad fuera de rango");
            return;
        }

        // --- REGLA 1: TARIFA BASE ---
        int tarifaKm = 0, tarifaMinima = 0;
        if (V == 1) { // Motocarro
            tarifaKm = 1200; tarifaMinima = 5000;
        } else if (V == 2) { // Automóvil
            tarifaKm = 2000; tarifaMinima = 8000;
        } else { // Camioneta 4x4
            tarifaKm = 2800; tarifaMinima = 12000;
        }

        double subtotal = km * tarifaKm;
        boolean aplicoMinima = false; // variable booleana intermedia
        if (subtotal < tarifaMinima) {
            subtotal = tarifaMinima;
            aplicoMinima = true;
        }

        // --- REGLA 2: RECARGOS ---
        double porcentajeRecargo = 0;
        if (hora >= 22 || hora < 5) porcentajeRecargo += 0.20; // recargo nocturno
        if (D == 'S') porcentajeRecargo += 0.15;               // recargo domingo/festivo
        if (L == 'S') porcentajeRecargo += 0.10;               // recargo lluvia
        if (R == 'S') porcentajeRecargo += 0.25;               // recargo rural

        double valorRecargo = subtotal * porcentajeRecargo;
        double totalConRecargos = subtotal + valorRecargo;

        // --- REGLA 3: DESCUENTOS ---
        double porcentajeDescuento = 0;
        if (P == 1) { // Pasajero frecuente
            porcentajeDescuento = 0.10;
        } else if (P == 2) { // Estudiante
            porcentajeDescuento = 0.08;
        } else if (P == 3) { // Adulto mayor
            if (edad >= 60) {
                porcentajeDescuento = 0.12;
            } else {
                System.out.println("Inconsistencia: edad no corresponde a adulto mayor");
                P = 4; // reasignar a ocasional
            }
        } else { // Ocasional
            porcentajeDescuento = 0;
        }

        double valorDescuento = totalConRecargos * porcentajeDescuento;
        double totalFinal = totalConRecargos - valorDescuento;

        // --- REGLA 4: TARIFA SOLIDARIA ---
        if (R == 'N' && totalFinal < tarifaMinima) {
            totalFinal = tarifaMinima;
            System.out.println("Se aplicó tarifa solidaria mínima");
        }

        // --- RECIBO FINAL ---
        System.out.println("\n--- RECIBO DE VIAJE ---");
        System.out.println("Vehículo tipo " + V + ", km recorridos: " + km);
        System.out.println("Subtotal base: $" + (int)subtotal);
        if (aplicoMinima) System.out.println("Se aplicó tarifa mínima en Regla 1");
        System.out.println("Recargo total: " + (int)(porcentajeRecargo*100) + "% → $" + (int)valorRecargo);
        System.out.println("Valor con recargos: $" + (int)totalConRecargos);
        System.out.println("Pasajero tipo " + P + ", descuento: " + (int)(porcentajeDescuento*100) + "% → $" + (int)valorDescuento);
        System.out.println("TOTAL A PAGAR: $" + (int)totalFinal);
    }
}
