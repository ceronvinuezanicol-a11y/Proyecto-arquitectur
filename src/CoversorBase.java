import java.util.Scanner;

public class CoversorBase {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String VERDE = "\u001B[32m";
    public static final String ROJO = "\u001B[31m";
    public static final String PURPURA = "\u001B[35m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            limpiarPantalla();
            System.out.println(CYAN + "========================================================");
            System.out.println("          SISTEMA INTEGRAL DE CONVERSIONES        ");
            System.out.println("========================================================" + RESET);
            System.out.println(" [1] Fase 1: Conversión de Decimal a Otras Bases");
            System.out.println(" [2] Fase 2: Conversión de Otras Bases a Decimal");
            System.out.println(" [3] Fase 3: Aritmética y Complemento a Dos");
            System.out.println(" [4] Fase 4: Módulo Avanzado (Suma y Resta en Ca2)");
            System.out.println(ROJO + " [5] Salir del Sistema" + RESET);
            System.out.println(CYAN + "========================================================" + RESET);
            System.out.print(" Seleccione una categoría: ");

            if (!sc.hasNextInt()) {
                System.out.println(ROJO + "Por favor, ingrese un número válido." + RESET);
                sc.next();
                pausaLocal(sc);
                continue;
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    Fase1.ejecutar(sc);
                    break;
                case 2:
                    Fase2.ejecutar(sc);
                    break;
                case 3:
                    Fase3.ejecutar(sc);
                    break;
                case 4:
                    Fase4.ejecutar(sc);
                    break;
                case 5:
                    System.out.println(VERDE + "\n¡Saliendo del sistema con éxito!" + RESET);
                    break;
                default:
                    System.out.println(ROJO + "\nOpción no válida. Intente de nuevo." + RESET);
                    pausaLocal(sc);
            }
        } while (opcion != 5);
        sc.close();
    }

    public static void pausaLocal(Scanner sc) {
        System.out.print(CYAN + "\nPresione Enter para continuar..." + RESET);
        sc.nextLine();
        sc.nextLine();
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}