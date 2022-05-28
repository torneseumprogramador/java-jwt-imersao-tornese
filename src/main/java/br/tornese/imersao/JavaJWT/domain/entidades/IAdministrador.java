package br.tornese.imersao.JavaJWT.domain.entidades;

public interface IAdministrador {
    String getEmail();
    void setEmail(String email);

    String getSenha();
    void setSenha(String senha);

    String getRegra();
    void setRegra(String regra);
}
