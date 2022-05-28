package br.tornese.imersao.JavaJWT.infraestrutura.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.tornese.imersao.JavaJWT.infraestrutura.entidades.Cliente;

public interface ClienteRepo extends CrudRepository<Cliente, Integer> {
    
}
