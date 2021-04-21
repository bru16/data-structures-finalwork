package tdaGrafo;

import lineales.dinamicas.Lista;

public class Grafo {

	private NodoVert inicio;
	
	
	//Metodos propios del TDA Grafo
	
	
	public boolean insertarVertice(Object nuevoVertice) {
		
		boolean exito = false;
		NodoVert aux = ubicarVertice(nuevoVertice);

		if(aux == null) {
			this.inicio = new NodoVert(nuevoVertice, this.inicio);
			exito = true;
		}

		return exito;
	}

	public boolean eliminarVertice(Object vert) {

		boolean exito = false;

		NodoVert auxVert = this.inicio;
		NodoVert auxiliar = this.inicio;

		while(auxVert != null && !auxVert.getElem().equals(vert)) {

			if(!auxVert.getElem().equals(vert)) {
				auxiliar = auxVert;
				auxVert = auxVert.getSigVertice();
			}
		}
		if(auxVert != null) {
			//si el vertice existe
			exito = true;
			if(auxVert.equals(this.inicio) && auxVert.getSigVertice() != null) {
				this.inicio = this.inicio.getSigVertice();
				eliminarAux(auxVert,vert);
				auxVert = null;	//null para el garbage collector.
				//Se puede mejorar esta parte junto al ultimo else.
			}
			else if(auxVert.equals(this.inicio) && this.inicio.getSigVertice() == null) {
				this.inicio = null;	//caso especial
			}
			else {
				//caso si no es el inicio el vertice.
				auxiliar.setSigVertice(auxVert.getSigVertice());
				eliminarAux(auxVert,vert);
				auxVert = null;	//null para el garbage collector;
			}
		}
		return exito;
	}
	
	private void eliminarAux(NodoVert v,Object vert) {
		
		NodoAdy arco = v.getPrimerAdy();
		while(arco != null) {
			NodoVert aux2 = arco.getVertice();	//Me da el vertice al que referencia
			NodoAdy arcoVertAdy = aux2.getPrimerAdy();
			NodoAdy auxAdy = arcoVertAdy;
			if(arcoVertAdy.getVertice().getElem().equals(vert)) 
				aux2.setPrimerAdy(arcoVertAdy.getSiguienteAdy());//si es el primer adyacente
			else {
				while(arcoVertAdy.getVertice().getElem() != vert) {
					//hasta encontrar el vertice adyacente a eliminar
					auxAdy = arcoVertAdy;
					arcoVertAdy = arcoVertAdy.getSiguienteAdy();
				}
				auxAdy.setSiguienteAdy(arcoVertAdy.getSiguienteAdy());
			}
			arco = arco.getSiguienteAdy();	//voy pasando por toda la lista de adyacentes del vertice que quiero eliminar para sacar los arcos.
		}

	}


	public boolean existeVertice(Object vert) {

		NodoVert aux = this.inicio;
		boolean exito = false;

		while(aux != null && !aux.getElem().equals(vert)) {
			aux = aux.getSigVertice();
		}
		if(aux != null)
			exito = true;
		
		return exito;
	}

	public NodoVert ubicarVertice(Object buscado) {

		NodoVert aux = this.inicio;

		while(aux != null && !aux.getElem().equals(buscado)) {
			aux = aux.getSigVertice();
		}

		return aux;
	}

	public boolean insertarArco(Object origen, Object destino, int etiqueta) {

		boolean exito = false;
		NodoVert auxO = null;
		NodoVert auxD = null;
		NodoVert aux = this.inicio;

		while((auxO == null || auxD == null) && aux != null) {
			if(aux.getElem().equals(origen)) auxO = aux;
			if(aux.getElem().equals(destino)) auxD = aux;
			aux = aux.getSigVertice();
		}

		if(auxO != null && auxD != null) {
			//si ambos vertices existen inserto el arco.
			NodoAdy arco = auxO.getPrimerAdy();
			NodoAdy arco2 = auxD.getPrimerAdy();

			if(arco != null) {
				//Existe al menos un Nodo Adyacente en Vertice ORIGEN y recorro su lista de adyacentes para insertar el nuevo nodo
				while(arco.getSiguienteAdy() != null) {
					arco = arco.getSiguienteAdy();	
				}
				arco.setSiguienteAdy(new NodoAdy(auxD, etiqueta));
			}
			else {
				//Como la lista de adyacentes del Vertice Origen esta vacia, inserto el primer nodo.
				auxO.setPrimerAdy(new NodoAdy(auxD, etiqueta));
			}
			if(arco2 != null) {
				//Existe al menos un Nodo Adyacente en Vertice DESTINO y recorro su lista de adyacentes para insertar el nuevo nodo
				while(arco2.getSiguienteAdy() != null) {
					arco2 = arco2.getSiguienteAdy();
				}
				arco2.setSiguienteAdy(new NodoAdy(auxO, etiqueta));
			}
			else {
				//Como la lista de adyacentes del Vertice Destino esta vacia, inserto el primer nodo.
				auxD.setPrimerAdy(new NodoAdy(auxO, etiqueta));
			}
			exito = true;
		}

		return exito;
	}

