/* Invariante de Representacion:
 * 
 * mesas_presi tiene el tamaño de nombresPar.
 * 
 * mesas_distrito es una matriz donde en las columnas tiene la longitud de mesasDe y en las filas tiene la longitud de nombrePar.
 * 
 * dhond es un heap([tuplas]) que tiene tamaño de banca. Para cualquier posicion del heap el primer valor de la tupla va a ir entre 0 y el tamaño de banca,
 * el segundo valor si o si tiene que ser un valor de mesasDistritos.
 * 
 * banca tiene el tamaño de nombresPar - 1.
 * 
 * votosMasVotados es el maximo valor para determinado distrito en mesas distrito.
 * 
 * masVotado es la posicion de votosMasVotados.
 * 
 * votosSegundoMasVotado es el segundo maximo valor par determinado distrito en mesasDistrito y es si o si menor a votosDelMasVotado
 * 
 * votosTotalesPresidente es la suma de todas las posiciones de mesas_presi
 * 
 * result es una matriz donde las columnas tienen la longitud de mesasD y en las filas de longitud  de nombresPar 
 * 
 * distritoAnterior tiene valores posibles entre -1 y longitud de bancas
 * 
 * array de heaps tiene el mismo tamaño que diputados y para cualquier posicion de dhoundt
 * 
 * nuevosDistritos tiene el tamaño de mesas_distrito para determinado distrito es decir la longitud  de nombresPar
 */

package aed;

public class SistemaCNE {
    private String[] distritos;
    private int[] diputados;
    private String[] nombresPar;
    private int[] mesasD;
    private Arreglo mesas_presi;
    private Matriz mesas_distrito;
    private Heap dhoundt;
    private int[] banca;
    private int votosMasVotado;
    private int masVotado;
    private int votosSegundoMasVotado;
    private int votosTotalesPresidente;
    private Matriz result;
    private int distritoAnterior;
    private int votosTotalesDistrito;
    private Heap[] arrayHeaps;
    private int[] NuevoDistritos;
    private int[] listaUmbral;

    public class VotosPartido {
        private int presidente;
        private int diputados;

        VotosPartido(int presidente, int diputados) {
            this.presidente = presidente;
            this.diputados = diputados;
        }

        public int votosPresidente() {
            return presidente;
        }

        public int votosDiputados() {
            return diputados;
        }
    }

    public SistemaCNE(String[] nombresDistritos, int[] diputadosPorDistrito, String[] nombresPartidos,
            int[] ultimasMesasDistritos) {
        this.nombresPar = nombresPartidos;
        this.distritos = nombresDistritos;
        this.diputados = diputadosPorDistrito;
        this.mesasD = ultimasMesasDistritos;
        this.mesas_presi = new Arreglo(nombresPartidos.length);
        this.mesas_distrito = new Matriz(ultimasMesasDistritos.length, nombresPartidos.length);
        this.banca = new int[nombresPartidos.length - 1];
        masVotado = -1;
        votosMasVotado = 0;
        votosSegundoMasVotado = 0;
        this.votosTotalesPresidente = 0;
        this.arrayHeaps = new Heap[diputados.length];
        this.dhoundt = new Heap(banca);
        this.distritoAnterior = -1;
        this.result = new Matriz(diputados.length, banca.length);
        this.listaUmbral = new int[diputadosPorDistrito.length];

    }

    // complejidad O(1) porque es un array donde le paso una posicion y me devuelve
    // el valor
    public String nombrePartido(int idPartido) {
        return nombresPar[idPartido];
    }

    // complejidad O(1) porque es un array donde le paso una posicion y me devuelve
    // el valor
    public String nombreDistrito(int idDistrito) {
        return distritos[idDistrito];
    }

    // complejidad O(1) porque es un array donde le paso una posicion y me devuelve
    // el valor
    public int diputadosEnDisputa(int idDistrito) {
        return diputados[idDistrito];
    }

    // complejidad O(log D) porque implementa busqueda binaria que va reduciendo el
    // array por mitades
    public String distritoDeMesa(int idMesa) {
        return distritos[busquedaBinaria(mesasD, idMesa)];
    }

    public int porcentaje(int partido, int total) {
        int res = (partido * 100) / total;// O(1)
        return res;
    }

    public void calcularMasVotado() {
        for (int i = 0; i < nombresPar.length; i++) {
            if (mesas_presi.obtenerElemento(i) >= votosMasVotado) {
                votosMasVotado = mesas_presi.obtenerElemento(i);
                masVotado = i;
            }
        }
    }

