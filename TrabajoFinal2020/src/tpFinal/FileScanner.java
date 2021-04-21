package tpFinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import conjuntistas.ArbolAVL;
import lineales.dinamicas.Lista;
import tdaEspeciales.Diccionario;
import tdaGrafo.Grafo;

public class FileScanner {

	public static void leerAvion(String direccion,Grafo a,Logger logger) {
		File f = new File(direccion);
		Scanner s;
		try {
			s = new Scanner(f);
			logger.info("COMIENZA CARGA DE DATOS DE AVIONES");
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("-|\n");
				String nombreAereonautico = sl.next();
				String ciudad = sl.next();
				String telefono = sl.next();
				Aereopuerto nuevo = new Aereopuerto(nombreAereonautico,ciudad,telefono);
				a.insertarVertice(nuevo);
				logger.info("Se ha INTRODUCIDO el Aereopuerto con nombre Aereonautico: " + nombreAereonautico+ "\n");
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerVuelo(String direccion,Grafo a,Diccionario vuelos,Logger logger) {
		File f = new File(direccion);
		Scanner s;
		try {
			s = new Scanner(f);
			logger.info("\n COMIENZA CARGA DE DATOS DE VUELOS");
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("-|\n");
				String codigo = sl.next();
				String origen = sl.next();
				String destino = sl.next();
				int salida = sl.nextInt();
				int llegada = sl.nextInt();
				Aereopuerto o = new Aereopuerto(origen);
				Aereopuerto d = new Aereopuerto(destino);
				Vuelo nuevo = new Vuelo(codigo,origen,destino,salida,llegada);
				vuelos.insertar(codigo, nuevo);
				int minutos = (llegada-salida) * 60;
				a.insertarArco(o, d, minutos);
				logger.info("El Vuelo: "+codigo+" ha sido INGRESADO CORRECTAMENTE \n");
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerViaje(String direccion,Grafo a,Diccionario vuelos,Logger logger) {
		File f = new File(direccion);
		Scanner s;
		try {
			s = new Scanner(f);
			logger.info("\n COMIENZA CARGA DE DATOS DE UN VIAJE");
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("-|\n");
				String codigo = sl.next();
				String fecha = sl.next();
				int asientosTotales = sl.nextInt();
				Vuelo x = (Vuelo) vuelos.obtenerDato(codigo);
				Viaje nuevo = new Viaje(fecha,asientosTotales,new ArbolAVL());
				x.getViajes().insertar(nuevo, 1);
				logger.info("El Vuelo: "+codigo+" ha sido INGRESADO CORRECTAMENTE \n");
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerCliente(String direccion,Diccionario clientes,HashMap pasajes,Logger logger) {
		File f = new File(direccion);
		Scanner s;
		try {
			s = new Scanner(f);
			logger.info("\n COMIENZA CARGA DE DATOS DE CLIENTE");
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("-|\n");
				String numeroDni = sl.next();
				String tipoDni = sl.next();
				String nombre = sl.next();
				String apellido = sl.next();
				String telefono = sl.next();
				String domicilio = sl.next();
				String fechaNac = sl.next();
				ClaveCliente claveC = new ClaveCliente(numeroDni,tipoDni);
				Cliente nuevo = new Cliente(numeroDni,tipoDni,nombre,apellido,fechaNac,domicilio,telefono);
				pasajes.put(claveC, new Lista());
				clientes.insertar(claveC, nuevo);
				logger.info("El cliente con numero de DNI: "+numeroDni+" con tipo de DNI: "+tipoDni+" ha sido INGRESADO CORRECTAMENTE");
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void leerPasaje(String direccion,Diccionario clientes,HashMap pasajes,Diccionario vuelos,Logger logger) {
		File f = new File(direccion);
		Scanner s;
		try {
			s = new Scanner(f);
			logger.info("\n COMIENZA CARGA DE DATOS DE PASAJE");
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("-|\n");
				String numDni = sl.next();
				String tipoDni = sl.next();
				String codigoVuelo = sl.next();
				String fecha = sl.next();
				int numAsiento = sl.nextInt();
				
				//Logica de insertado del Pasaje.
				Vuelo auxV = (Vuelo)vuelos.obtenerDato(codigoVuelo);
				ClaveCliente cc = new ClaveCliente(numDni,tipoDni);
				Lista ls = auxV.getViajes();
				Lista lsPasaje = (Lista)pasajes.get(cc);
				Viaje viaje = null;
				boolean exito = true;
				for(int i = 1; i <= ls.longitud() && exito; i++) {
					viaje = (Viaje) ls.recuperar(i);
					if(fecha.equals(viaje.getFecha()))	//Recorro la Lista de viajes hasta encontrar la que tenga la fecha indicada.
						exito = false;
				}
				ArbolAVL avl = viaje.getAsientos();
				avl.insertar(numAsiento);
				viaje.setCantVendidos(avl.getCantAsientos());
				Pasaje nuevo = new Pasaje(codigoVuelo,numAsiento,"pendiente",fecha);
				lsPasaje.insertar(nuevo, 1);
				logger.info("El cliente con numero de DNI: "+numDni+" con tipo de DNI: "+tipoDni+" ha COMPRADO UN PASAJE PARA EL VUELO: " + codigoVuelo + " con FECHA: " +fecha);
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}









