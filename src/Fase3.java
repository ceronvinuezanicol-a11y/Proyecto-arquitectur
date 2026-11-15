import java.util.Scanner;

public class Fase3 {
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String ROJO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";
    public static final String AZUL_CLARO = "\u001B[96m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String PURPURA = "\u001B[35m";
    public static final String BLANCO = "\u001B[37m";

    public static void ejecutar(Scanner sc) {
        System.out.println(PURPURA + "\n--- FASE 3: COMPLEMENTO A DOS Y VERIFICACIÓN ---" + RESET);
        System.out.print("Ingrese un número entero decimal (positivo o negativo): ");
        int numero = sc.nextInt();
        System.out.print("Ingrese el número de bits (N: 8, 16 o 32): ");
        int nBits = sc.nextInt();

        int minValor = (int) -Math.pow(2, nBits - 1);
        int maxValor = (int) Math.pow(2, nBits - 1) - 1;

        if (numero < minValor || numero > maxValor) {
            System.out.println(ROJO + " ¡DESBORDAMIENTO (OVERFLOW)! El número " + numero + " no cabe en " + nBits + " bits." + RESET);
            System.out.println(AMARILLO + "Rango permitido para " + nBits + " bits: [" + minValor + " a " + maxValor + "]" + RESET);
            pausaLocal(sc);
            return;
        }

        String ca2Binario = obtenerComplementoADos(numero, nBits);
        System.out.println(VERDE + "\n [Paso A] Representación en Complemento a 2 (" + nBits + " bits):");
        System.out.println(" Binario Ca2 = " + ca2Binario + RESET);

        int decimalRevertido = verificarComplementoADos(ca2Binario);
        System.out.println(VERDE + "\n ¡Verificación exitosa! Decimal original recuperado = " + decimalRevertido + RESET);

        pausaLocal(sc);
    }

    public static String obtenerComplementoADos(int num, int bits) {
        if (num >= 0) {
            String bin = Integer.toBinaryString(num);
            return String.format("%" + bits + "s", bin).replace(' ', '0');
        } else {
            int valorAbs = Math.abs(num);
            String binAbs = Integer.toBinaryString(valorAbs);
            String binPadded = String.format("%" + bits + "s", binAbs).replace(' ', '0');

            StringBuilder c1 = new StringBuilder();
            for (char c : binPadded.toCharArray()) {
                c1.append(c == '0' ? '1' : '0');
            }

            char[] c2 = c1.toString().toCharArray();
            int acarreo = 1;
            for (int i = c2.length - 1; i >= 0; i--) {
                if (c2[i] == '1' && acarreo == 1) {
                    c2[i] = '0';
                } else if (c2[i] == '0' && acarreo == 1) {
                    c2[i] = '1';
                    acarreo = 0;
                    break;
                }
            }
            return new String(c2);
        }
    }

    public static int verificarComplementoADos(String binarioCa2) {
        char bitSigno = binarioCa2.charAt(0);


        System.out.println(AZUL_CLARO + "\n=============================================================");
        System.out.println(" [Paso B] Verificación y Reversión del Proceso (Ca2 -> Decimal):");
        System.out.println("=============================================================" + RESET);

        System.out.println(AZUL_CLARO + " • Paso 1:" + BLANCO + " Analizar el bit de signo (primer bit de la izquierda): '" + bitSigno + "'");

        if (bitSigno == '0') {
            System.out.println(BLANCO + "   -> Como el bit de signo es 0, el número es POSITIVO.");
            System.out.println(AZUL_CLARO + " • Paso 2:" + BLANCO + " Conversión directa de binario a decimal.");
            int decimal = Integer.parseInt(binarioCa2, 2);
            System.out.println(AZUL_CLARO + "=============================================================" + RESET);
            return decimal;
        } else {
            System.out.println(BLANCO + "   -> Como el bit de signo es 1, el número es NEGATIVO.");

            StringBuilder c1 = new StringBuilder();
            for (char c : binarioCa2.toCharArray()) {
                c1.append(c == '0' ? '1' : '0');
            }
            System.out.println(AZUL_CLARO + " • Paso 2:" + BLANCO + " Aplicar complemento a 1 (invertir bits del Ca2):");
            System.out.println(BLANCO + "   Ca2 actual: " + binarioCa2 + "  -->  Invertidos: " + c1.toString());

            int valorPositivo = Integer.parseInt(c1.toString(), 2) + 1;
            String binMag = Integer.toBinaryString(valorPositivo);
            String magnitudPadded = String.format("%" + binarioCa2.length() + "s", binMag).replace(' ', '0');

            System.out.println(AZUL_CLARO + " • Paso 3:" + BLANCO + " Sumar 1 al resultado anterior para obtener la magnitud:");
            System.out.println(BLANCO + "   " + c1.toString() + " + 1 = " + magnitudPadded + " (Magnitud absoluta)");

            System.out.println(AZUL_CLARO + " • Paso 4:" + BLANCO + " Convertir la magnitud binaria a decimal:");
            System.out.println(BLANCO + "   " + magnitudPadded + " (Base 2) = " + valorPositivo + " (Base 10)");

            int resultadoFinal = -valorPositivo;
            System.out.println(AZUL_CLARO + " • Paso 5:" + BLANCO + " Aplicar el signo negativo correspondiente: " + resultadoFinal);
            System.out.println(AZUL_CLARO + "=============================================================" + RESET);
            return resultadoFinal;
        }
    }

    public static void pausaLocal(Scanner sc) {
        System.out.print(AZUL_CLARO + "\nPresione Enter para continuar..." + RESET);
        sc.nextLine();
        sc.nextLine();
    }
}