	public boolean eliminarArco(Object e, Object e2, int etiqueta) {
		boolean res = false;
		NodoVert origen = this.ubicarVertice(e);
		NodoVert destino = this.ubicarVertice(e2);
		if (origen != null && destino != null) {
			res = true;
			NodoAdy a = origen.getPrimerAdy();
			if (a.getEtiqueta() == etiqueta && a.getVertice().getElem().equals(e2)) {
				//Caso el primer adyacente es el que estoy buscando
				eliminarArcoAux(destino, e, etiqueta);	// Elimino el arco de vuelta
				origen.setPrimerAdy(a.getSiguienteAdy());// elimino el arco de ida
			} else {
				while (a.getSiguienteAdy() != null) {
					//Voy recorriendo los adyacentes hasta encontrar el que busco.
					if ((a.getSiguienteAdy().getEtiqueta() == etiqueta) && (a.getSiguienteAdy().getVertice().getElem().equals(e2))) {
						eliminarArcoAux(destino, e, etiqueta);
						a.setSiguienteAdy(a.getSiguienteAdy().getSiguienteAdy());	// Ver variable booleana? para que corte el while
					} else 	
						a = a.getSiguienteAdy();

				}
			}
		}
		return res;
	}

	private void eliminarArcoAux(NodoVert destino, Object e, int etiqueta) {
		NodoAdy b = destino.getPrimerAdy();
		if (b.getEtiqueta() == etiqueta && b.getVertice().getElem().equals(e)) 	// caso primer adyacente
			destino.setPrimerAdy(b.getSiguienteAdy());
		else {
			while (b.getSiguienteAdy() != null) {	//recorro los nodos hasta encontrar.
				if ((b.getSiguienteAdy().getEtiqueta() == etiqueta) && (b.getSiguienteAdy().getVertice().getElem().equals(e))) 
					b.setSiguienteAdy(b.getSiguienteAdy().getSiguienteAdy());
				else
					b = b.getSiguienteAdy();
			}
		}
	}

	public Lista listarEnProfunidad() {

		Lista visitados = new Lista();
		NodoVert aux = this.inicio;

		while(aux != null) {

			if(visitados.localizar(aux.getElem()) < 0)
				listarEnProfundidadAux(aux,visitados);

			aux = aux.getSigVertice();
		}
		return visitados;
	}

	private void listarEnProfundidadAux(NodoVert n, Lista vis) {

		if(n != null) {
			//marca al vertice n como visitado.
			vis.insertar(n.getElem(), vis.longitud() + 1);
			NodoAdy ady = n.getPrimerAdy();
			while(ady != null) {
				//visita en profundidad los adyacentes de n aun no visitados.
				if(vis.localizar(ady.getVertice().getElem()) < 0)
					listarEnProfundidadAux(ady.getVertice(),vis);

				ady = ady.getSiguienteAdy();
			}
		}
	}

	public boolean esVacio() {

		if(this.inicio != null)
			return false;
		else
			return true;
		
	}

	public boolean existeCamino(Object origen, Object destino) {

		boolean exito = false;
		//verifica si ambos vertices existen

		NodoVert auxO = null;
		NodoVert auxD = null;
		NodoVert aux = this.inicio;

		while((auxO == null || auxD == null) && aux != null) {
			if(aux.getElem().equals(origen)) auxO = aux;
			if(aux.getElem().equals(destino)) auxD = aux;
			aux = aux.getSigVertice();
		}

		if(auxO != null && auxD != null) {
			//si ambos vertices existen
			Lista visitados = new Lista();
			exito = existeCaminoAux(auxO, destino, visitados);
		}

		return exito;
	}

