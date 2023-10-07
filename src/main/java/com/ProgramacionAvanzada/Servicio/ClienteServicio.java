package com.ProgramacionAvanzada.Servicio;

import com.ProgramacionAvanzada.modelo.Cliente;
import java.util.List;


public interface ClienteServicio {
    public List<Cliente> listaClientes();
    public void registrar(Cliente cliente);
    public void eliminar(Cliente cliente);
    public Cliente localizarCliente(Cliente cliente);

    public Cliente obtenerClientePorId(Long clienteId);
}
