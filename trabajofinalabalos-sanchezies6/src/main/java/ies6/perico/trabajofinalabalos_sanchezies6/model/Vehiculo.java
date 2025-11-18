package ies6.perico.trabajofinalabalos_sanchezies6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // ========================= CAMPOS BÁSICOS =========================

    @NotBlank(message = "Debe ingresar la marca del vehículo.")
    private String marca;

    @NotBlank(message = "Debe ingresar el modelo del vehículo.")
    private String modelo;

    @NotNull(message = "Debe ingresar el año de fabricación.")
    @Max(value = 9999, message = "El año no puede ser mayor a 9999") 
    private Integer anio;

    //Campo 'color' 
    @NotBlank(message = "Debe ingresar el color del vehículo.")
    private String color; 

    @NotBlank(message = "Debe ingresar la patente del vehículo.")
    @Column(unique = true)
    private String patente;

    // Tipo de vehiculo: X, LUXE, PREMIUM
    @NotBlank(message = "Debe seleccionar el tipo de vehículo.")
    private String tipo;

    // ========================= RELACIÓN 1:1 =========================

    @OneToOne
    @JoinColumn(name = "conductor_id", unique = true, nullable = false)
    @NotNull(message = "Debe asignar un conductor.")
    private Conductor conductor;

    // ========================= ESTADO ===============================

    private boolean activo = true;

    // ========================= GETTERS & SETTERS =====================

    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}