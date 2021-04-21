package lineales.dinamicas;

import Utiles.TecladoIn;
import lineales.dinamicas.Cola;

public class TestCola {
	public static void main(String[] args) {

		Cola nueva = new Cola();
		
		int x = -1;
		int y;

		while(x != 0) {

			System.out.println("Introduzca una opcion.");

			menu();

			x = TecladoIn.readLineInt();

			switch(x) {

			case 1: 
				boolean si;
				System.out.println("Introduzca un ELEMENTO para el frente de la cola.");
				y = TecladoIn.readLineInt();
				si = nueva.poner(y);
				if(!si) 
					System.out.println("COLA LLENA");
				break;
			case 2:
				nueva.sacar();
				break;
			case 3:
				System.out.println(nueva.obtenerFrente());
				break;
			case 4:	
				System.out.println(nueva.esVacia());
				break;
			case 5:
				nueva.vaciar();
				break;
			case 6:
				Cola c2 = nueva.clone();
				System.out.println(c2.toString());
				break;
			case 7:
				System.out.println(nueva.toString());
				break;
			}


		}
		
	}

	public static void menu() {

		System.out.println("1) Poner.");
		System.out.println("2) Sacar.");
		System.out.println("3) Obtener Frente.");
		System.out.println("4) Verificar si la COLA esta vacia.");
		System.out.println("5) Vaciar la COLA.");
		System.out.println("6) Clonar COLA.");
		System.out.println("7) ToString.");
		System.out.println("0) QUIT.");		
		}
	
}
