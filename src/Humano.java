public class Humano extends Jugador {

    public Humano(String nombre) {
        super(nombre);
    }
    // Las dos jugadas posibles para el humano
    // void realizar(int opcion, Baraja baraja){
    // if(opcion == 1){
    // pass

    /*
     * Casuística
     * se require un booleano que controle si existe jugada
     * boolean jugable;
     * 1. cartaArriba no es +2:
     * a. Si tiene el mismo símbolo, sale return 1 x
     * b. Si tiene el mismo color, sale return 1 x
     * c. Si la carta elegida es errónea, mensaje return 4
     * d. Si se hace arrastrar, check y sale(1, 2) o mensaje(4)
     * (1) return 1 (2) return 2[la puede poner] (4) return 4
     * 2. cartaArriba es un +2:
     * a. Si se tiene con el mismo símbolo, sale return (1)
     * b. Si la carta elegida es errónea, mensaje return (4)
     * c. Si no tiene, arrastra 2*contador return ¿3? (falta saber cómo se controla)
     * Siempre se debe controlar:
     * + solo se puede arrastrar si no existe jugada correcta
     * + for (int i = 0, i<a = size.tales(); i++){
     * -->ver control<--}
     * 
     */
    
    public int jugar(int indice, Baraja baraja, int contadorMasDos) {
        Carta cartaArriba = baraja.getPilaJugadas().peekFirst();
        if (indice >= cartas.size()){
            System.out.println("Carta errónea");
            return 4;
        }
        if (indice == -1) {
            if (puedeTirar(cartaArriba)) {
                System.out.println("No se puede arrastrar, tienes una jugada posible");
                return 4;
            } else {
                //si no es un numero, no se sacan
                arrastrarCartas(contadorMasDos, baraja);
                //ver si se automatiza o qué /////////////////////////////////
                //se debe hacer check si la carta nueva se debe poner
                return 1;

            }
        } else {
            if (cartaArriba == null){
                tirarA(indice, baraja);
                return 1;
            }
            Carta carta = cartas.get(indice);
            // Si no tenemos un +2
            if (!cartaArriba.getSimbolo().equals("+2")) {
                if (cartaArriba.getSimbolo().equals(carta.getSimbolo()) ||
                        cartaArriba.getColor().equals(carta.getColor())) {
                    tirarA(indice, baraja);
                    return 1;
                } else {
                    System.out.println("La carta debe tener o el mismo símbolo o el mismo color");
                    return 4;

                }
                // es +2
            } else {
                if (cartaArriba.getSimbolo().equals(carta.getSimbolo())) {
                    tirarA(indice, baraja);
                    Tablero.setArrastraron(false);
                    return 1;
                } else if (Tablero.getArrastraron()){
                    if (cartaArriba.getColor().equals(carta.getColor())){
                        tirarA(indice, baraja);
                        Tablero.setArrastraron(false);
                        return 1;
                    }
                    System.out.println("El anterior jugador arrastró, debes tirar una carta del mismo color");
                    return 4;
                }

                else {
                    System.out.println("Hay un +2 en la pila de jugadas, debes tirar un +2 o recoger");
                    return 4;

                }
            } 
        }

    }

    public boolean puedeTirar(Carta carta) {
        // Busca si tiene una carta del mismo simbolo, si la tiene la tira
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).getSimbolo().equals(carta.getSimbolo())) {
                return true;
            }
        }
        // Si la carta es un +2, retorna false
        if (!Tablero.getArrastraron())
            if (carta.getSimbolo().equals("+2")) {
                return false;
        }
        // Busca si tiene una carta del mismo color, si la tiene la tira
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).getColor().equals(carta.getColor())) {
                return true;
            }
        }
        return false;
    }
}

/*
 * if (cartaArriba.getSimbolo().equals(carta.getSimbolo())){
 * tirarA(indice, baraja);
 * return true;
 * }
 * else if (cartaArriba.getSimbolo().equals("+2")){
 * return false;
 * }
 * 
 * else if (cartaArriba.getColor().equals(carta.getColor()))
 * {
 * tirarA(indice, baraja);
 * return true;
 * }
 * else{ //System.out.println("Tú no metes cabra saramambiche");
 * return false;}
 */