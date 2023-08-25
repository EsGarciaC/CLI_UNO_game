import java.util.*;

public class Test {

    public static void testBaraja(Baraja baraja){
        System.out.println(Arrays.toString(baraja.getCartas()));
    }
    public static void testJugador(Baraja baraja){
        Jugador jugador = new Jugador("Jugador 1 (Tu)");
        jugador.arrastrarDe(baraja);
        jugador.arrastrarDe(baraja);
        jugador.arrastrarDe(baraja);
        System.out.println("Imprimiendo 3 cartas: ");
        System.out.println(jugador.getCartas());

        List<Carta> lista = new ArrayList<Carta>(jugador.getCartas());
        //Carta carta = lista.remove(0);

        System.out.println("Sacando +carta+ de jugador");
        jugador.tirarA(0, baraja);
        System.out.println("Imprimiendo 2 cartas: ");
        System.out.println(jugador.getCartas());
        System.out.println("Imprimiendo primera en pila jugadas: ");
        System.out.println(baraja.getPilaJugadas().getFirst());
    }

    public static void testShuffle(Baraja baraja){
        Jugador jugador = new Jugador("Jej");
        jugador.arrastrarDe(baraja);
        jugador.arrastrarDe(baraja);
        jugador.arrastrarDe(baraja);

        List<Carta> lista = new ArrayList<Carta>(jugador.getCartas());
        //Carta carta = lista.remove(0);
        //Carta carta2 = lista.remove(0);
        //Carta carta3 = lista.remove(0);

        jugador.tirarA(0, baraja);
        jugador.tirarA(1, baraja);
        jugador.tirarA(2, baraja);

        Baraja.shuffle(baraja);
    }
    public static void testBaraja2(Baraja baraja){
        int cont = 0;
        for(Carta carta:baraja.getCartas()){
            cont++;
            System.out.println(cont + " " + carta );
        }
        int it = baraja.getColaDeArrastre().size();
        for (int i = 0; i<it; i++){
            System.out.println(baraja.getColaDeArrastre().removeFirst());
        }
    }

    public static void testJugarMaquina(Baraja baraja){
        baraja.getPilaJugadas().addFirst(new Carta("verde", "2"));
        baraja.getPilaJugadas().addFirst(new Carta("amarillo", "3"));
        Maquina jugador2 = new Maquina("Jugador 2 (Maquina)");
        jugador2.getCartas().add(new Carta("rojo", "4"));
        System.out.println("Imprimiendo mano (1 carta):");
        System.out.println(jugador2.cartas); 
        System.out.println("Imprimiendo carta arrastrada");
        System.out.println(jugador2.jugar(baraja));
        System.out.println("Imprimiendo pila jugadas");
        System.out.println(baraja.getPilaJugadas().getFirst());
        System.out.println("Imprimiendo mano (1 carta si pudo jugas, 2 si no):");
        System.out.println(jugador2.cartas); 
        baraja.getPilaJugadas().addFirst(new Carta("rojo", "+2"));
        System.out.println("Imprimiendo pila jugadas");
        System.out.println(baraja.getPilaJugadas().getFirst());
        System.out.println("debería ser false");
        System.out.println(jugador2.jugar(baraja));
        jugador2.getCartas().add(new Carta("amarillo", "+2"));
        System.out.println("debería ser true");
        System.out.println(jugador2.jugar(baraja));
        System.out.println("Imprimiendo pila jugadas (debería ser +2 amarillo)");
        System.out.println(baraja.getPilaJugadas().getFirst());
        System.out.println("Imprimiendo mano:");
        System.out.println(jugador2.cartas); 
    }
/*
    public static void testBaraja(){
            Baraja baraja = new Baraja();
            System.out.println(Arrays.toString(baraja.getCartas()));
    }

    public static void testColaArrastre(){
            ColaArrastre colaArrastre = new ColaArrastre();
            Baraja baraja = new Baraja(); 
            Deque<Carta> dq = new LinkedList<Carta>();
            for (Carta carta : baraja.getCartas()){
                dq.add(carta);
            }
            colaArrastre.setCartasAbajo(dq);
            System.out.println("Carta a girar: " + colaArrastre.getCartasAbajo().getFirst());
            colaArrastre.destapar();
            System.out.println("Carta girada: " + colaArrastre.getCartasArriba().getFirst());
            for (int i=0; i<51; i++){
                colaArrastre.destapar();
            }
            System.out.println("Reiniciando");
            colaArrastre.reiniciar();
            System.out.println("Carta girada anteriormente: " + colaArrastre.getCartasAbajo().getFirst());
            System.out.println("Poniendo 2D y AD en cola de arrastre: ");
            Carta carta1 = new Carta("A", "D");
            Carta carta2 = new Carta("2", "D");
            colaArrastre.getCartasArriba().addFirst(carta2);
            colaArrastre.getCartasArriba().addFirst(carta1);
            System.out.println("Carta en cola: " + colaArrastre);
            Torre torre = new Torre();
            System.out.println("Llevando AD de cola a torre: ");
            colaArrastre.mover(torre);
            System.out.println("Torre: "+torre);
            System.out.println("Carta en cola: " + colaArrastre);
            System.out.println("Llevando 2D de cola a torre: ");
            colaArrastre.mover(torre);
            System.out.println("Torre: "+torre);          
            System.out.println("Carta en cola (deberia ser random): " + colaArrastre);
    */
}

