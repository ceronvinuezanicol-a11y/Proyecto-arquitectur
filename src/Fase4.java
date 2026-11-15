import java.util.Scanner;

public class Fase4 {
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROJO = "\u001B[31m";
    public static final String PURPURA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void ejecutar(Scanner sc) {
        System.out.println(PURPURA + "\n--- FASE 4: SUMA Y RESTA BINARIA EN COMPLEMENTO A 2 ---" + RESET);
        System.out.print("Ingrese el primer número decimal (A): ");
        int num1 = sc.nextInt();
        System.out.print("Ingrese el segundo número decimal (B): ");
        int num2 = sc.nextInt();
        System.out.print("Ingrese el número de bits (N: 8 o 16): ");
        int nBits = sc.nextInt();

        System.out.println("\nSeleccione la operación:");
        System.out.println(" 1. Suma (A + B)");
        System.out.println(" 2. Resta (A - B)");
        System.out.print(" Opción: ");
        int opArit = sc.nextInt();

        int operando2Real = (opArit == 1) ? num2 : -num2;

        // Validar rangos individuales antes de operar
        int minValor = (int) -Math.pow(2, nBits - 1);
        int maxValor = (int) Math.pow(2, nBits - 1) - 1;

        if (num1 < minValor || num1 > maxValor || operando2Real < minValor || operando2Real > maxValor) {
            System.out.println(ROJO + " ¡Error! Uno de los operandos excede el rango permitido para " + nBits + " bits." + RESET);
            pausaLocal(sc);
            return;
        }

        String binA = Fase3.obtenerComplementoADos(num1, nBits);
        String binB = Fase3.obtenerComplementoADos(operando2Real, nBits);

        System.out.println(VERDE + "\n Representación en " + nBits + " bits:");
        System.out.println(" Operando A (" + num1 + ") = " + binA);
        System.out.println(" Operando B (" + operando2Real + ") = " + binB);

        String resultadoBinario = sumarBinariosCa2(binA, binB, nBits);

        // 1. PRIMERO MOSTRAR EL RESULTADO EN BINARIO Ca2
        System.out.println(AZUL + "\n Resultado en Binario Ca2 = " + resultadoBinario);

        // 2. SMOSTRAR EL PROCEDIMIENTO DEL PASO B (Transformación Ca2 -> Decimal)
        int decimalObtenido = Fase3.verificarComplementoADos(resultadoBinario);

        System.out.println(VERDE + " Resultado Decimal Equivalente = " + decimalObtenido);

        // DETECCIÓN DE OVERFLOW
        char signoA = binA.charAt(0);
        char signoB = binB.charAt(0);
        char signoRes = resultadoBinario.charAt(0);

        if (signoA == signoB && signoA != signoRes) {
            System.out.println(ROJO + "\n  ¡ALERTA DE OVERFLOW (DESBORDAMIENTO ARITMÉTICO)! ");
            System.out.println(" El resultado matemático real no cabe en " + nBits + " bits, por lo que el signo se invirtió por desbordamiento." + RESET);
        } else {
            System.out.println(VERDE + " Operación aritmética completada sin desbordamiento." + RESET);
        }

        pausaLocal(sc);
    }

    public static String sumarBinariosCa2(String binA, String binB, int bits) {
        StringBuilder resultado = new StringBuilder();
        int acarreo = 0;

        for (int i = bits - 1; i >= 0; i--) {
            int bitA = binA.charAt(i) - '0';
            int bitB = binB.charAt(i) - '0';

            int suma = bitA + bitB + acarreo;
            resultado.insert(0, suma % 2);
            acarreo = suma / 2;
        }
        return resultado.toString();
    }

    public static void pausaLocal(Scanner sc) {
        System.out.print(CYAN + "\nPresione Enter para continuar..." + RESET);
        sc.nextLine();
        sc.nextLine();
    }
}