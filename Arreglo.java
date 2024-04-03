/* Invariante de Representacion:
 * 
 * longitud es igual a la cantidad de elementos del arreglo.
 * 
 * Arreglo es una secuencia de enteros.
 * 
 */

package aed;

public class Arreglo {
    private int longitud;
    private int[] elementos;

    public Arreglo(int tamaño) {
        elementos = new int[tamaño];
    }
    public int Longitud(){
        return longitud;
    }

    public int obtenerElemento(int indice) {
        return elementos[indice];
    }

    public void asignarElemento(int indice, int valor) {
        if (indice >= 0 && indice < elementos.length) {
            elementos[indice] += valor;
            longitud +=1;
        } 
    }
    public void reemplacelemento(int indice, int valor){
        if (indice >= 0 && indice < elementos.length) {
            elementos[indice] = valor;
        } 
    }
    public void eliminarElemento(int indice) {
        if (indice >= 0 && indice < elementos.length) {
            elementos[indice] = 0; 
            longitud = longitud-1;
        } 
    }
}
