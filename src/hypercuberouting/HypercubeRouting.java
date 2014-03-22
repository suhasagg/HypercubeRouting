/*
 * Practico de Maquina N° 1
 * Sistemas Distribuidos y Paralelos
 * Licenciatura en Ciencias de la Computación
 * UNSL
 */
package hypercubetopologyrouting;

/**
 *
 * @author Juan Martin Loyola
 */


public class HypercubeTopologyRouting {

    static String[] ayuda = {"*********************************************************************",
                            "---- PROGRAMA PARA RUTEO DE NODOS EN RED CON TOPOLOGIA HYPERCUBO ----",
                            "*********************************************************************",
                            "",
                            "El programa se encarga de mostrar la secuencia de nodos por los que pasa un paquete originado en un nodo fuente para llegar a un nodo destino en una topologia de red de hypercubo.",
                            "",
                            "Para llamar de forma correcta al programa debe suministrarle tres parametros:",
                            "    -> La dimension del hypercubo (un entero mayor que 1).",
                            "    -> El nodo fuente del mensaje (un entero entre 0 y (2^dim)-1).",
                            "    -> El nodo destino del mensaje (un entero entre 0 y (2^dim)-1) (distinto del nodo fuente).",
                            ""};
    
    
    public static void imprimir(String[] informacion){
        for (int i=0; i < informacion.length; i++)
            System.out.println(informacion[i]);
    }
    
    public static void imprimirCamino(int dimension, int nodoFuente, int nodoDestino){
        // Controlamos que los valores para los parametros sean validos
        if (dimension < 1){
            System.out.println("Se ha llamado al programa con una dimension invalida");
            System.out.println("Ejecute el programa con el parametro \"--help\" para obtener mas informacion acerca de como llamar correctamente al programa. ");
        }
        else{
            if (((0 > nodoFuente) || (nodoFuente >= (Math.pow(2,dimension)))) || ((0 > nodoDestino) || (nodoDestino >= (Math.pow(2,dimension))))){
                System.out.println("Se ha llamado al programa con valores de nodos fuente y/o destino incorrecto");
                System.out.println("Ejecute el programa con el parametro \"--help\" para obtener mas informacion acerca de como llamar correctamente al programa. ");
            }
            else{
                // Los parametros ingresados son validos
                int nodoVecinoTemporario = nodoFuente;
                int dimensionActual = dimension;
                
                int xorEntreNodos = nodoFuente ^ nodoDestino; // xorEntreNodos mantiene el resultado de la operacion a nivel de bit XOR entre nodoFuente y nodoDestino
                
                System.out.printf("A continuacion se muestra el camino realizado por un paquete desde el nodo fuente %d al nodo destino %d\n", nodoFuente, nodoDestino);
                
                System.out.printf("[ %d ", nodoFuente);
                
                // Para determinar por cuales nodos viaja el mensaje para ir desde el nodo fuente al nodo destino trabajamos con la representacion binaria de los nodos.
                // Al realizar la operacion a nivel de bit XOR entre el nodo fuente y el nodo destino obtenemos un numero que en su representacion binaria indica los distintos movimientos que debemos hacer.
                // Asi, sea "nm ... n3 n2 n1" el valor de la operacion XOR con m el valor de la dimension del hypercubo, si nm (esto es el digito en la posicion m del resultado) es uno indica que en ese nivel el mensaje debe viajar al vecino en dicha dimension.
                // Si dicho digito es cero entonces en dicha dimension el mensaje no debe viajar a ningun vecino.
                
                // Asi lo que hacemos es iterar sobre las dimensiones, comenzando en la mayor.
                // Si en dicha dimension el resultado del XOR indica que se debe mover a un nodo vecino realizamos una operacion para que el nodo actual se convierta en el nodo vecino destino temporal.
                // Asi hasta llegar al nodo destino (si se llega al destino antes de recorrer todas las dimensiones) o hasta que se recorran todas las dimensiones.
                while (dimensionActual != 0){
                    // Si el nodo aux es igual al nodo destino cortamos el bucle
                    if (nodoVecinoTemporario == nodoDestino){
                        break;
                    }
                    
                    if ((xorEntreNodos & (int)(Math.pow(2, dimensionActual - 1))) != 0){ // Controlamos si en la dimension actual debemos viajar al nodo vecino.
                        // Significa que en esa dimension debo cambiar de nodo al otro nodo de la dimension.
                        
                        if ((nodoVecinoTemporario & (int)(Math.pow(2, dimensionActual - 1))) == 0){
                            nodoVecinoTemporario += Math.pow(2,dimensionActual-1);
                        }
                        else{
                            nodoVecinoTemporario -= Math.pow(2,dimensionActual-1);
                        }
                        
                        System.out.printf("-> %d ", nodoVecinoTemporario);
                    }
                    dimensionActual--;
                    
                }
                System.out.printf("]");
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        switch (args.length){
            case 1:{
                if (args[0].equals("--help")){       
                    imprimir(ayuda);
                }
                else{
                    System.out.println("Se ha llamado al programa de forma incorrecta.");
                    System.out.println("Ejecute el programa con el parametro \"--help\" para obtener mas informacion acerca de como llamar correctamente al programa. ");
                }
                break;
            }
            case 3:{
                int dimension = Integer.parseInt(args[0]);
                int nodoFuente = Integer.parseInt(args[1]);
                int nodoDestino = Integer.parseInt(args[2]);
                
                imprimirCamino(dimension, nodoFuente, nodoDestino);
                
                break;
            }
            default:{
                System.out.println("Se ha llamado al programa con una cantidad de parametros invalida.");
                System.out.println("Ejecute el programa con el parametro \"--help\" para obtener mas informacion acerca de como llamar correctamente al programa. ");
                break;
            }    
            
        }
        

    }
}
