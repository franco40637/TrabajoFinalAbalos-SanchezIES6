package ies6.perico.trabajofinalabalos_sanchezies6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ============================================================
    // RELACIÓN N:N USER — VIAJE (pero aqui es ManyToOne para viaje)
    // ============================================================
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "Debe seleccionar un usuario.")
    private Usuario usuario;

    // ============================================================
    // RELACIÓN VEHÍCULO — VIAJE (cada viaje usa un vehículo)
    // ============================================================
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    @NotNull(message = "Debe seleccionar un vehículo.")
    private Vehiculo vehiculo;

    // ============================================================
    // TIPO DE DISTANCIA (CORTA, MEDIA, LARGA)
    // ============================================================
    @NotNull(message = "Debe seleccionar un tipo de viaje.")
    @Column(name = "tipo_distancia")
    private String tipoDistancia;

    // ============================================================
    // PRECIO CALCULADO AUTOMATICAMENTE
    // ============================================================
    private Double precio;

    // ============================================================
    // ACTIVACIÓN / ELIMINACION LOGICA
    // ============================================================
    private boolean activo = true;

    // ============================================================
    // GETTERS & SETTERS
    // ============================================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getTipoDistancia() {
        return tipoDistancia;
    }

    public void setTipoDistancia(String tipoDistancia) {
        this.tipoDistancia = tipoDistancia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
