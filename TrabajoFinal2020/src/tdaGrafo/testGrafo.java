package tdaGrafo;

public class testGrafo {

	public static void main(String[] args) {
		
		
		Grafo x = new Grafo();
		

		x.insertarVertice('A');
		x.insertarVertice('B');
		x.insertarVertice('C');
		x.insertarVertice('T');
		x.insertarVertice('E');
		System.out.println(x.listarEnProfunidad());
		System.out.println(x.insertarArco('E', 'A',2));
		System.out.println(x.insertarArco('E', 'A',2));

	}

}
