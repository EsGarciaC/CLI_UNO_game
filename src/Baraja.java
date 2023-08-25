import java.util.Random;
import java.util.Deque;
import java.util.LinkedList;

public class Baraja {

    private String[] colores = {"amarillo", "azul", "rojo", "verde"};
    private String[] simbolos = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "ST", "IR", "+2"} ;
    private Deque<Carta> ColaDeArrastre = new LinkedList<Carta>();
    private Deque<Carta> PilaJugadas = new LinkedList<Carta>();
    private Carta[] cartas = new Carta[96];

    public Baraja(){
        int cont = 0;
        for (String color: colores){
            for(int i = 0; i<2; i++){
                for (String simbolo: simbolos){
                    Carta carta1 = new Carta(color, simbolo);
                    cartas[cont] = carta1;
                    cont++;
                }
            }
            
        }
        Random rand = new Random();
        for (int i = 0; i < cartas.length; i++) {
            int index = rand.nextInt(cartas.length - i);
            Carta tmp = cartas[cartas.length - 1 - i];
            cartas[cartas.length - 1 - i] = cartas[index];
            cartas[index] = tmp;}
        
        for (Carta carta:cartas){
            this.ColaDeArrastre.push(carta);

        }

        }
        


    public Carta[] getCartas() {
        return this.cartas;
    }

    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }


    public Deque<Carta> getColaDeArrastre() {
        return this.ColaDeArrastre;
    }


    public Deque<Carta> getPilaJugadas() {
        return this.PilaJugadas;
    }


    public void setColaDeArrastre(Deque<Carta> colaDeArrastre) {
        this.ColaDeArrastre = colaDeArrastre;
    }


    public void setPilaJugadas(Deque<Carta> pilaJugadas) {
        this.PilaJugadas = pilaJugadas;
    }
    
    public static void shuffle(Baraja baraja){

        Carta cartaJugada = baraja.getPilaJugadas().removeFirst();

        System.out.println("Coge " + baraja.getPilaJugadas().size() + " Cartas de pilaJugadas haciendo shuffle menos la primera.");
        //agrega todas las cartas de la pila de jugadas en la cola de arrastre
        if (baraja.getPilaJugadas().size()> 0){
            int tamano = baraja.getPilaJugadas().size();
            for (int k = 0; k< tamano; k++){
                baraja.getColaDeArrastre().addFirst(baraja.getPilaJugadas().removeFirst());
            }
        }
        System.out.println("Coge " + baraja.getColaDeArrastre().size() + " Cartas de cola de arrastre luego de coger coger todas las de pilaJugadas menos una");
        Carta[] shuffleArray = new Carta[baraja.getColaDeArrastre().size()];
        //Vuelve a poner la ultima carta jugada en la pila de jugadas para continuar
        baraja.getPilaJugadas().addFirst(cartaJugada);

        //Pone todas las cartas en cola de arrastre en un array para luego revolverlo
        if (baraja.getColaDeArrastre().size()> 0){
            int tamano = baraja.getColaDeArrastre().size();
            for (int j = 0; j< tamano; j++){
                shuffleArray[j] = baraja.getColaDeArrastre().removeFirst();
            }
        }
        
        //Revuelve el array
        Random rand = new Random();
        for (int i = 0; i < shuffleArray.length; ++i) {
            int index = rand.nextInt(shuffleArray.length - i);
            Carta tmp = shuffleArray[shuffleArray.length - 1 - i];
            shuffleArray[shuffleArray.length - 1 - i] = shuffleArray[index];
            shuffleArray[index] = tmp;
        }
        
        
        //Devuelve todas las cartas en shufflearray a la cola de arrastre
        int contador = 0;
        for (Carta carta:shuffleArray){
            baraja.getColaDeArrastre().addFirst(carta);
            contador++;

        }
        System.out.println("Pone en la cola de arrastre " + contador + " Cartas para arrastrar");

    }

}

    
