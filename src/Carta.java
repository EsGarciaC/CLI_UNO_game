public class Carta {
    //simbolo string que puede ser 1,2,3,4,5,6,7,8,9,st,+2,ir
    private String simbolo;
    //Color es verde, rojo, amarilo o azul. 
    private String color;

    public Carta(String color_arg, String simbolo_arg) {
        this.color = color_arg;
        this.simbolo = simbolo_arg;
        }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        if (this.color.equals("amarillo")){
            return "\u001B[33m"+this.simbolo+"\u001B[0m";
        }
        else if (this.color.equals("azul")){
            return "\u001B[34m"+this.simbolo+"\u001B[0m";
        }
        else if (this.color.equals("rojo")){
            return "\u001B[31m"+this.simbolo+"\u001B[0m";
        }
        else{
            return "\u001B[32m"+this.simbolo+"\u001B[0m";
        }
    }
}
