import java.util.*;
public class Jugador{
    protected String nombre;
    protected List<Carta> cartas = new ArrayList<Carta>();
    
    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public Carta arrastrarDe(Baraja baraja){
        Carta carta = baraja.getColaDeArrastre().removeFirst();
        cartas.add(carta);
        return carta;
    }
    /*
    @summary Tira la carta en el indice especificado de la mano
    del jugador a la pila de jugadas
    */
    public void tirarA(int cartaIndex, Baraja baraja){
        Carta carta = this.cartas.remove(cartaIndex);
        baraja.getPilaJugadas().addFirst(carta);
 
    }

    public void arrastrarCartas(int cantidad, Baraja baraja){
        if (cantidad == 0){
            cantidad = 1;
        }
        for(int i=0; i<cantidad; i++){
            arrastrarDe(baraja);
        }
        Tablero.setArrastraron(true);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Carta> getCartas() {
        return this.cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
    
    public String toString(){
        return nombre;
    }
}