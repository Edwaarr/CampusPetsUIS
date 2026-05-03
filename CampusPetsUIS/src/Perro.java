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
    public void jugar() {
        if (energia > 10) {
            felicidad = Math.min(100, felicidad + 30);
            energia = Math.max(0, energia - 15);
            System.out.println(nombre + " juega a buscar la pelota. Felicidad: " + felicidad + "%");
        } else {
            System.out.println(nombre + " está agotado.");
        }
    }
}