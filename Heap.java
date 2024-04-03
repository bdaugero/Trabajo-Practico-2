/* Invariante de Representacion:
 *
 * Todo elemento del heap que no sea la raiz va a tener un padre que sea mayor.
 * 
 * longitud es el tamaño del Heap.
 * 
 * Mientras lugaresOcupados sea menor o igual a longitud al momento de asignar valores lo va a hacer de 0 a 5 y cuando supere a longitud asigna siempre en la  
 * posicion 0.
 */


package aed;

public class Heap {
    private Tupla [] heap;
    private int longi;
    private int lugaresOcupados=0;

    public Heap(int[] arr) {
        this.longi = arr.length;
        this.heap = new Tupla[arr.length];
    }

    private int parent(int pos) {
        return (pos -1 ) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }


    private void intercambiar(int pos1, int pos2) {
        int tempPrimerElemento = heap[pos1].Primer();
        int tempSegundoElemento = heap[pos1].Segundo();
    
        heap[pos1].definirPrimerElemento(heap[pos2].Primer());
        heap[pos1].definirSegundoElemento(heap[pos2].Segundo());
    
        heap[pos2].definirPrimerElemento(tempPrimerElemento);
        heap[pos2].definirSegundoElemento(tempSegundoElemento);
        
    }
    
    public boolean esHoja(int pos){
        return (pos >= (longi / 2) && pos < longi);
    }
    
    public void crearHeap(int[] pipi ){
        lugaresOcupados=0;
        for (int i = 0; i<longi;i++){
            Agregar(pipi[i]);
        }

    }

    public void maxHeap() {
        for (int i = parent(longi - 1); i >= 0; i--) {
            Bajar(i);
        }
    }
    
    private void Bajar(int posicion) {
        while (!esHoja(posicion)) {
            int hijoMayor = HijoMayor(posicion);
    
            if (hijoMayor != -1 && heap[posicion].Segundo() < heap[hijoMayor].Segundo()) {
                intercambiar(posicion, hijoMayor);
                posicion = hijoMayor;
            } else {
                break;
            }
        }
    }
    
    private int HijoMayor(int posicion) {
        int leftChildIndex = leftChild(posicion);
        int rightChildIndex = rightChild(posicion);
    
        if (longi % 2 == 0) {
            if (leftChildIndex < longi) {
                return heap[leftChildIndex].Segundo() > heap[posicion].Segundo() ? leftChildIndex : -1;
            } else {
                return -1;
            }
        } else {
            if (rightChildIndex < longi) {
                int mayor = heap[leftChildIndex].Segundo() > heap[rightChildIndex].Segundo() ? leftChildIndex : rightChildIndex;
                return heap[mayor].Segundo() > heap[posicion].Segundo() ? mayor : -1;
            } else {
                return -1;
            }
        }
    }
    

    public void Agregar(int elemento) {
        lugaresOcupados +=1;
        if(lugaresOcupados <= longi){
            heap[lugaresOcupados-1]= new Tupla(lugaresOcupados-1,elemento);
            
        
        }
        else{
            heap[0].definirSegundoElemento(elemento);
            maxHeap();
           
    }
}

    public int MaximoDevuelto() {
        
        int maxi = heap[0].Primer();
        return maxi;
    }
    
    
}

