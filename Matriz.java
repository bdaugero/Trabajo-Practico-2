/* Invariante de Representacion:
 * 
 * matriz tamaño nXm.
 * 
 */

package aed;

public class Matriz {

    private int[][] elementos;

    public Matriz(int tamaño, int tamaño2) {
        elementos = new int[tamaño][tamaño2];
    }

    public int obtenerElemento(int indice1, int indice2) {
        return elementos[indice1][indice2];
    }

    public void asignarElemento(int indice, int indice2, int valor) {
        if (indice >= 0 && indice < elementos.length) {
            elementos[indice][indice2] += valor;
        }
    }

    public int[] obtenerLista(int indice1) {
        return elementos[indice1];
    }

}
