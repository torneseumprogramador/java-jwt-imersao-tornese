package br.tornese.imersao.JavaJWT.infraestrutura.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.tornese.imersao.JavaJWT.domain.entidades.IAdministrador;

@Entity
@Table(name="administradores")
public class Administrador implements IAdministrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String email;
    private String senha;
    private String regra;
    
    public int getId() {
        return id;
    }
    public String getRegra() {
        return regra;
    }
    public void setRegra(String regra) {
        this.regra = regra;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
}
