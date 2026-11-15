import java.util.Scanner;

public class Fase2 {
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String ROJO = "\u001B[31m";
    public static final String PURPURA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void ejecutar(Scanner sc) {
        int op;
        do {
            System.out.println(PURPURA + "\n--- FASE 2: CONVERSIÓN DE OTRAS BASES A DECIMAL ---" + RESET);
            System.out.println(" 1. Binario a Decimal");
            System.out.println(" 2. Octal a Decimal");
            System.out.println(" 3. Hexadecimal a Decimal");
            System.out.println(" 4. Volver al Menú Principal");
            System.out.print(" Elija una opción: ");
            op = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            if (op >= 1 && op <= 3) {
                int base = (op == 1) ? 2 : (op == 2) ? 8 : 16;
                String nombreBase = (op == 1) ? "Binario" : (op == 2) ? "Octal" : "Hexadecimal";

                System.out.print("Ingrese el número en " + nombreBase + ": ");
                String entrada = sc.nextLine();

                try {
                    int resultadoDecimal = convertirBaseADecimal(entrada, base);
                    System.out.println(VERDE + " Resultado: El número " + entrada + " (Base " + base + ") en Decimal es: " + resultadoDecimal + RESET);
                } catch (Exception e) {
                    System.out.println(ROJO + " Error: Entrada inválida o no corresponde a la base " + nombreBase + "." + RESET);
                }
                pausaLocal(sc);
            }
        } while (op != 4);
    }

    public static int convertirBaseADecimal(String cadena, int base) {
        String cadenaInvertida = new StringBuilder(cadena.toUpperCase()).reverse().toString();
        int decimal = 0;
        for (int i = 0; i < cadenaInvertida.length(); i++) {
            char digitoChar = cadenaInvertida.charAt(i);
            int valor = (digitoChar >= '0' && digitoChar <= '9') ? digitoChar - '0' :
                    (digitoChar >= 'A' && digitoChar <= 'F') ? digitoChar - 'A' + 10 : -1;
            if (valor == -1 || valor >= base) {
                throw new IllegalArgumentException("Dígito inválido");
            }
            decimal += valor * Math.pow(base, i);
        }
        return decimal;
    }

    public static void pausaLocal(Scanner sc) {
        System.out.print(CYAN + "\nPresione Enter para continuar..." + RESET);
        sc.nextLine();
    }
}