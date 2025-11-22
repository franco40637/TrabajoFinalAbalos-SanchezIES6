package ies6.perico.trabajofinalabalos_sanchezies6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // No puede estar vacío y min 3, max 50
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios.")
    @Column(nullable = false)
    private String nombre;

    //No puede estar vacío y min 3, max 50
    @NotBlank(message = "El apellido es obligatorio.")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios.")
    @Column(nullable = false)
    private String apellido;

    // Email válido y no vacío
    @NotBlank(message = "El email es obligatorio.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Formato de email no válido.")
    @Column(nullable = false, unique = true)
    private String email;

    //DNI no vacío y solo 8 dígitos 
    @NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "^\\d{8}$", message = "El DNI debe contener 8 dígitos numéricos.")
    @Column(nullable = false, unique = true)
    private String dni;
    
    // Teléfono Obligatorio
@NotBlank(message = "El teléfono es obligatorio para la comunicación en el servicio.") // Se requiere valor
@Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres (solo números, espacios, guiones).") 
@Pattern(regexp = "^[\\d\\s\\(\\)\\-]{7,15}$", message = "Formato de teléfono no válido. Use solo números, espacios, paréntesis o guiones.")
private String telefono; 
    

    // Eliminación lógica
    private boolean activo = true; 

    // Constructor vacío
    public Usuario() {}

    // Constructor con parametros
    public Usuario(String nombre, String apellido, String email, String dni, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.telefono = telefono;
        this.activo = true;
    }

    // ---------- Getters y Setters ----------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    //  Getter y Setter para 'activo'
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}