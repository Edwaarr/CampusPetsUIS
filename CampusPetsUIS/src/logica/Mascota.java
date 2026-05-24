package logica;

public abstract class Mascota implements Cuidable {

    private static final int MINIMO = 0;
    private static final int MAXIMO = 100;

    protected String nombre;
    protected String especie;
    protected int hambre;
    protected int energia;
    protected int felicidad;

    public Mascota(String nombre, String especie) {
        this.nombre = nombre;
        this.especie = especie;
        this.hambre = MAXIMO;
        this.energia = MAXIMO;
        this.felicidad = MAXIMO;
    }

    @Override
    public void comer() throws MascotaException {
        if (hambre >= MAXIMO) {
            throw new MascotaException(nombre + " no tiene hambre. Ya está satisfecho.");
        }
        hambre = limitar(hambre + 25);
        felicidad = limitar(felicidad + 5);
    }

    @Override
    public void jugar() throws MascotaException {
        if (energia <= 10) {
            throw new MascotaException(nombre + " está muy cansado para jugar. Déjalo dormir.");
        }
        felicidad = limitar(felicidad + 20);
        energia = limitar(energia - 12);
        hambre = limitar(hambre - 6);
    }

    @Override
    public void dormir() throws MascotaException {
        if (energia >= MAXIMO) {
            throw new MascotaException(nombre + " no tiene sueño. Ya descansó suficiente.");
        }
        energia = limitar(energia + 30);
        hambre = limitar(hambre - 5);
    }

    @Override
    public void degradarAtributos() {
        hambre = limitar(hambre - 6);
        energia = limitar(energia - 4);
        felicidad = limitar(felicidad - 5);
    }

    @Override
    public String verEstado() {
        return nombre + " (" + especie + ")"
                + "\n  Hambre: " + hambre + "%"
                + "\n  Energía: " + energia + "%"
                + "\n  Felicidad: " + felicidad + "%";
    }

    @Override
    public abstract void hacerSonido();

    public boolean estaEnEstadoCritico() {
        return hambre == MINIMO || energia == MINIMO || felicidad == MINIMO;
    }

    public void recuperar() {
        hambre = 60;
        energia = 60;
        felicidad = 60;
    }

    protected int limitar(int valor) {
        return Math.max(MINIMO, Math.min(MAXIMO, valor));
    }

    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getHambre() { return hambre; }
    public int getEnergia() { return energia; }
    public int getFelicidad() { return felicidad; }

    public void setHambre(int hambre) { this.hambre = limitar(hambre); }
    public void setEnergia(int energia) { this.energia = limitar(energia); }
    public void setFelicidad(int felicidad) { this.felicidad = limitar(felicidad); }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
