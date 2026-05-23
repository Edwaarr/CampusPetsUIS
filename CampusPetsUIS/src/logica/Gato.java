package logica;

public class Gato extends Mascota {
    
    // Constructor
    public Gato(String nombre) {
        super(nombre, "Gato");
    }
    
    // Implementación del método abstracto
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Miau!");
    }
    
    // Comportamiento especial del gato
    @Override
    public void jugar() throws MascotaException {
        if (energia <= 10) {
            throw new MascotaException(nombre + " prefiere dormir que jugar. ¡Está agotado!");
        }
    felicidad = Math.min(100, felicidad + 25);
    energia = Math.max(0, energia - 8);
    System.out.println(nombre + " juega con un ovillo. Felicidad: " + felicidad + "%");
    }
}