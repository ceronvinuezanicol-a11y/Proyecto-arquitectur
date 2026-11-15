import java.util.Scanner;

public class Fase1 {
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String ROJO = "\u001B[31m";
    public static final String PURPURA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void ejecutar(Scanner sc) {
        System.out.println(PURPURA + "\n--- FASE 1: CONVERSIÓN DE DECIMAL A OTRAS BASES  ---" + RESET);
        System.out.print("Ingrese un número entero decimal positivo: ");
        int num = sc.nextInt();

        if (num < 0) {
            System.out.println(ROJO + "Error: El número debe ser mayor o igual a cero." + RESET);
        } else if (num == 0) {
            System.out.println(VERDE + " [Binario]:     0");
            System.out.println(" [Octal]:       0");
            System.out.println(" [Hexadecimal]: 0" + RESET);
        } else {
            // divisiones sucesivas
            String binario = convertirPorDivisiones(num, 2);
            String octal = convertirPorDivisiones(num, 8);
            String hexadecimal = convertirPorDivisiones(num, 16);

            System.out.println(VERDE + " [Binario]:     " + binario);
            System.out.println(" [Octal]:       " + octal);
            System.out.println(" [Hexadecimal]: " + hexadecimal + RESET);
        }
        pausaLocal(sc);
    }

    // divisiones sucesivas y residuos
    public static String convertirPorDivisiones(int num, int base) {
        StringBuilder resultado = new StringBuilder();
        int temp = num;

        while (temp > 0) {
            int residuo = temp % base;
            if (residuo < 10) {
                resultado.insert(0, residuo); // Inserta el residuo al inicio
            } else {
                // Mapeo para los valores hexadecimales (A=10, B=11, C=12, D=13, E=14, F=15)
                char letraHex = (char) ('A' + (residuo - 10));
                resultado.insert(0, letraHex);
            }
            temp = temp / base; // Cociente para la siguiente división
        }
        return resultado.toString();
    }

    public static void pausaLocal(Scanner sc) {
        System.out.print(CYAN + "\nPresione Enter para continuar..." + RESET);
        sc.nextLine();
        sc.nextLine();
    }
}