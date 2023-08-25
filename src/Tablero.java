import java.util.Random;

public class Tablero {
    private Baraja baraja;
    private Humano jugador1;
    private Maquina jugador2;
    private Maquina jugador3;
    private Maquina jugador4;
    private boolean reversa;
    private boolean saltoDeTurno;
    private boolean mismoTurno;
    private int turno;
    private Jugador[] jugadores = new Jugador[4];
    private int cartasARepartir = 8;
    private int acumuladorMasDos = 0;


    private static boolean arrastraron;
    
    
    public Tablero(){
        baraja = new Baraja();
        jugador1 = new Humano("Jugador 1 (Tú)");
        jugador2 = new Maquina("Jugador 2 (Máquina)");
        jugador3 = new Maquina("Jugador 3 (Máquina)");
        jugador4 = new Maquina("Jugador 4 (Máquina)");
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
        jugadores[2] = jugador3;
        jugadores[3] = jugador4;
        Random rand = new Random();
        this.turno = rand.nextInt(4);
        repartirCartas();
    }
    //Wall of text de emilio que falta arreglar ajajajajjaja
    public String toString(){
        String t1="";
        String t2="";
        String t3="";
        String t4="";
        StringBuffer visiblesBuffer = new StringBuffer();
        int i = 0;
        int sliceIndex = 0;
        for (Carta carta:jugador1.getCartas()){
            visiblesBuffer.append(carta + " ");
            i++;
            if (i == 12) sliceIndex = visiblesBuffer.length();
        }
        String visibles = visiblesBuffer.toString();
        if (jugador3.getCartas().size()<4) t1 = "\t\t\t\t";
        else if (jugador3.getCartas().size()<8) t1 = "\t\t\t";
        else if (jugador3.getCartas().size()<12) t1 = "\t\t";
        else if (jugador3.getCartas().size()<16) {
            t1 = "\t";
            t2 = "\t\t\t\t";
            }
        else {
            t1 = "\t";
            t2 = "\t\t\t";
        }
        if (jugador2.getCartas().size()<4) t4 = "\t\t\t\t";
        else if (jugador2.getCartas().size()<8) t4 = "\t\t\t";
        else if (jugador2.getCartas().size()<12) t4 = "\t\t";
        else if (jugador2.getCartas().size()<16) {
            t4 = "\t";
            t3 = "\t\t\t\t";
            }
        else {
            t4 = "\t";
            t3 = "\t\t\t";
        }
        StringBuffer s = new StringBuffer();
        s.append("\t"+jugador3+"\t\t"+jugador4+"\t"+"\n");
        if (jugador3.getCartas().size()<=12 && jugador4.getCartas().size()<=12){
            s.append("\t"+"X ".repeat(jugador3.getCartas().size())+t1);
            s.append("X ".repeat(jugador4.getCartas().size())+"\t"+"\n");
        }
        else if (jugador3.getCartas().size()>12 && jugador4.getCartas().size()<=12){
            s.append("\t"+"X ".repeat(12)+t1);
            s.append("X ".repeat(jugador4.getCartas().size())+"\t"+"\n");
            s.append("\t"+"X ".repeat(jugador3.getCartas().size()-12)+"\n");
        }
        else if (jugador3.getCartas().size()<=12 && jugador4.getCartas().size()>12){
            s.append("\t"+"X ".repeat(jugador3.getCartas().size())+t1);
            s.append("X ".repeat(12)+"\n");
            s.append("\t\t\t\t\t"+"X ".repeat(jugador4.getCartas().size()-12)+"\n");
        }
        else if (jugador3.getCartas().size()>12 && jugador4.getCartas().size()>12){
            s.append("\t"+"X ".repeat(12));
            s.append("\t"+"X ".repeat(12)+"\n");
            s.append("\t"+"X ".repeat(jugador3.getCartas().size()-12)+t2);
            s.append("X ".repeat(jugador4.getCartas().size()-12)+"\n");
        }
        s.append("\n\n\n");
        if (baraja.getPilaJugadas().peekFirst() != null){
            s.append("\t\t\t\t"+baraja.getPilaJugadas().peekFirst()+"\t\t"+"\n");
        }
        s.append("\n\n\n");
        if (jugador2.getCartas().size()<=12 && jugador1.getCartas().size()<=12){
            s.append("\t"+"X ".repeat(jugador2.getCartas().size())+t4);
            s.append(visibles+"\t"+"\n");
        }
        else if (jugador2.getCartas().size()>12 && jugador1.getCartas().size()<=12){
            s.append("\t"+"X ".repeat(jugador2.getCartas().size()-12)+"\n");
            s.append("\t"+"X ".repeat(12)+t4);
            s.append(visibles+"\t"+"\n");
        }
        else if (jugador2.getCartas().size()<=12 && jugador1.getCartas().size()>12){
            s.append("\t\t\t\t\t"+visibles.substring(sliceIndex)+"\n");
            s.append("\t"+"X ".repeat(jugador2.getCartas().size())+t4);
            s.append(visibles.substring(0,sliceIndex)+"\n");
        }
        else if (jugador2.getCartas().size()>12 && jugador1.getCartas().size()>12){
            s.append("\t"+"X ".repeat(jugador2.getCartas().size()-12)+t3);
            s.append(visibles.substring(sliceIndex)+"\n");
            s.append("\t"+"X ".repeat(12)+t4);
            s.append(visibles.substring(0,sliceIndex)+"\n");
        }
        s.append("\t"+jugador2+"\t\t"+jugador1+"\t"+"\n");
        return  s.toString();
    }
    /////Getters y setters///////
    public Baraja getBaraja() {
        return this.baraja;
    }

    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }

    public Humano getJugador1() {
        return this.jugador1;
    }

    public void setJugador1(Humano jugador1) {
        this.jugador1 = jugador1;
    }

    public Maquina getJugador2() {
        return this.jugador2;
    }

    public void setJugador2(Maquina jugador2) {
        this.jugador2 = jugador2;
    }

    public Maquina getJugador3() {
        return this.jugador3;
    }

    public void setJugador3(Maquina jugador3) {
        this.jugador3 = jugador3;
    }

    public Maquina getJugador4() {
        return this.jugador4;
    }

    public void setJugador4(Maquina jugador4) {
        this.jugador4 = jugador4;
    }

    public boolean getReversa(){
        return this.reversa;
    }

    public boolean getSaltoDeTurno(){
        return this.saltoDeTurno;
    }

    public void setSaltoDeTurno(boolean salto){
        this.saltoDeTurno = salto;
    }

    public static boolean getArrastraron(){
        return arrastraron;
    }

    public static void setArrastraron(boolean hanArrastrado){
        arrastraron = hanArrastrado;
    }

    public int getTurno(){
        return this.turno;
    }

    public void setTurno(int elturno){
        this.turno = elturno;
    }

    public Jugador[] getJugadores(){
        return jugadores;
    }
    //Función que lleva la cuenta de la acumulación de cartas +2
    public int getAcumuladorMas2(){
        if(getArrastraron()){
            this.acumuladorMasDos = 0;
            return this.acumuladorMasDos;}
        
        if(getMismoTurno()){
            setMismoTurno(false);
            return this.acumuladorMasDos;
        }

        if (baraja.getPilaJugadas().size() > 0){
                if (baraja.getPilaJugadas().getFirst().getSimbolo().equals("+2")){
                    this.acumuladorMasDos+=2;
                    return this.acumuladorMasDos;
                }
                else{
                    this.acumuladorMasDos=0;
                    return this.acumuladorMasDos;
                }
            }
        return this.acumuladorMasDos;
    }

    public boolean getMismoTurno(){
        return mismoTurno;
    }

    public void setMismoTurno(boolean mismo){
        this.mismoTurno = mismo;
    }
    /////////////////////////////

    //////CHECKS////////////
    public void checkShuffle(){
        if (this.acumuladorMasDos > baraja.getColaDeArrastre().size() || baraja.getColaDeArrastre().size() == 0){
            Baraja.shuffle(baraja);
        }
    }

    public void checkSaltoDeTurno(){
        if (baraja.getPilaJugadas().peekFirst().getSimbolo().equals("ST")){
            int sigTurno = getTurno();
            setSaltoDeTurno(true);
            if (!reversa){
                sigTurno +=1;

                sigTurno = ((sigTurno%4)+4)%4;
                    
                }
            else{
                sigTurno -=1;
                    
                sigTurno = ((sigTurno%4)+4)%4;
                    
                }
            for (Carta carta: jugadores[sigTurno].getCartas()){
                if(carta.getSimbolo().equals("ST")){
                    setSaltoDeTurno(false);
                    sigTurno++;
                    System.out.println("Jugador " + sigTurno + ", tienes un salto de turno para responder!");
                    sigTurno--;
                    break;
                }

            }
            
        }
    }

    public void checkRepetirTurno(int jugada){
        if (jugada == 1){return;}
        else{
            if(!reversa){
                if (!saltoDeTurno){
                    setTurno(getTurno()-1);
                }
                else{
                    setTurno(getTurno()-2);
                }
            }
            else{
                if(!saltoDeTurno){
                    setTurno(getTurno()+1);
                }
                else{
                    setTurno(getTurno()+2);
                }
            }
            setMismoTurno(true);
        }
    }
    /////////////////////////////

    //////FuncionesUtiles////////////
    public void siguienteTurno(){
        if (!reversa){
            if(!saltoDeTurno){
                this.turno +=1;

                this.turno = ((this.turno%4)+4)%4;
                
            }
            else{
                this.turno +=2;
                this.turno = ((this.turno%4)+4)%4;
                
                setSaltoDeTurno(false);
            }
        }
        else{
            if(!saltoDeTurno){
                this.turno -=1;
                
                this.turno = ((this.turno%4)+4)%4;
                
            }
            else{
                this.turno -=2;

                this.turno = ((this.turno%4)+4)%4;
                
                setSaltoDeTurno(false);
            }
        }

        
    }
    
    private void repartirCartas(){
        for (Jugador jugador: jugadores){
            for(int i=0; i<cartasARepartir;i++){
                jugador.arrastrarDe(baraja);
            }
        }
    }

    public void cambiarReversa(){
        if (baraja.getPilaJugadas().peekFirst()!= null){
            if(baraja.getPilaJugadas().peekFirst().getSimbolo().equals("IR")){
                System.out.println("Se cambia orientación del juego.");
                reversa = !reversa;
            }
        }
    }
    /////////////////////////////
    



    

}
