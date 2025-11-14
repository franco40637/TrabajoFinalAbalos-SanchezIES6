package ies6.perico.trabajofinalabalos_sanchezies6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^\\d{7,9}$", message = "El DNI debe contener entre 7 y 9 dígitos")
    @Column(unique = true, nullable = false)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "La licencia es obligatoria")
    @Pattern(regexp = "^[A-Z]{2}\\d{6}$", message = "La Licencia debe tener un formato válido (Ej: AB123456)")
    @Column(unique = true, nullable = false)
    private String licencia;

    // --- RELACIÓN UNO A UNO (OneToOne) con VEHÍCULO ---
    // Un Conductor tiene UN Vehículo. El Conductor posee la clave foránea.
    @OneToOne
    @JoinColumn(name = "vehiculo_id", unique = true) // Asegura que el vehiculo_id sea único
    private Vehiculo vehiculo;

    private boolean activo = true;
    
    // Constructores, Getters y Setters
    public Conductor() {}

    public Conductor(String dni, String nombre, String apellido, String licencia, Vehiculo vehiculo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.licencia = licencia;
        this.vehiculo = vehiculo;
        this.activo = true;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}