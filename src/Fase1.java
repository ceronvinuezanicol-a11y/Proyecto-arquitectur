
import java.util.Scanner;

public class Fase1 {

    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String ROJO = "\u001B[31m";
    public static final String PURPURA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void ejecutar(Scanner sc) {

        int opcion = 0;

        do {

            System.out.println(CYAN + "\n========== FASE 1 ==========" + RESET);
            System.out.println(PURPURA + "1. Decimal a Binario" + RESET);
            System.out.println(PURPURA + "2. Decimal a Octal" + RESET);
            System.out.println(PURPURA + "3. Decimal a Hexadecimal" + RESET);
            System.out.println(PURPURA + "4. Convertir a todas las bases" + RESET);
            System.out.println(PURPURA + "5. Regresar al menu principal" + RESET);
            System.out.print(CYAN + "Seleccione una opcion: " + RESET);

            opcion = sc.nextInt();

            switch (opcion) {

                case 1: {
                    int numero;

                    System.out.print("Ingrese un numero positivo: ");
                    numero = sc.nextInt();

                    if (numero < 0) {
                        System.out.println(ROJO + "Error. Ingrese un numero positivo." + RESET);
                    } else {
                        int binario = DecimalaBinario(numero);

                        System.out.println(VERDE + "El numero decimal " + numero +
                                " en binario es " + binario + RESET);
                    }
                    break;
                }

                case 2: {
                    int numero;

                    System.out.print("Ingrese un numero entero decimal positivo: ");
                    numero = sc.nextInt();

                    if (numero < 0) {
                        System.out.println(ROJO + "Error. Ingrese un numero positivo." + RESET);
                    } else {
                        int octal = DecimalaOctal(numero);

                        System.out.println(VERDE + "El numero decimal " + numero +
                                " en octal es " + octal + RESET);
                    }
                    break;
                }

                case 3: {
                    int numero;

                    System.out.print("Ingrese un numero entero decimal positivo: ");
                    numero = sc.nextInt();

                    if (numero < 0) {
                        System.out.println(ROJO + "Error. Ingrese un numero positivo." + RESET);
                    } else {
                        String hexadecimal = DecimalaHexadecimal(numero);

                        System.out.println(VERDE + "El numero decimal " + numero +
                                " en hexadecimal es " + hexadecimal + RESET);
                    }
                    break;
                }

                case 4: {
                    int numero;

                    System.out.print("Ingrese un numero entero decimal positivo: ");
                    numero = sc.nextInt();

                    if (numero < 0) {
                        System.out.println(ROJO + "Error. Ingrese un numero positivo." + RESET);
                    } else {
                        System.out.println(VERDE + "Binario: " + DecimalaBinario(numero) + RESET);
                        System.out.println(VERDE + "Octal: " + DecimalaOctal(numero) + RESET);
                        System.out.println(VERDE + "Hexadecimal: " + DecimalaHexadecimal(numero) + RESET);
                    }
                    break;
                }

                case 5:
                    System.out.println(CYAN + "Regresando al menu principal..." + RESET);
                    break;

                default:
                    System.out.println(ROJO + "Opcion no valida." + RESET);
                    break;
            }

        } while (opcion != 5);
    }

    // Convierte un numero decimal a binario mediante divisiones para 2
    public static int DecimalaBinario(int num) {

        int binario = 0;
        int multiplicador = 1;
        int residuo;

        while (num > 0) {
            residuo = num % 2;
            binario = binario + residuo * multiplicador;

            multiplicador = multiplicador * 10;

            num = num / 2;
        }

        return binario;
    }

    // Convierte un numero decimal a octal mediante divisiones para 8
    public static int DecimalaOctal(int num) {

        int octal = 0;
        int multiplicador = 1;
        int residuo;

        while (num > 0) {
            residuo = num % 8;
            octal = octal + residuo * multiplicador;

            multiplicador = multiplicador * 10;

            num = num / 8;
        }

        return octal;
    }

    // Convierte un numero decimal a hexadecimal mediante divisiones para 16
    public static String DecimalaHexadecimal(int num) {

        int residuo;
        String hexadecimal = "";

        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            residuo = num % 16;

            if (residuo < 10) {
                hexadecimal = residuo + hexadecimal;
            } else {
                hexadecimal = (char) (residuo + 55) + hexadecimal;
            }

            num = num / 16;
        }

        return hexadecimal;
    }
}
