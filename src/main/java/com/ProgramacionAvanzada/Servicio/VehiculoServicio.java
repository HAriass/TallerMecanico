package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Vehiculo;
import java.util.List;

public interface VehiculoServicio {
    public List<Vehiculo> listaVehiculos();
    public void registrar(Vehiculo vehiculo);
    public void eliminar(Vehiculo vehiculo);
    public Vehiculo localizarVehiculo(Vehiculo vehiculo);
}
