package logica;

public class Gato extends Mascota {

    public Gato(String nombre) {
        super(nombre, "Gato");
    }

    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: Miau");
    }

    @Override
    public void jugar() throws MascotaException {
        if (energia <= 10) {
            throw new MascotaException(nombre + " prefiere dormir que jugar. Está agotado.");
        }
        felicidad = limitar(felicidad + 25);
        energia = limitar(energia - 10);
        hambre = limitar(hambre - 6);
    }
}
