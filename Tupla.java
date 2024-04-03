/* Invariante de Representacion:
 *
 * tupla donde primerelement y segundoelement pertenecen a los enteros.
 * 
 */


package aed;

public class Tupla {
    private int primerelement;
    private int segundoelement;

    public Tupla (int primerelement , int segundoelement){
        this.primerelement = primerelement;
        this.segundoelement = segundoelement;

    }
    public int Primer(){
        return primerelement;
    }
    public int Segundo(){
        return segundoelement;
    }

    public void definirPrimerElemento (int elemento){
        this.primerelement = elemento;
    }

    public void definirSegundoElemento (int elemento){
       this.segundoelement = elemento;
    }
    
}
