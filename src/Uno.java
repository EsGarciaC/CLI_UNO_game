import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Thread;

public class Uno {

    public static void main(String[] args) throws Exception {

        Tablero tablero = new Tablero();
        Baraja baraja = tablero.getBaraja();
        Scanner sc = new Scanner(System.in);
        int opcion = 0, acumulador;
        int turnoDe = tablero.getTurno();
        String s;
        String type,a;
        Maquina maquina;
        int jugada;
        int tamanoPilaJugadas = -1;
        boolean mismaCarta = false;

        System.out.println("Bienvenido a la implementación del juego UNO\n");
        System.out.println("Creado por: ");
        System.out.println("Esteban García Carmona");
        System.out.println("Felipe Miranda Arboleda");
        System.out.println("Emilio Porras Mejía\n");

        System.out.println("Para escoger la carta que desee lanzar, contar el número de la carta de abajo hacia arriba y de izquierda a derecha de la siguiente manera:\n");
        System.out.println("5 4 7");
        System.out.println("6 9 +2 1 ST 8 IR\n");
        System.out.println("Corresponden a los números de carta\n");
        System.out.println("8 9 10");
        System.out.println("1 2 3 4 5 6 7\n");
        Thread.sleep(1000);
        System.out.println("Presione Enter Para Continuar");
        
        try {
            System.in.read();
        } catch (InputMismatchException e) {}
        a = sc.nextLine();

        do {
            if(tamanoPilaJugadas == baraja.getPilaJugadas().size()){
                mismaCarta = true;
                
            }
            else{
                tamanoPilaJugadas = baraja.getPilaJugadas().size();
                mismaCarta = false;
            }
            
            turnoDe = tablero.getTurno();
            acumulador = tablero.getAcumuladorMas2();

            if (acumulador > 0) {
                System.out.println("El acumulador de +2 va en: "+acumulador);
                Thread.sleep(1000);
            }

            tablero.checkShuffle();
            
            System.out.println("Es el turno del " + tablero.getJugadores()[turnoDe].getNombre());
            System.out.println(tablero);


            if (turnoDe == 0) {
                turnoDe++;
                System.out.println("Jugador " + turnoDe);
                turnoDe--;
                System.out.println(" Escribe el número de carta que desees jugar.");
                System.out.println(" o 0 para arrastrar.");
                System.out.println("Elija una opcion: ");

                try {
                    opcion = sc.nextInt();
                } catch (InputMismatchException e) {
                    a = sc.nextLine();
                    opcion = sc.nextInt();
                }
                a = sc.nextLine();

                switch (opcion) {

                    case 0:
                        jugada = tablero.getJugador1().jugar(-1, baraja, acumulador);
                        if(acumulador == 0){
                            Humano humano = (Humano) tablero.getJugador1();
                            if(humano.puedeTirar(baraja.getPilaJugadas().peekFirst()) && jugada == 1){
                                System.out.println("Puedes tirar la carta que arrastraste.");
                                jugada = 4;
                            }
                        }
                        if(!mismaCarta || jugada == 4){
                            tablero.checkSaltoDeTurno();
                        }
                        tablero.checkRepetirTurno(jugada);
                        tablero.siguienteTurno();
                        break;

                    default:
                        jugada = tablero.getJugador1().jugar(opcion - 1, baraja, acumulador);

                        if(!mismaCarta || jugada == 1){
                            tablero.cambiarReversa();
                            tablero.checkSaltoDeTurno();
                        }
                        tablero.checkRepetirTurno(jugada);
                        tablero.siguienteTurno();
                        break;
                }

            } else {
                turnoDe++;
                System.out.println("Jugador " + turnoDe);
                turnoDe--;

                maquina = (Maquina) tablero.getJugadores()[turnoDe];
                if (maquina.jugar(baraja)) { // Hace la jugada de la maquina y también devuelve True or false y la hace
                                             // o no.

                    Thread.sleep(1000);
                    turnoDe++;
                    System.out.println("Jugador " + turnoDe + " hizo su jugada");
                    turnoDe--;
                    Thread.sleep(1000);

                    if(!mismaCarta || 
                        baraja.getPilaJugadas().peekFirst().getSimbolo().equals("IR") || 
                        baraja.getPilaJugadas().peekFirst().getSimbolo().equals("ST")){

                        tablero.cambiarReversa();
                        tablero.checkSaltoDeTurno();
                    }
                    tablero.siguienteTurno();
                } else { // Si no hizo jugada la maquina, arrastra

                    maquina.arrastrarCartas(acumulador, baraja);

                    Thread.sleep(1000);
                    turnoDe++;
                    if(acumulador == 0){
                        acumulador = 1;
                        System.out.println("El jugador " + turnoDe + " ha arrastrado " + acumulador + " carta.");
                        acumulador = 0;

                    } 
                    else {System.out.println("El jugador " + turnoDe + " ha arrastrado " + acumulador + " cartas.");}

                    turnoDe--;
                    Thread.sleep(1000);
                    tablero.siguienteTurno();
                }
            }
            if (tablero.getJugadores()[turnoDe].getCartas().size() == 1) System.out.println(tablero.getJugadores()[turnoDe]+" grita: 'UNO!'");
            if (tablero.getJugadores()[turnoDe].getCartas().size() == 0) {
                System.out.println("\n"+tablero.getJugadores()[turnoDe]+" ha ganado!\n");
                opcion = -1;
                Thread.sleep(4000);
            }
            System.out.println("Presione Enter Para Continuar");
            try {
                System.in.read();
            } catch (InputMismatchException e) {}
            a = sc.nextLine();
            //
        } while (opcion != -1);

        System.out.println("\nGracias por jugar!");
        Thread.sleep(6000);

    }
    
}
