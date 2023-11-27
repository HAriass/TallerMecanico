package com.ProgramacionAvanzada.modelo;

public class ServicioCantidad {

    private String nombreServicio;
    private int cantidadUtilizada;

    // Constructor, getters y setters

    // Constructor
    public ServicioCantidad(String nombreServicio, int cantidadUtilizada) {
        this.nombreServicio = nombreServicio;
        this.cantidadUtilizada = cantidadUtilizada;
    }

    // Getters y setters
    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getCantidadUtilizada() {
        return cantidadUtilizada;
    }

    public void setCantidadUtilizada(int cantidadUtilizada) {
        this.cantidadUtilizada = cantidadUtilizada;
    }
}
