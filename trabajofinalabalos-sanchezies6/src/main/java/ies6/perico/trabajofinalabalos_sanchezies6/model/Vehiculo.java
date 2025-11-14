package ies6.perico.trabajofinalabalos_sanchezies6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Vehiculo {
    
    // El ID 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // Validación: La marca no puede estar vacía y debe tener entre 3 y 50 caracteres
    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 3, max = 50, message = "La marca debe tener entre 3 y 50 caracteres")
    private String marca;
    
    // Validación: El modelo no puede estar vacío
    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;
    
    // Validación: El año es obligatorio y debe ser un valor razonable
    @NotNull(message = "El año es obligatorio")
    @Min(value = 1950, message = "El año debe ser posterior a 1950")
    @Max(value = 2026, message = "El año no puede ser futuro")
    private int anio;
    
    // Validación: El color no puede estar vacío
    @NotBlank(message = "El color es obligatorio")
    private String color;
    
    // Validación: Patente es obligatoria y se valida con Regex para formato de patente
    // Se mantiene la restricción de base de datos
    @NotBlank(message = "La patente es obligatoria")
    @Pattern(regexp = "^[A-Z]{2}\\d{3}[A-Z]{2}$|^[A-Z]{3}\\d{3}$", message = "Formato de patente no válido (Ej: AB123CD o ABC123)")
    @Column(unique = true, nullable = false)
    private String patente;
    
    // Validación: El tipo es obligatorio (Se valida con @NotBlank porque viene de un select)
    @NotBlank(message = "Debe seleccionar un tipo de vehículo")
    // Tipo de vehículo según la consigna (X, Luxe o Premium)
    private String tipo;
    
    // Eliminación lógica
    private boolean activo = true;

    // ... (El resto del código: Constructores, Getters y Setters se mantienen) ...
    
    // Constructor vacío (requerido por JPA)
    public Vehiculo() {}
    
    // Constructor con parámetros
    public Vehiculo(String marca, String modelo, int anio, String color,
                    String patente, String tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.patente = patente;
        this.tipo = tipo;
        this.activo = true;
    }
    
    // ---------- Getters y Setters ----------
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}