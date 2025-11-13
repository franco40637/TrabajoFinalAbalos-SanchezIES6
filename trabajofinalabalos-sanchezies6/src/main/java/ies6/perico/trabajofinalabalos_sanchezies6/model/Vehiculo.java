package ies6.perico.trabajofinalabalos_sanchezies6.model;

import jakarta.persistence.*;

@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String marca;
    private String modelo;
    private int anio;
    private String color;

    @Column(unique = true, nullable = false)
    private String patente;

    // 🔹 Tipo de vehículo según la consigna (X, Luxe o Premium)
    private String tipo;

    // 🔹 Eliminación lógica
    private boolean activo = true;

    // 🔹 Constructor vacío (requerido por JPA)
    public Vehiculo() {}

    // 🔹 Constructor con parámetros
    public Vehiculo(String marca, String modelo, int anio, String color, String patente, String tipo) {
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
