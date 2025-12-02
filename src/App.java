package src;

import java.util.Scanner;
import java.util.InputMismatchException;

public class App {
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            Fecha fecha = leerFecha();
            if (fecha.comprobarBisiesto())
                System.out.println("La fecha introducida es de un año bisiesto :D");
            else
                System.out.println("La fecha introducida no es de un año bisiesto :(");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static int leerEntero(String msg) {
		int numero = 0;
		boolean correcto = false;
		
		do {
			System.out.println("Introduzca " + msg + ":");
			try {
				numero = sc.nextInt();
				sc.nextLine();
				correcto = true;
				
			} catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("Error. Debe introducir un número: ");
			}
			
		} while(!correcto);
		
		return numero;
    }

    public static Fecha leerFecha() throws Exception {
        int anio = leerEntero("año");
        return new Fecha(anio);
    }
}
