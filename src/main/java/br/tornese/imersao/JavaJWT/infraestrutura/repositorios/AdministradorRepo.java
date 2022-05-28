package br.tornese.imersao.JavaJWT.infraestrutura.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.tornese.imersao.JavaJWT.infraestrutura.entidades.Administrador;

public interface AdministradorRepo extends CrudRepository<Administrador, Integer> {
    
  @Query(value="select * from administradores where email = :email", nativeQuery = true)
  public Administrador getByEmail(String email);
}
