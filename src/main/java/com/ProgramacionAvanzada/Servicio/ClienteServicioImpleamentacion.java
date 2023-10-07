
package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.DAO.ClienteDAO;
import com.ProgramacionAvanzada.modelo.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicioImpleamentacion implements ClienteServicio{
    
    @Autowired
    private ClienteDAO clienteDao;

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listaClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    public void registrar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public Cliente localizarCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getId()).orElse(null);
    }

    @Override
    public Cliente obtenerClientePorId(Long clienteId) {
        return clienteDao.findById(clienteId).orElse(null);
    }

    
}
