package br.tornese.imersao.JavaJWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.tornese.imersao.JavaJWT.domain.DTOs.AdministradorDTO;
import br.tornese.imersao.JavaJWT.domain.model_views.LoginError;
import br.tornese.imersao.JavaJWT.domain.model_views.LoginNotFound;
import br.tornese.imersao.JavaJWT.domain.model_views.LoginUnauthorized;
import br.tornese.imersao.JavaJWT.infraestrutura.entidades.Administrador;
import br.tornese.imersao.JavaJWT.infraestrutura.repositorios.AdministradorRepo;
import br.tornese.imersao.JavaJWT.infraestrutura.seguranca.Cripto;
import br.tornese.imersao.JavaJWT.infraestrutura.seguranca.JwtToken;

@CrossOrigin("*")
@RestController
public class LoginController {
    @Autowired
    private AdministradorRepo repo;

    @PostMapping("/login")
    public ResponseEntity<Object> index(@RequestBody Administrador adm){

        Administrador administrador  = repo.getByEmail(adm.getEmail());
        if(administrador == null) return ResponseEntity.status(404).body(new LoginNotFound(adm.getEmail()));

        var cripto = new Cripto();
        String senhaCriptoAdm;
        try{
            senhaCriptoAdm = cripto.build(adm.getSenha());
        }
        catch(Exception e){
            return ResponseEntity.status(401).body(new LoginError(e));
        }

        if(!administrador.getSenha().equals(senhaCriptoAdm)) return ResponseEntity.status(401).body(new LoginUnauthorized());
        
        var token = new JwtToken().build(administrador);
        var administradorDTO = new AdministradorDTO(administrador.getEmail(), administrador.getRegra(), token);
        return ResponseEntity.status(200).body(administradorDTO);
    }
}
