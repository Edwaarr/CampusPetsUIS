public abstract class Mascota {
    
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
    
    // Métodos principales
    public void comer() {
        if (hambre < 100) {
            hambre = Math.min(100, hambre + 20);
            System.out.println(nombre + " ha comido. Hambre: " + hambre + "%");
        } else {
            System.out.println(nombre + " no tiene hambre.");
        }
    }
    
    public void jugar() {
        if (energia > 10) {
            felicidad = Math.min(100, felicidad + 20);
            energia = Math.max(0, energia - 10);
            System.out.println(nombre + " ha jugado. Felicidad: " + felicidad + "%");
        } else {
            System.out.println(nombre + " está muy cansado para jugar.");
        }
    }
    
    public void dormir() {
        if (energia < 100) {
            energia = Math.min(100, energia + 30);
            System.out.println(nombre + " ha dormido. Energía: " + energia + "%");
        } else {
            System.out.println(nombre + " no tiene sueño.");
        }
    }
    
    public void degradarAtributos() {
        hambre = Math.max(0, hambre - 5);
        energia = Math.max(0, energia - 3);
        felicidad = Math.max(0, felicidad - 4);
    }
    
    public String verEstado() {
        return "🐾 " + nombre + " (" + especie + ")" +
               "\n  Hambre: " + hambre + "%" +
               "\n  Energía: " + energia + "%" +
               "\n  Felicidad: " + felicidad + "%";
    }
    
    // Método abstracto que cada subclase implementa
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
}