package br.tornese.imersao.JavaJWT.domain.servicos;

import br.tornese.imersao.JavaJWT.domain.DTOs.ClienteDTO;
import br.tornese.imersao.JavaJWT.domain.entidades.ICliente;
import br.tornese.imersao.JavaJWT.domain.model_views.ClienteNotFound;

public class ClienteServico {
    public static ICliente buildCliente(ClienteDTO clienteDTO, ICliente cliente){
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTel());

        return cliente;
    }

    public static ClienteDTO getClienteDTO(ICliente cliente){
        var clienteDTO = new ClienteDTO();
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTel(cliente.getTelefone());

        return clienteDTO;
    }

    public static ClienteNotFound getClienteNotFound(int id) {
        return new ClienteNotFound(id);
    }
}