    public void calcularSegundoMasVotado() {
        for (int i = 0; i < nombresPar.length; i++) {
            if (i != masVotado) {
                if (mesas_presi.obtenerElemento(i) >= votosSegundoMasVotado) {
                    votosSegundoMasVotado = mesas_presi.obtenerElemento(i);
                }
            }
        }
    }

    // complejidad (p + log(D));
    public void registrarMesa(int idMesa, VotosPartido[] actaMesa) {

        for (int i = 0; i < nombresPar.length; i++) { // recorre la cantidad de partidos politicos entonces es igual
                                                      // O(P)
            mesas_presi.asignarElemento(i, actaMesa[i].votosPresidente()); // O(1) porque es una asignacion
            mesas_distrito.asignarElemento(busquedaBinaria(mesasD, idMesa), i, actaMesa[i].votosDiputados()); // O(1)
                                                                                                              // por que
                                                                                                              // es
                                                                                                              // asignacion
            votosTotalesPresidente += actaMesa[i].votosPresidente(); // O(1) por que es asignacion
            votosTotalesDistrito += actaMesa[i].votosDiputados(); // O(1) por que es asignacion

        }
        calcularMasVotado(); // recorre todos los partidos politicos hasta encontrar el mayor valor por eso
                             // O(P)
        calcularSegundoMasVotado(); // recorre todos los partidos politicos hasta encontrar el segundo mayor valor
                                    // por eso O(P)
        dhoundt = new Heap(banca); // crear un heap tiene complejidad O(P) porque depende de los partidos
        dhoundt = CrearHeap(busquedaBinaria(mesasD, idMesa)); // esta funcion crearHeap tiene log(D)
        arrayHeaps[busquedaBinaria(mesasD, idMesa)] = dhoundt; // asignacion O(1)

    }

    public int votosPresidenciales(int idPartido) {

        return mesas_presi.obtenerElemento(idPartido);
    }

    public int votosDiputados(int idPartido, int idDistrito) {
        return mesas_distrito.obtenerElemento(idDistrito, idPartido);
    }

    private Heap CrearHeap(int idDistrito) {
        NuevoDistritos = Umbral(mesas_distrito.obtenerLista(idDistrito), listaUmbral, votosTotalesDistrito);
        dhoundt.crearHeap(NuevoDistritos);
        dhoundt.maxHeap();
        return dhoundt;
    }

    private int[] Umbral(int[] lista, int[] listaUmbral, int valor) {
        for (int i = 0; i < lista.length; i++) {
            if (porcentaje(lista[i], valor) <= 3) {
                listaUmbral[i] = 0;
            } else {
                listaUmbral[i] = lista[i];
            }

        }
        return listaUmbral;
    }

    // tiene complejidad O(D_d * log(P)) porque:
    public int[] resultadosDiputados(int idDistrito) {
        if (distritoAnterior == (idDistrito)) { // O(1) porque es una comparacion y luego una asignacion
            return result.obtenerLista(idDistrito);
        }

        for (int i = 0; i < diputados[idDistrito]; i++) { // tiene complejidad O(D_d porque recorre todos los distritos)
            int division = arrayHeaps[idDistrito].MaximoDevuelto(); // O(1) por que es asignacion
            result.obtenerLista(idDistrito)[division] += 1; // O(1) operacion elemental
            arrayHeaps[idDistrito].Agregar(mesas_distrito.obtenerElemento(idDistrito, division) // O(log P) porque es lo
                                                                                                // que cuesta agregar en
                                                                                                // un heap
                    / (result.obtenerLista(idDistrito)[division] + 1));
        }
        distritoAnterior = idDistrito;
        return result.obtenerLista(idDistrito);
    }

    // O(1) porque solo realiza comparaciones logicas y operaciones aritmeticas
    public boolean hayBallotage() {
        {
            int p1 = porcentaje(votosMasVotado, votosTotalesPresidente);
            if (p1 >= 45) {
                return false;
            }
            if (p1 >= 40) {
                int p2 = porcentaje(votosSegundoMasVotado, votosTotalesPresidente);
                if (p1 - p2 > 10) {
                    return false;
                }
            }
            return true;
        }
    }

    public static int busquedaBinaria(int[] array, int elemento) {
        int inferior = 0;
        int superior = array.length - 1;

        while (inferior <= superior) {
            int centro = (inferior + superior) / 2;

            if (array[centro] == elemento) {
                return centro + 1;
            } else if (elemento < array[centro]) {
                superior = centro - 1;
            } else {
                inferior = centro + 1;
            }
        }
        return inferior;
    }
}