	private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis) {

		boolean exito = false;
		if (n != null) {
			//si vertice n es el destino hay camino.
			if(n.getElem().equals(dest)) {
				exito = true;
			}
			else {
				// si no es el destino verifica si hay camino entre n y destino.
				vis.insertar(n.getElem(),vis.longitud() + 1);
				NodoAdy ady = n.getPrimerAdy();
				while(!exito && ady != null) {
					if(vis.localizar(ady.getVertice().getElem()) < 0)
						exito = existeCaminoAux(ady.getVertice(),dest,vis);
					
					ady = ady.getSiguienteAdy();

				}
			}

		}
		return exito;

	}

	public Lista caminoMasCorto(Object origen, Object destino){
		Lista masCorto = new Lista();
		NodoVert verticeOr = ubicarVertice(origen);
		if(verticeOr!=null){
			Lista masCortoActual = new Lista();
			masCorto = caminoMasCortoAux(verticeOr,destino,masCorto,masCortoActual);
		}
		return masCorto;
	}

	private Lista caminoMasCortoAux(NodoVert n,Object destino,Lista masCorto,Lista masCortoActual){
		if(n != null){
			masCortoActual.insertar(n.getElem(),masCortoActual.longitud()+1);
			if(n.getElem().equals(destino)){
				if(masCorto.esVacia()){//condicion para guardar el camino mas corto caso especial (la primera vez)
					masCorto=masCortoActual.clone();
				}else if(masCorto.longitud() > masCortoActual.longitud()){//condicion para guardar el camino mas corto
					masCorto.vaciar();
					masCorto=masCortoActual.clone();   
				}
			}else{
				NodoAdy ady = n.getPrimerAdy();
				while(ady != null){
					if(masCortoActual.localizar(ady.getVertice().getElem()) < 0)	// si lo encuentra es porque ya se paso por ese nodo y se obtiene el siguiente adyacente.
						masCorto = caminoMasCortoAux(ady.getVertice(), destino,masCorto,masCortoActual);

					ady=ady.getSiguienteAdy();
				}
			}
			masCortoActual.eliminar(masCortoActual.longitud());	// se elimina el ultimo ya que por ese camino no se encontro el nodo buscado.
		}
		return masCorto;
	}

	public Lista caminoMasCortoEtiqueta(Object origen, Object destino) {

		Lista masCorto = new Lista();
		NodoVert verticeOrigen = ubicarVertice(origen);
		if(verticeOrigen != null) {
			Lista masCortoActual = new Lista();
			masCorto = caminoMasCortoEtiquetaAux(verticeOrigen,destino,masCorto,masCortoActual);
		}
		return masCorto;
	}
	
	private Lista caminoMasCortoEtiquetaAux(NodoVert n,Object destino,Lista masCorto,Lista masCortoActual){
		if(n!=null){
			//System.out.println(masCorto);
			masCortoActual.insertar(n.getElem(),masCortoActual.longitud()+1);
			if(n.getElem().equals(destino)){
				if(masCorto.esVacia()){//condicion para guardar el camino mas corto caso especial (la primera vez)
					masCorto=masCortoActual.clone();
				}else if(masCorto.longitud() > masCortoActual.longitud()  ){//condicion para guardar el camino mas corto
					masCorto.vaciar();
					masCorto=masCortoActual.clone();   
				}
			}else{
				NodoAdy ady=n.getPrimerAdy();
				while(ady!=null){
					if(masCortoActual.localizar(ady.getVertice().getElem())<0)
						masCorto = caminoMasCortoEtiquetaAux(ady.getVertice(), destino,masCorto,masCortoActual);

					ady=ady.getSiguienteAdy();
				}
			}
			masCortoActual.eliminar(masCortoActual.longitud());
		}
		return masCorto;
	}


	public Lista caminoMasCortoSegunLimite(Object origen, Object destino,int max){
		Lista masCorto = new Lista();
		NodoVert verticeOr = ubicarVertice(origen);
		if(verticeOr!=null){
			Lista masCortoActual = new Lista();
			masCorto=caminoMasCortoSegunLimiteAux(verticeOr,destino,masCorto,masCortoActual,max);
		}
		return masCorto;
	}

	private Lista caminoMasCortoSegunLimiteAux(NodoVert n,Object destino,Lista masCorto,Lista masCortoActual,int max){
		if(n != null && masCortoActual.longitud() <= max){
			masCortoActual.insertar(n.getElem(),masCortoActual.longitud()+1);
			if(n.getElem().equals(destino)){
				if(masCorto.esVacia()){//guardar el camino mas corto caso especial (primera vez)
					masCorto = masCortoActual.clone();
				}else if(masCorto.longitud() > masCortoActual.longitud() ){//condicion para guardar el camino mas corto
					masCorto.vaciar();
					masCorto = masCortoActual.clone();
				}
			}else{
				NodoAdy ady=n.getPrimerAdy();
				while(ady != null){
					if(masCortoActual.localizar(ady.getVertice().getElem()) < 0)
						masCorto = caminoMasCortoSegunLimiteAux(ady.getVertice(), destino,masCorto,masCortoActual,max);

					ady=ady.getSiguienteAdy();
				}
			}
			masCortoActual.eliminar(masCortoActual.longitud());
		}
		return masCorto;
	}

	public Lista caminoMasLargo(Object origen, Object destino){
		Lista masLargo=new Lista();
		NodoVert verticeOr=ubicarVertice(origen);
		if(verticeOr!=null){
			Lista visitados=new Lista();
			Lista masLargoActual=new Lista();
			masLargo=caminoMasLargoAux(verticeOr,destino,visitados,masLargo,masLargoActual);
		}
		return masLargo;
	}
	public Lista caminoMasLargoAux(NodoVert n,Object destino,Lista vis,Lista masLargo,Lista masLargoActual){
		if(n!=null){
			vis.insertar(n.getElem(), vis.longitud()+1);
			masLargoActual.insertar(n.getElem(),masLargoActual.longitud()+1);
			if(n.getElem().equals(destino)){
				if(masLargo.longitud()< masLargoActual.longitud()){
					masLargo.vaciar();
					masLargo=masLargoActual.clone();
				}
			}else{
				NodoAdy ady=n.getPrimerAdy();
				while(ady!=null){
					if(vis.localizar(ady.getVertice().getElem())<0){
						masLargo=caminoMasLargoAux(ady.getVertice(), destino, vis,masLargo,masLargoActual);
					}
					ady=ady.getSiguienteAdy();
				}
			}
			masLargoActual.eliminar(masLargoActual.longitud());
			vis.eliminar(vis.longitud());
		}
		return masLargo;
	}

	public Lista caminoCortoSegunEtiqueta(Object origen, Object destino) {
		Lista menorPeso = new Lista();
		NodoVert verticeOr = this.ubicarVertice(origen);
    	if (verticeOr != null) {
    		Lista masLargoActual = new Lista();
    		Lista minutos = new Lista();
    		menorPeso = new Lista();
    		menorPeso = cortoAux(verticeOr, destino, new NodoSuma(0), new NodoSuma(0), menorPeso, masLargoActual,minutos);
    		String s = "Minutos: ";
    		menorPeso.insertar(s+minutos.recuperar(1) + " ", 1);
    	}
    	return menorPeso;
	}

	private Lista cortoAux(NodoVert n, Object destino, NodoSuma sumaMenor, NodoSuma sumaActual, Lista menor, Lista actual,Lista minutos) {
		if (n != null) {
			actual.insertar(n.getElem(), actual.longitud() + 1);
			if (n.getElem().equals(destino)) {
				if (sumaMenor.getValor() == 0) {
					sumaMenor.setValor(sumaActual.getValor());
					menor = actual.clone();
				} else {
					if (sumaActual.getValor() <= sumaMenor.getValor()) {
						sumaMenor.setValor(sumaActual.getValor());
						menor = actual.clone();
					}
				}
			} else {
				NodoAdy aux = n.getPrimerAdy();
				while (aux != null && ((sumaActual.getValor() < sumaMenor.getValor()) || sumaMenor.getValor() == 0)) {
					if (actual.localizar(aux.getVertice().getElem()) == -1) {
						sumaActual.setValor(sumaActual.getValor()+(int) aux.getEtiqueta()); 
						menor = cortoAux(aux.getVertice(), destino, sumaMenor, sumaActual, menor, actual,minutos);
						sumaActual.setValor(sumaActual.getValor()-(int) aux.getEtiqueta());
					}
					aux = aux.getSiguienteAdy();
				}

			}
			actual.eliminar(actual.longitud());
		}	
		minutos.insertar(sumaMenor.getValor(), 1);
		return menor;
	}

	@Override
    public String toString() {

    	String s = "";
    	NodoVert aux = this.inicio;
    	NodoVert aux2;
    	NodoAdy auxAdy;
    	while(aux != null) {
    		s += " AEREOPUERTO: "+aux.getElem().toString();
    		auxAdy = aux.getPrimerAdy();
    		while(auxAdy != null) {
    			aux2 = auxAdy.getVertice();
    			
        		s +=  " \t\t\t\t<-->\t"+auxAdy.getEtiqueta() +"\t";
    			s += aux2.getElem().toString();
    			auxAdy = auxAdy.getSiguienteAdy();
    		}
    		s += "\n\n";
    		aux = aux.getSigVertice();
    	}
    	return s;
	}


	public Object ubicarElemento(Object buscado) {

		Object x = null;
		NodoVert aux = this.inicio;
		while(aux != null && !aux.getElem().equals(buscado)) {
			aux = aux.getSigVertice();
		}
		if(aux != null)
			x = aux.getElem();
		return x;
	}


	public boolean tieneArcos(Object a) {

		NodoVert aux = this.inicio;
    	boolean exito = false;
    	while(aux != null && !aux.getElem().equals(a)) {
			aux = aux.getSigVertice();
		}
    
    	if(aux != null && aux.getPrimerAdy() != null)
    		exito = true;
    	
    	return exito;
    }

    
    
    
    
    
    
    
    
    
    
    
}
