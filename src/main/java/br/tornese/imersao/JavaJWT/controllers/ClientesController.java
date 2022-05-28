package br.tornese.imersao.JavaJWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.tornese.imersao.JavaJWT.domain.DTOs.ClienteDTO;
import br.tornese.imersao.JavaJWT.domain.servicos.ClienteServico;
import br.tornese.imersao.JavaJWT.infraestrutura.entidades.Cliente;
import br.tornese.imersao.JavaJWT.infraestrutura.repositorios.ClienteRepo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
public class ClientesController {

    @Autowired
    private ClienteRepo repo;

    @ApiOperation(value = "Cadastra um Cliente")
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "Não tem autorização para acessar"),
			@ApiResponse(code = 403, message = "Não tem permissão para acessar"),
	})
    @CrossOrigin("*")
    @GetMapping("/clientes")
    public ResponseEntity<Iterable<Cliente>> index(){
        Iterable<Cliente> clientes = repo.findAll();
        return ResponseEntity.status(200).body(clientes);
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO cliente){
        var clienteInfra = (Cliente)ClienteServico.buildCliente(cliente, new Cliente());
        repo.save(clienteInfra);

        return ResponseEntity.status(201).body(ClienteServico.getClienteDTO(clienteInfra));
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody ClienteDTO cliente){
        var clienteInfra = (Cliente)ClienteServico.buildCliente(cliente, new Cliente());

        if(!repo.existsById(id)) return ResponseEntity.status(404).body(ClienteServico.getClienteNotFound(id));

        clienteInfra.setId(id);
        repo.save(clienteInfra);

        return ResponseEntity.status(201).body(ClienteServico.getClienteDTO(clienteInfra));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> show(@PathVariable int id){
        var clienteOptional = repo.findById(id);

        if(clienteOptional.isEmpty()) return ResponseEntity.status(404).body(ClienteServico.getClienteNotFound(id));

        return ResponseEntity.status(200).body(ClienteServico.getClienteDTO(clienteOptional.get()));
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        if(!repo.existsById(id)) return ResponseEntity.status(404).body(ClienteServico.getClienteNotFound(id));
        repo.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
