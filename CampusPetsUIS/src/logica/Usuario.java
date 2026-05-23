package logica;

public class Usuario {
    
    // Atributos
    private String nombre;
    private String programaAcademico;
    private Mascota mascota;
    
    // Constructor
    public Usuario(String nombre, String programaAcademico) {
        this.nombre = nombre;
        this.programaAcademico = programaAcademico;
        this.mascota = null;
    }
    
    // Método para registrar perfil
    public void registrarPerfil() {
        System.out.println("Perfil registrado:");
        System.out.println("  Nombre: " + nombre);
        System.out.println("  Programa: " + programaAcademico);
    }
    
    // Método para asignar mascota
    public void asignarMascota(Mascota mascota) {
        this.mascota = mascota;
        System.out.println(nombre + " adoptó a " + mascota.getNombre() + " (" + mascota.getEspecie() + ")");
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public String getProgramaAcademico() { return programaAcademico; }
    public Mascota getMascota() { return mascota; }
    
    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setProgramaAcademico(String programaAcademico) { 
        this.programaAcademico = programaAcademico; 
    }
}