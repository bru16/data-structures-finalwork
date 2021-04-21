package tpFinal;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Utiles.TecladoIn;
import conjuntistas.ArbolAVL;
import conjuntistas.Heap;
import conjuntistas.HeapEspecial;
import lineales.dinamicas.Lista;
import tdaGrafo.Grafo;
import tdaEspeciales.Diccionario;

public class SistemaViajes {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Grafo aereopuertos = new Grafo();
		Diccionario clientes = new Diccionario();
		Diccionario vuelos = new Diccionario();
		HashMap <ClaveCliente,Lista> pasajes = new HashMap();
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;  
		logger.setUseParentHandlers(false);
		String ruta = System.getProperty("user.dir") + "/src";
		try {

			// This block configure the logger with handler and formatter 
			fh = new FileHandler(ruta+"/lectura/MyLog.txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  

			// the following statement is used to log any messages  
		} catch (SecurityException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
		
		FileScanner.leerAvion(ruta+"/lectura/a.txt", aereopuertos,logger);
		FileScanner.leerCliente(ruta+"/lectura/c.txt",clientes,pasajes,logger);
		FileScanner.leerVuelo(ruta+"/lectura/vuelo.txt",aereopuertos,vuelos,logger);
		FileScanner.leerViaje(ruta + "/lectura/viaje.txt",aereopuertos,vuelos,logger);
		FileScanner.leerPasaje(ruta+"/lectura/p.txt",clientes,pasajes,vuelos,logger);



		// MENU
		int opcion = 1;
		while (opcion != 0) {
			menu();
			opcion = TecladoIn.readLineInt();
			switch (opcion) {
			case 0:
				System.out.println("Hasta la proxima.");
				break;
			case 1:
				abmAereopuerto();
				int op = TecladoIn.readLineInt();
				switch(op) {
				case 1:
					insertarAereopuerto(aereopuertos);
					break;
				case 2:
					eliminarAereopuerto(aereopuertos);
					break;
				case 3:
					modificarAereopuerto(aereopuertos);
					break;
				}
				break;
			case 2:
				abmCliente();
				int op2 = TecladoIn.readLineInt();
				switch(op2) {
				case 1:
					insertarCliente(clientes,pasajes);
					break;
				case 2:
					eliminarCliente(clientes,pasajes);
					break;
				case 3:
					modificarCliente(clientes);
					break;
				}
				break;
			case 3:
				abmVuelo();
				int op3 = TecladoIn.readLineInt();
				switch(op3) {
				case 1:
					insertarVuelo(vuelos,aereopuertos);
					break;
				case 2:
					eliminarVuelo(vuelos,aereopuertos,pasajes);
					break;
				case 3:
					modificarVuelo(vuelos,aereopuertos,pasajes);
					break;
				}
				break;
			case 4:
				abmPasaje();
				int op4 = TecladoIn.readLineInt();
				switch(op4) {
				case 1:
					comprarPasaje(clientes,pasajes,vuelos);
					break;
				case 2:
					cancelarPasaje(clientes,pasajes,vuelos);
					break;
				case 3:
					modificarPasaje(clientes,pasajes,vuelos);
					break;
				}
				break;
			case 5:
				listadoPasajes(clientes,pasajes);
				break;
			case 6:
				historialVuelos(clientes,pasajes,vuelos,aereopuertos);
				break;
			case 7:
				listarVuelo(vuelos);
				break;
			case 8:
				System.out.println(vuelos.toString());
				listarRangoVuelos(vuelos);
				break;
			case 9:
				System.out.println(maximoXVuelos(aereopuertos));
				break;
			case 10:
				menorTiempo(aereopuertos);
				break;
			case 11:
				System.out.println(promocionClientes(clientes,pasajes).toString());
				break;
			case 12:
				estadoSistema(aereopuertos,clientes,vuelos,pasajes);
				break;
			}


		}
	}



	public static void menu() {

		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Ingrese el número correspondiente a la opción deseada \n ");
		System.out.println("1. ABM (Altas-Bajas-Modificaciones) de aeropuertos");
		System.out.println("2. ABM (Altas-Bajas-Modificaciones) de clientes");
		System.out.println("3. ABM (Altas-Bajas-Modificaciones) de vuelos");
		System.out.println("4. ABM (Altas-Bajas-Modificaciones) de pasajes");
		System.out.println("5. Mostrar informacion de contacto de un cliente y sus pasajes comprados pendientes de volar");
		System.out.println("6. Mostrar ciudades que ha visitado un cliente, segun su historial de vuelos.");
		System.out.println("7. Mostrar informacion de un vuelo.");
		System.out.println("8. Dados dos vuelos, mostrar todos los vuelos existentes que están en el rango entre el menor y el mayor de ambos vuelos");
		System.out.println("9. Mostrar si es posible que un cliente que parte del aeropuerto A llegue al B en como máximo X vuelos");
		System.out.println("10. Obtener el camino que llegue de A a B de menor tiempo de vuelo");
		System.out.println("11. Listado de los clientes que han comprado más pasajes de MAYOR a MENOR.");
		System.out.println("12 Estado del Sistema.");
		System.out.println("0. Salir.");
		System.out.println("-------------------------------------------------------------------------");

	}

	public static void abmAereopuerto() {

		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Ingrese el número correspondiente a la opción deseada \n");
		System.out.println("1. Insertar Aereopuerto");
		System.out.println("2. Eliminar Aereopuerto.");
		System.out.println("3. Modificar datos de Aereopuerto.");
		System.out.println("0. Volver al menu.");
		System.out.println("-------------------------------------------------------------------------");
	}
	
	public static void abmCliente() {

		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Ingrese el número correspondiente a la opción deseada \n");
		System.out.println("1. Insertar Cliente");
		System.out.println("2. Eliminar Cliente.");
		System.out.println("3. Modificar datos de Cliente.");
		System.out.println("0. Volver al menu.");
		System.out.println("-------------------------------------------------------------------------");
	}
	
	public static void abmVuelo() {

		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Ingrese el número correspondiente a la opción deseada \n");
		System.out.println("1. Insertar Vuelo");
		System.out.println("2. Eliminar Vuelo.");
		System.out.println("3. Modificar datos de un Vuelo.");
		System.out.println("0. Volver al menu.");
		System.out.println("-------------------------------------------------------------------------");
	}

	public static void abmPasaje() {

		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Ingrese el número correspondiente a la opción deseada \n");
		System.out.println("1. Comprar Pasaje");
		System.out.println("2. Cancelar Pasaje.");
		System.out.println("3. Modificar datos de un Pasaje.");
		System.out.println("0. Volver al menu.");
		System.out.println("-------------------------------------------------------------------------");
	}

	public static boolean insertarAereopuerto(Grafo aereopuertos) {

		boolean exito = false;
		System.out.print("Ingrese el nombre Aereonautico: "); String nombre = TecladoIn.readLine();
		if(!nombre.matches(".*\\d.*") && nombre.length() <= 3 && nombre.length() > 0) {
			Aereopuerto nuevo = new Aereopuerto(nombre);
			if(!aereopuertos.existeVertice(nuevo)) {
				System.out.print("Ingrese la ciudad: "); String ciudad = TecladoIn.readLine();
				System.out.print("Ingrese el telefono: "); String tel = TecladoIn.readLine();
				nuevo = new Aereopuerto(nombre,ciudad,tel);
				aereopuertos.insertarVertice(nuevo);
				System.out.println("Aereopuerto agregado correctamente! \n");
				exito = true;
				escribirLOG("Se ha INTRODUCIDO el Aereopuerto con nombre Aereonautico: " + nombre);
			}
			else
				System.out.println("Ese Aereopuerto ya existe, intente con otro.");
		}
		else
			System.out.println("Nombre aereonautico no es valido, intente nuevamente.");
		return exito;
	}

	public static boolean eliminarAereopuerto(Grafo a) {

		boolean exito = false;
		System.out.println("Introduzca el aereopuerto a eliminar (clave). --- (SOLO se eliminaran Aereopuertos sin VUELOS)");
		String nom = TecladoIn.readLine();
		if(!nom.matches(".*\\d.*") && nom.length() <= 3 && nom.length() > 0) {
			Aereopuerto buscado = new Aereopuerto(nom);
			boolean e = a.existeVertice(buscado);
			if(e && !a.tieneArcos(buscado)) {
				exito = true;
				Aereopuerto aux = (Aereopuerto) a.ubicarElemento(buscado);
				a.eliminarVertice(aux);
				System.out.println("La eliminacion ha sido exitosa! \n");
				escribirLOG("El Aereopuerto " + nom + " ha sido ELIMINADO.");
			}
			else
				System.out.println("No ha sido posible eliminar el Aereopuerto.");
		}
		else
			System.out.println("Nombre aereonautico no es valido, intente nuevamente.");
		return exito;
	}

	public static boolean modificarAereopuerto(Grafo a) {
		//modifica solo el telefono
		boolean exito = false;
		System.out.println("Introduzca la clave del aereopuerto a modificar");
		String x = TecladoIn.readLine();
		if(!x.matches(".*\\d.*") && x.length() <= 3 && x.length() > 0) {
			// aerepuerto valido
			Aereopuerto buscado = new Aereopuerto(x);
			boolean e = a.existeVertice(buscado);
			if(e) {
				exito = true;
				System.out.println("Introduzca el nuevo telefono.");
				String tel = TecladoIn.readLine();
				Aereopuerto aux = ((Aereopuerto) a.ubicarElemento(buscado));
				aux.setTelefono(tel);
				System.out.println(aux.toString());
				escribirLOG("El Aereopuerto " + x + " ha sido MODIFICADO.");
			}
			else
				System.out.println("No existe tal Aereopuerto.");
		}
		else {
			System.out.println("Nombre aereonautico no es valido, intente nuevamente.");
		}
		return exito;
	}

	public static boolean insertarCliente(Diccionario clientes, HashMap pasajes) {
		
		boolean exito = false;
		System.out.println("Ingrese numero de DNI del cliente");
		String num = TecladoIn.readLine();
		System.out.println("Ingrese tipo de DNI");
		String tipo = TecladoIn.readLine();
		ClaveCliente aux = new ClaveCliente(num,tipo);
		if(!clientes.existeClave(aux)) {
			//si no existe el Cliente lo creo
			System.out.println("Ingrese Nombre");
			String nombre = TecladoIn.readLine();
			System.out.println("Ingrese Apellido");
			String apellido = TecladoIn.readLine();
			System.out.println("Ingrese fecha de nacimiento");
			String fecha = TecladoIn.readLine();
			System.out.println("Ingrese domicilio");
			String dom = TecladoIn.readLine();
			System.out.println("Ingrese telefono");
			String telef = TecladoIn.readLine();
			Cliente cliente2 = new Cliente(num,tipo,nombre,apellido,fecha,dom,telef);
			clientes.insertar(aux, cliente2);
			exito = true;
			pasajes.put(aux, new Lista());
			escribirLOG("El cliente con numero de DNI: "+num+" con tipo de DNI: "+tipo+" ha sido INGRESADO CORRECTAMENTE");
		}
		else
			System.out.println("Cliente no es valido/ya existe.");
		
		return exito;
	}
	
	public static boolean eliminarCliente(Diccionario clientes,HashMap pasajes) {
		
		boolean exito = false;
		ClaveCliente aux = verificarCliente(clientes);	// Verifica si existe un cliente.
		if(aux != null) {
			//Si existe lo elimino.
			exito = true;
			clientes.eliminar(aux);
			pasajes.remove(aux);
			escribirLOG("El cliente con numero de DNI: " + aux.getNumDni() + " y tipo de DNI: "+aux.getTipoDni() + " ha sido ELIMINADO CORRECTAMENTE");
		}
		else 
			System.out.println("El cliente no es valido/existe.");
		
		return exito;
		
	}

	public static boolean modificarCliente(Diccionario clientes) {

		boolean exito = false;
		ClaveCliente aux = verificarCliente(clientes);
		if(aux != null) {
			//Si existe lo modifico
			System.out.println("Introduzca nuevo domicilio");
			String dom = TecladoIn.readLine();
			System.out.println("Introduzca nuevo telefono");
			String tele = TecladoIn.readLine();
			Cliente auxCliente = (Cliente) clientes.obtenerDato(aux);
			auxCliente.setDomicilio(dom);
			auxCliente.setTelefono(tele);
			exito = true;
			escribirLOG("El cliente con numero de DNI: " + aux.getNumDni() + " y tipo de DNI: "+aux.getTipoDni() + " ha sido MODIFICADO CORRECTAMENTE");
		}
		else 
			System.out.println("El cliente no es valido/existe.");

		return exito;

	}

	public static boolean insertarVuelo(Diccionario vuelos, Grafo aereopuertos) {

		boolean exito = false;
		System.out.println("Ingrese codigo de vuelo");
		String claveVuelo = TecladoIn.readLine();
		if(claveVuelo.length() <= 6 && claveVuelo.length() > 0) {
			if(!vuelos.existeClave(claveVuelo)) {
				//Clave valida y no existe
				System.out.println("Ingrese Aereopuerto origen");
				String origen = TecladoIn.readLine();
				System.out.println("Ingrese Aereopuerto destino");
				String destino = TecladoIn.readLine();
				if(!origen.matches(".*\\d.*") && origen.length() <= 3 && origen.length() > 0 && !destino.matches(".*\\d.*") && destino.length() <= 3 && destino.length() > 0) {
					//Aereopuertos validos
					Aereopuerto auxOrigen = new Aereopuerto(origen);
					Aereopuerto auxDestino = new Aereopuerto(destino);
					if(aereopuertos.existeVertice(auxOrigen) && aereopuertos.existeVertice(auxDestino)) {
						//Si existen los aereopuertos entonces es posible agregar el vuelo.
						System.out.print("Ingrese hora de salida del vuelo(HH): ");	int salida = TecladoIn.readLineInt();
						System.out.print("Ingrese hora de llegada del vuelo(HH): "); int llegada = TecladoIn.readLineInt();
						Vuelo nuevo = new Vuelo(claveVuelo,origen,destino,salida,llegada);
						vuelos.insertar(claveVuelo, nuevo);
						//Creo el arco etiquetado entre los 2 aereopuertos
						int minutos = calcularMinutos(salida,llegada);
						aereopuertos.insertarArco(auxOrigen, auxDestino, minutos);	//Arco etiquetado
						exito = true;
						escribirLOG("El Vuelo: "+claveVuelo+" ha sido INGRESADO CORRECTAMENTE");
					}
					else
						System.out.println("Aereopuertos no existen.");
				}
				else
					System.out.println("Aerepuertos no validos");
			}
			else {
				//El vuelo ya existe y creo un viaje para insertarlo en la Lista de viajes de ese vuelo
				Vuelo aux = (Vuelo) vuelos.obtenerDato(claveVuelo);
				System.out.println("Introduzca fecha del Viaje");
				String fecha = TecladoIn.readLine();
				System.out.println("Introduzca cantidad asientos totales");
				int aTotal = TecladoIn.readLineInt();
				Viaje nuevo = new Viaje(fecha,aTotal,new ArbolAVL());
				Lista ls = aux.getViajes();
				ls.insertar(nuevo, 1);
				exito = true;
				escribirLOG("El Viaje del Vuelo" + claveVuelo+ " con Fecha: " +fecha+" ha sido INGRESADO CORRECTAMENTE");
				
			}
		}
		else
			System.out.println("Codigo de vuelo no valido.");

		return exito;
	}

	public static boolean eliminarVuelo(Diccionario vuelos, Grafo aereopuertos, HashMap pasajes) {
		
		boolean exito = false;
		
		System.out.println("Ingrese codigo de vuelo");
		String codigo = TecladoIn.readLine();
		if(vuelos.existeClave(codigo)) {
			exito = true;
			Vuelo aux = (Vuelo) vuelos.obtenerDato(codigo);	//Obtengo el vuelo
			eliminarVueloAux(aux,aereopuertos,pasajes);	//Elimina el ARCO entre los 2 Aereopuertos y ademas CANCELA los pasajes de los clientes que tienen el Vuelo
			vuelos.eliminar(codigo);	//Elimino vuelo del arbol AVL vuelos.
			escribirLOG("El Vuelo: "+codigo+" ha sido ELIMINADO CORRECTAMENTE");
		}
		else
			System.out.println("Codigo no existe");

		return exito;
	}

	public static boolean modificarVuelo(Diccionario vuelos, Grafo aereopuertos,HashMap pasajes) {

		boolean exito = false;
	
		System.out.println("Ingrese codigo de vuelo");
		String codigo = TecladoIn.readLine();
		if(vuelos.existeClave(codigo)) {
			exito = true;
			System.out.println("¿Que desea modificar?");
			System.out.println("1. MODIFICAR TOTALMENTE EL VUELO (Origen-destino y hora salida-llegada)");
			System.out.println("2. MODIFICAR solamente hora de salida y llegada del vuelo");
			int op = TecladoIn.readLineInt();
			switch(op) {
			case 1:
				System.out.println("Ingrese NUEVO Aereopuerto origen: ");
				String origen = TecladoIn.readLine();
				System.out.println("Ingrese NUEVO Aereopuerto destino: ");
				String destino = TecladoIn.readLine();
				Aereopuerto o = new Aereopuerto(origen);
				Aereopuerto d = new Aereopuerto(destino);
				if(aereopuertos.existeVertice(o) && aereopuertos.existeVertice(d)) {
					//Si existen los Aereopuertos es posible modificar origen y destino.
					//Elimino Vuelo anterior.
					Vuelo auxVuelo = (Vuelo) vuelos.obtenerDato(codigo);
					eliminarVueloAux(auxVuelo,aereopuertos,pasajes);	//Elimina el ARCO entre los 2 Aereopuertos y ademas CANCELA los pasajes de los clientes que tienen el Vuelo
					System.out.print("Ingrese NUEVA hora de salida (HHMM): "); int salida = TecladoIn.readLineInt();
					System.out.print("Ingrese NUEVA hora de llegada (HHMM):"); int llegada = TecladoIn.readLineInt();
					int minutos = calcularMinutos(salida,llegada);
					//Modifico datos del vuelo.
					auxVuelo.setOrigen(origen);
					auxVuelo.setDestino(destino);
					auxVuelo.setHoraLlegada(llegada);
					auxVuelo.setHoraSalida(salida);
					aereopuertos.insertarArco(o, d, minutos);
					escribirLOG("El Vuelo: "+codigo+" ha sido MODIFICADO CORRECTAMENTE");
				}
				else
					System.out.println("Los aereopuertos no existen.");
				break;
			case 2:
				System.out.print("Ingrese NUEVA hora de salida (HHMM): "); int salida = TecladoIn.readLineInt();
				System.out.print("Ingrese NUEVA hora de llegada (HHMM):"); int llegada = TecladoIn.readLineInt();
				Vuelo auxVuelo = (Vuelo) vuelos.obtenerDato(codigo);
				int minutos = calcularMinutos(salida,llegada);
				int minutosOriginal = calcularMinutos(auxVuelo.getHoraSalida(), auxVuelo.getHoraLlegada());
				auxVuelo.setHoraLlegada(llegada);
				auxVuelo.setHoraSalida(salida);
				Aereopuerto a1 = new Aereopuerto(auxVuelo.getOrigen());
				Aereopuerto a2 = new Aereopuerto(auxVuelo.getDestino());
				a1 = (Aereopuerto) aereopuertos.ubicarElemento(a1);
				a2 = (Aereopuerto) aereopuertos.ubicarElemento(a2);
				aereopuertos.eliminarArco(a1, a2, minutosOriginal);
				aereopuertos.insertarArco(a1, a2, minutos);
				escribirLOG("El Vuelo: "+codigo+" ha sido MODIFICADO CORRECTAMENTE");
				break;
			}
		}
		else
			System.out.println("El vuelo no existe.");
		return exito;
	}

	public static boolean comprarPasaje(Diccionario clientes,HashMap pasajes, Diccionario vuelos) {

		System.out.println(pasajes.toString());
		boolean exito = false;
		ClaveCliente aux = verificarCliente(clientes);
		if(aux != null) {
			// Si existe el Cliente entonces le agrego pasaje.
			exito = true;
			System.out.println("Introduzca codigo vuelo");
			String cod = TecladoIn.readLine();
			Vuelo auxV = (Vuelo)vuelos.obtenerDato(cod);
			if(vuelos.existeClave(cod) && !auxV.sinViajes()) {	// Si existe el vuelo puedo comprar un pasaje. Y la lista de Viajes de la clase Vuelo, NO Esta vacia.
				Lista viajes = auxV.getViajes();
				Lista auxLista = (Lista)pasajes.get(aux);
				Pasaje nuevo = comprarPasajeAux(cod,viajes);
				if(nuevo != null) {
					auxLista.insertar(nuevo, 1);
					escribirLOG("El cliente con numero de DNI: " + aux.getNumDni() + " y tipo de DNI: "+aux.getTipoDni() + " ha COMPRADO UN PASAJE para el Vuelo: " +cod);
				}
				else
					System.out.println("No se ha podido comprar el pasaje.");
			}
			else
				System.out.println("No existe el vuelo/viaje.");
		}
		return exito;
	}

	public static boolean cancelarPasaje(Diccionario clientes,HashMap pasajes,Diccionario vuelos) {

		boolean exito = false;
		ClaveCliente aux = verificarCliente(clientes);
		if(aux != null) {
			// Si existe el Cliente entonces le elimino pasaje.
			System.out.println("Introduzca codigo vuelo para cancelar el pasaje");
			String cod = TecladoIn.readLine();
			if(vuelos.existeClave(cod)) {
				System.out.println("Introduzca la fecha del Vuelo a cancelar.");
				String fecha = TecladoIn.readLine();
				Lista auxLista = (Lista) pasajes.get(aux);
				Pasaje nuevo = new Pasaje(cod,fecha);
				int x = auxLista.localizar(nuevo);	//localizo y recupero el pasaje con el codigo de vuelo ingresado.
				if(x != -1) {
					nuevo = (Pasaje) auxLista.recuperar(x);
					nuevo.setEstado("cancelado");
					exito = true;
					escribirLOG("El cliente con numero de DNI: " + aux.getNumDni() + " y tipo de DNI: "+aux.getTipoDni() + " ha CANCELADO el PASAJE con Vuelo: "+cod);
				}
			}
		}
		return exito;
	}

	public static boolean modificarPasaje(Diccionario clientes,HashMap pasajes,Diccionario vuelos) {

		boolean exito = false;
		ClaveCliente aux = verificarCliente(clientes);
		if(aux != null) {
			// Si existe el Cliente entonces le modifico pasaje.
			Lista auxLista = (Lista) pasajes.get(aux);
			System.out.println("Introduzca codigo vuelo para modificar el pasaje");
			String cod = TecladoIn.readLine();
			if(vuelos.existeClave(cod)) {
				Vuelo auxV = (Vuelo)vuelos.obtenerDato(cod);
				System.out.println(auxV.getViajes().toString());
				System.out.println("\n Introduzca la fecha del Vuelo a modificar");
				String fecha = TecladoIn.readLine();
				Pasaje nuevo = new Pasaje(cod,fecha);
				int x = auxLista.localizar(nuevo);	//localizo y recupero el pasaje con el codigo de vuelo ingresado.
				if(x != -1) {
					Lista ls = auxV.getViajes();
					Viaje viaje = null;
					exito = true;
					nuevo = (Pasaje) auxLista.recuperar(x);
					System.out.println("Introduzca numero de asiento");
					int asiento = TecladoIn.readLineInt();
					for(int i = 1; i <= ls.longitud() && exito; i++) {
						viaje = (Viaje) ls.recuperar(i);
						if(fecha.equals(viaje.getFecha()))	//Recorro la Lista de viajes hasta encontrar la que tenga la fecha indicada.
							exito = false;
					}
					ArbolAVL avl = (ArbolAVL) viaje.getAsientos();
					if(!avl.pertenece(asiento)) {	// si no existe el asiento puedo modificarlo.
						avl.eliminar(nuevo.getNumAsiento());
						avl.insertar(asiento);
						System.out.println("Introduzca nuevo estado");
						String estado = TecladoIn.readLine();
						nuevo.setEstado(estado);
						nuevo.setNumAsiento(asiento);
						escribirLOG("El cliente con numero de DNI: " + aux.getNumDni() + " y tipo de DNI: "+aux.getTipoDni() + " ha MODIFICADO el PASAJE con Vuelo: "+cod);
					}
					else
						System.out.println("Ese asiento ya esta OCUPADO!");
				}
				else
					System.out.println("El cliente no tiene pasaje/s de ese vuelo..");
			}
			else
				System.out.println("No existe el vuelo.");
		}
		return exito;
	}

	//Consultas sobre Clientes

	public static void listadoPasajes(Diccionario clientes, HashMap pasajes) {

		boolean exito = true;
		ClaveCliente aux = verificarCliente(clientes);
		if(aux != null) {
			String s = clientes.obtenerDato(aux).toString();
			System.out.println(s);
			Lista auxLista = (Lista) pasajes.get(aux);
			Pasaje auxPasaje;
			for(int i = 1; i <= auxLista.longitud(); i++) {
				auxPasaje = (Pasaje)auxLista.recuperar(i);
				if(auxPasaje.getEstado().equals("pendiente")) {
					exito = false;
					System.out.println("\n VUELO: " + auxPasaje.getCodigoVuelo() + " NUMERO DE ASIENTO: " + auxPasaje.getNumAsiento() + " FECHA:  " + auxPasaje.getFecha() + "\n");
				}
			}
			if(exito)
				System.out.println("El cliente: "+aux+" no tiene pasajes pendientes de volar.");
		}
		else
			System.out.println("Cliente NO Valido");
	}

	public static void historialVuelos(Diccionario clientes, HashMap pasajes, Diccionario vuelos, Grafo aereopuertos) {

		ClaveCliente aux = verificarCliente(clientes);
		if(aux != null) {
			Lista auxLista = (Lista) pasajes.get(aux);
			Pasaje auxPasaje;
			Vuelo auxVuelo;
			String ciudad1,ciudad2 = null;
			boolean exito = true;
			for(int i = 1; i <= auxLista.longitud(); i++) {
				auxPasaje = (Pasaje) auxLista.recuperar(i);	//Voy recuperando los pasajes
				if(auxPasaje.getEstado().equals("volado")) {
					exito = false;
					auxVuelo = (Vuelo) vuelos.obtenerDato(auxPasaje.getCodigoVuelo());	//Obtengo la instancia del Vuelo
					Aereopuerto auxAer2 = new Aereopuerto(auxVuelo.getOrigen());
					Aereopuerto auxAer = new Aereopuerto(auxVuelo.getDestino());	// Creo nuevo Aereopuerto para pasarlo por parametro al grafo y obtener el Aereopuerto con el destino correcto
					auxAer2 = (Aereopuerto) aereopuertos.ubicarElemento(auxAer2);
					auxAer = (Aereopuerto) aereopuertos.ubicarElemento(auxAer);//Obtengo la instancia del Aereopuerto
					ciudad1 = auxAer.getCiudad();	//Obtengo la ciudad del Aereopuerto
					System.out.println(auxAer2.getCiudad());
					if(!ciudad1.equals(ciudad2))	//para que no se repitan las ciudades.
						System.out.println(ciudad1 + "\n");
					ciudad2 = ciudad1;	//para ir comparando que no se repitan.
				}
			}
			if(exito)
				System.out.println("El cliente "+aux+" no ha visitado ninguna ciudad aun.");
		}
	}

	//Consultas sobre Vuelos

	public static void listarVuelo(Diccionario vuelos) {

		System.out.println("Introduzca un Vuelo");
		String codigo = TecladoIn.readLine();
		if(vuelos.existeClave(codigo)) {
			Vuelo auxVuelo = (Vuelo) vuelos.obtenerDato(codigo);	//obtengo al Vuelo.
			if(!auxVuelo.sinViajes()) {	//Si NO esta sin Viajes (es decir, su Lista de viajes no es vacia)
				System.out.println("A continuacion se le mostrara las FECHAS del vuelo.");
				System.out.println(auxVuelo.getViajes().toString());
				System.out.println("Introduzca la fecha del Vuelo");
				String fecha = TecladoIn.readLine();
				boolean exito = true;
				Lista auxLista = auxVuelo.getViajes();
				for(int i = 1; i <= auxLista.longitud() && exito; i++) {
					Viaje v = (Viaje) auxLista.recuperar(i);
					if(v.getFecha().equals(fecha)) {
						System.out.println(auxVuelo.toString());	//Info del vuelo.
						System.out.println(v.toString());//Informacion del Viaje del Vuelo.
						exito = false;		
					}
				}
				if(exito)
					System.out.println("No existe un Vuelo con esa fecha.");
			}
			else
				System.out.println("No hay viajes asociados a este Vuelo.");
		}
		else
			System.out.println("Vuelo no existe.");
	}

	public static void listarRangoVuelos(Diccionario vuelos) {

		System.out.println("Introduzca codigo Vuelo numero 1");
		String codigo1 = TecladoIn.readLine();
		System.out.println("Introduzca codigo Vuelo numero 2");
		String codigo2 = TecladoIn.readLine();
		Lista aux = vuelos.listarRango(codigo1, codigo2);
		System.out.println(aux.toString());
	}

	public static boolean maximoXVuelos(Grafo aereopuerto) {

		boolean exito = false;
		System.out.println("Introduzca Aereopuerto A: ");
		String a = TecladoIn.readLine();
		System.out.println("Introduzca Aereopuerto B: ");
		String b = TecladoIn.readLine();
		Aereopuerto origen = new Aereopuerto(a);
		Aereopuerto destino = new Aereopuerto(b);
		if(aereopuerto.existeVertice(origen) && aereopuerto.existeVertice(destino)) {
			System.out.println("Introduzca cantidad maxima de vuelos: ");
			int max = TecladoIn.readLineInt();
			int longitud = (aereopuerto.caminoMasCortoSegunLimite(origen, destino, max).longitud()) - 1;	//Resto uno ya que me devuelve la Lista con el aereopuerto Origen.
			System.out.println(aereopuerto.caminoMasCortoSegunLimite(origen, destino,max).toString());
			if(longitud != -1 && longitud <= max)
				exito = true;
		}
		else
			System.out.println("No existen los aereopuertos");

		return exito;
	}
	
	public static Lista menorTiempo(Grafo aereopuerto) {

		System.out.println("Introduzca Aereopuerto A: ");
		String a = TecladoIn.readLine();
		System.out.println("Introduzca Aereopuerto B: ");
		String b = TecladoIn.readLine();
		Aereopuerto origen = new Aereopuerto(a);
		Aereopuerto destino = new Aereopuerto(b);
		Lista ls = null;
		if(aereopuerto.existeVertice(origen) && aereopuerto.existeVertice(destino)) {
			ls = (aereopuerto.caminoCortoSegunEtiqueta(origen, destino));
			System.out.println(aereopuerto.caminoCortoSegunEtiqueta(origen, destino).toString());
		}
		else
			System.out.println("No existen los aereopuertos");

		return ls;
	}
	
	public static Lista promocionClientes(Diccionario clientes,HashMap pasajes) {

		HeapEspecial min = new HeapEspecial();
		Lista ls = new Lista();
		Tupla auxT;	// Clase que guarda ClaveCliente y su cantidad de pasajes adquiridos.
		int x;
		for (Object key : pasajes.keySet()) {	// Por CADA ClaveCliente del HashMap obtengo su Value (Su lista de pasajes)
			x = ((Lista)pasajes.get(key)).longitud();	// Y obtengo la cantidad de pasajes que tiene con lista.longitud()
			min.insertar(new Tupla((ClaveCliente)key,x));	// Inserto en el heap la Tupla, (ClaveCliente,cantPasajes).
		}
		while(!min.esVacio()) {	//Voy vaciando el HEAP
			auxT = (Tupla) min.recuperarCima();	//Recupero la CIMA la cual está el Cliente con la menor cantidad de Pasajes.
			ls.insertar(auxT.getCliente().toString()+" Cantidad de pasajes: "+auxT.getCantPasajes()+"\n", 1);	//INSERTO siempre en 1, así se va ordenando de Mayor a Menor.
			min.eliminarCima();
		}
		return ls;
	}

	public static Pasaje comprarPasajeAux(String codigo, Lista viajes) {
		//Metodo auxiliar que verifica que sea posible comprar un pasaje, que exista el Vuelo con la fecha, que hayan asientos disponibles y que no este ocupado el numero que elige el Cliente.
		System.out.println("A continuacion se le mostrara las fechas del vuelo: \n");
		System.out.println(viajes.toString());
		System.out.println("Introduzca fecha del Vuelo(DDMMAA):");
		String fecha = TecladoIn.readLine();
		Viaje auxV = null;
		boolean exito = true;
		for(int i = 1; i <= viajes.longitud() && exito; i++) {
			auxV = (Viaje) viajes.recuperar(i);
			if(fecha.equals(auxV.getFecha()))	//Recorro la Lista de viajes hasta encontrar la que tenga la fecha indicada.
				exito = false;
		}
		Pasaje pas = null;
		if(!exito && (auxV.getCantVendidos() < auxV.getCantTotales())) {	//Si se encontro el Viaje y hay minimo 1 asiento disponible.
			System.out.println("Introduzca un numero de asiento: ");
			int num = TecladoIn.readLineInt();
			ArbolAVL avl = auxV.getAsientos();
			if(!avl.pertenece(num)) {	//Si no existe el asiento, entonces esta disponible.
				pas = new Pasaje(codigo,num,"pendiente",fecha);
				avl.insertar(num);	//Inserto en el AVL de asientos, el nuevo asiento.
				auxV.setCantVendidos(avl.getCantAsientos());//Actualizo cant de asientos vendidos (Variable global).
			}	
		}
		return pas;
	}

	public static ClaveCliente verificarCliente(Diccionario clientes) {
		//Verifica si dado un DNI y tipo DNI existe un cliente en el AVL Clientes. Si existe, retorna la clave del cliente.
		System.out.println("Introduzca DNI del cliente");
		String dni = TecladoIn.readLine();
		System.out.println("Introduzca Tipo de DNI del cliente");
		String tipo = TecladoIn.readLine();
		ClaveCliente aux = new ClaveCliente(dni,tipo);
		if(clientes.existeClave(aux))
			return aux;
		else
			return null;
	}

	private static void estadoCancelado(ClaveCliente c, String codigoVuelo,HashMap pasajes) {

		Pasaje auxPasaje;
		Lista auxLista = (Lista) pasajes.get(c);	// recupero Lista de pasajes de unCliente
		for(int i = 1; i <= auxLista.longitud(); i++) {
			auxPasaje = (Pasaje)auxLista.recuperar(i);	//Voy recuperando los pasajes
			if(auxPasaje.getCodigoVuelo().equals(codigoVuelo))
				auxPasaje.setEstado("cancelado");
		}
		escribirLOG("Los pasajes del VUELO: "+codigoVuelo+" del Cliente: "+c+" han sido cancelados por eliminacion del VUELO");
	}

	private static void eliminarVueloAux(Vuelo vuelo,Grafo aereopuertos,HashMap pasajes) {
		String codigo = vuelo.getClave();
		Aereopuerto a1 = new Aereopuerto(vuelo.getOrigen());//Creo los Aereopuertos auxiliares para ubicarlos en el grafo y eliminarlos.
		Aereopuerto a2 = new Aereopuerto(vuelo.getDestino());	//siempre van a existir los Aereopuertos ya que el Vuelo existe.
		a1 = (Aereopuerto)aereopuertos.ubicarElemento(a1);
		a2 = (Aereopuerto)aereopuertos.ubicarElemento(a2);
		int minutos = calcularMinutos(vuelo.getHoraSalida(),vuelo.getHoraLlegada());
		aereopuertos.eliminarArco(a1, a2,minutos);	//Elimino el arco del grafo de Aereopuertos.
		escribirLOG("Se ha eliminado el VUELO: "+codigo);
		for (Object key : pasajes.keySet())	//Por cada key (ClaveCliente) del keyset.
			estadoCancelado((ClaveCliente) key,codigo,pasajes);
	}

	public static void estadoSistema(Grafo a, Diccionario c, Diccionario v, HashMap p) {
		System.out.println(c.listarDatos());
		System.out.println(a.toString());
		System.out.println(v.toString()+"\n");
		System.out.println(p.toString());
	}

	public static void escribirLOG(String dato) {
		Logger x = Logger.getLogger("MyLog");
		x.info(dato);	//escribe en el log
	}

	public static int calcularMinutos(int salida, int llegada) {

		//0330
		int resultado;
		if(salida > llegada)
			resultado = 1440-((salida-llegada)*60);
		else
			resultado = (llegada-salida) * 60;
		return resultado;
	}

}



