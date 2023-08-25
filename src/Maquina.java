public class Maquina extends Jugador {
    public Maquina(String nombre){
        super(nombre);
    }
    public boolean jugar(Baraja baraja){
        //Revisa la primera carta de la pila
        Carta carta = baraja.getPilaJugadas().peekFirst();
        //Si no hay primera carta, tira cualquiera
        if (carta == null){
            tirarA(0, baraja);
            return true;
        }
        //Busca si tiene una carta del mismo simbolo, si la tiene la tira
        for (int i=0; i<cartas.size(); i++){
            if (cartas.get(i).getSimbolo().equals(carta.getSimbolo())){
                tirarA(i, baraja);
                Tablero.setArrastraron(false);
                return true;
            }
        }
        //Si la carta es un +2, retorna false
        if (!Tablero.getArrastraron())
            if (carta.getSimbolo().equals("+2")){
                return false;
        }
        
        //Busca si tiene una carta del mismo color, si la tiene la tira
        for (int i=0; i<cartas.size(); i++){
            if (cartas.get(i).getColor().equals(carta.getColor())){
                tirarA(i, baraja);
                Tablero.setArrastraron(false); //Solo cuando se pone
                return true;
            }
        }
        //Si no pudo tirar, arrastra 1
        Carta cartaArrastrada = baraja.getColaDeArrastre().peekFirst();
        System.out.println(this+" arrastra");
        //Si puede tirar la recién arrastrada, lo hace, si no, pasa
        if (cartaArrastrada.getColor().equals(carta.getColor()) ||
            cartaArrastrada.getSimbolo().equals(carta.getSimbolo())){
                cartaArrastrada = arrastrarDe(baraja);
                System.out.println(this+" arrastra "+cartaArrastrada+" y la pone");
                tirarA(cartas.size()-1, baraja);
                Tablero.setArrastraron(false);
                return true;
            }
        return false;
    }
}
