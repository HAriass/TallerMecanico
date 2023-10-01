package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.VehiculoDAO;
import com.ProgramacionAvanzada.modelo.Vehiculo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehiculoServicioImpleamentacion implements VehiculoServicio{
    
    @Autowired
    private VehiculoDAO vehiculoDao;

    @Transactional(readOnly = true)
    @Override
    public List<Vehiculo> listaVehiculos() {
        return (List<Vehiculo>) vehiculoDao.findAll();
    }

    @Override
    public void registrar(Vehiculo vehiculo) {
        vehiculoDao.save(vehiculo);
    }

    @Override
    public void eliminar(Vehiculo vehiculo) {
        vehiculoDao.delete(vehiculo);
    }

    @Override
    public Vehiculo localizarVehiculo(Vehiculo vehiculo) {
        return vehiculoDao.findById(vehiculo.getId()).orElse(null);
    }
    
}
