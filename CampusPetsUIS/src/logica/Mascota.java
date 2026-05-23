package logica;

public abstract class Mascota implements Cuidable {
    
    // Atributos
    protected String nombre;
    protected String especie;
    protected int hambre;
    protected int energia;
    protected int felicidad;
    
    // Constructor
    public Mascota(String nombre, String especie) {
        this.nombre = nombre;
        this.especie = especie;
        this.hambre = 100;
        this.energia = 100;
        this.felicidad = 100;
    }
    
    // Métodos principale
    @Override
    public void comer() throws MascotaException {
        if (hambre >= 100) {
            throw new MascotaException(nombre + " no tiene hambre. ¡Ya está satisfecho!");
        }
    hambre = Math.min(100, hambre + 20);
    System.out.println(nombre + " ha comido. Hambre: " + hambre + "%");
    }
    
    @Override
    public void jugar() throws MascotaException {
        if (energia <= 10) {
            throw new MascotaException(nombre + " está muy cansado para jugar. ¡Déjalo dormir!");
        }
    felicidad = Math.min(100, felicidad + 20);
    energia = Math.max(0, energia - 10);
    System.out.println(nombre + " ha jugado. Felicidad: " + felicidad + "%");
    }
    
    @Override
    public void dormir() throws MascotaException {
        if (energia >= 100) {
            throw new MascotaException(nombre + " no tiene sueño. ¡Ya descansó suficiente!");
        }
    energia = Math.min(100, energia + 30);
    System.out.println(nombre + " ha dormido. Energía: " + energia + "%");
    }
    
    @Override
    public void degradarAtributos() {
        hambre = Math.max(0, hambre - 5);
        energia = Math.max(0, energia - 3);
        felicidad = Math.max(0, felicidad - 4);
    }
    
    @Override
    public String verEstado() {
        return "🐾 " + nombre + " (" + especie + ")" +
               "\n  Hambre: " + hambre + "%" +
               "\n  Energía: " + energia + "%" +
               "\n  Felicidad: " + felicidad + "%";
    }
    
    // Método abstracto que cada subclase implementa
    @Override
    public abstract void hacerSonido();
    
    // Getters
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getHambre() { return hambre; }
    public int getEnergia() { return energia; }
    public int getFelicidad() { return felicidad; }
    
    // Setters
    public void setHambre(int hambre) { this.hambre = hambre; }
    public void setEnergia(int energia) { this.energia = energia; }
    public void setFelicidad(int felicidad) { this.felicidad = felicidad; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}