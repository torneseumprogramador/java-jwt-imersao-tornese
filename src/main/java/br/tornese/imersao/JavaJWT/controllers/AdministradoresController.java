package br.tornese.imersao.JavaJWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.tornese.imersao.JavaJWT.infraestrutura.entidades.Administrador;
import br.tornese.imersao.JavaJWT.infraestrutura.repositorios.AdministradorRepo;
import br.tornese.imersao.JavaJWT.infraestrutura.seguranca.Cripto;

@CrossOrigin("*")
@RestController
public class AdministradoresController {

    @Autowired
    private AdministradorRepo repo;
  
    @PostMapping("/administradores")
    public ResponseEntity<Administrador> create(@RequestBody Administrador adm) throws Exception{
      String senhaCripto = new Cripto().build(adm.getSenha());
      adm.setSenha(senhaCripto);
      repo.save(adm);
      return ResponseEntity.status(201).body(adm);
    }

    @GetMapping("/administradores")
    public ResponseEntity<Iterable<Administrador>> index(){
      return ResponseEntity.status(200).body(repo.findAll());
    }
  
    @GetMapping("/gerar-adm")
    public Administrador gerarAdm() throws Exception{
      var email = "danilo@torneseumprogramador.com.br";
      Administrador admExistente = repo.getByEmail(email);
      if(admExistente != null) return admExistente;
  
      Administrador adm = new Administrador();
      adm.setEmail(email);
      adm.setRegra("ADM");
      String senhaCripto = new Cripto().build("123456");
      adm.setSenha(senhaCripto);
      repo.save(adm);
      
      return adm;
    }
  }