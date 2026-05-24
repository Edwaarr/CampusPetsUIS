package logica;

public class Perro extends Mascota {

    public Perro(String nombre) {
        super(nombre, "Perro");
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: Guau");
    }

    @Override
    public void jugar() throws MascotaException {
        if (energia <= 10) {
            throw new MascotaException(nombre + " está agotado. Necesita descansar.");
        }
        felicidad = limitar(felicidad + 30);
        energia = limitar(energia - 15);
        hambre = limitar(hambre - 8);
    }
}
