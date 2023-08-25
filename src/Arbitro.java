import java.util.*;

public class Arbitro{

    public static void shuffle(Baraja baraja){

        Carta cartaJugada = baraja.getPilaJugadas().removeFirst();

        //agrega todas las cartas de la pila de jugadas en la cola de arrastre
        if (baraja.getPilaJugadas().size()> 0){
            int tamano = baraja.getPilaJugadas().size();
            for (int k = 0; k< tamano; k++){
                baraja.getColaDeArrastre().addFirst(baraja.getPilaJugadas().removeFirst());
            }
        }

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
        for (Carta carta:shuffleArray){
            baraja.getColaDeArrastre().addFirst(carta);

        }

    }
}
