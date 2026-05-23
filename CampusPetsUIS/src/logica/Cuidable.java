package logica;

public interface Cuidable {
    
    void comer() throws MascotaException;
    void jugar() throws MascotaException;
    void dormir() throws MascotaException;
    void hacerSonido();
    String verEstado();
    void degradarAtributos();
}