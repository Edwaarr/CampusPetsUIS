package logica;

public class Perro extends Mascota {
    
    // Constructor
    public Perro(String nombre) {
        super(nombre, "Perro");
    }
    
    // Implementación del método abstracto
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Guau!");
    }
    
    // Comportamiento especial del perro
    @Override
    public void jugar() throws MascotaException {
        if (energia <= 10) {
            throw new MascotaException(nombre + " está agotado. ¡Necesita descansar!");
        }
    felicidad = Math.min(100, felicidad + 30);
    energia = Math.max(0, energia - 15);
    System.out.println(nombre + " juega a buscar la pelota. Felicidad: " + felicidad + "%");
    }
}