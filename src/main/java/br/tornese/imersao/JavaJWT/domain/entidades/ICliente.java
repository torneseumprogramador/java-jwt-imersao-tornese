package br.tornese.imersao.JavaJWT.domain.entidades;

public interface ICliente {
    int getId();
    void setId(int id);

    String getNome();
    void setNome(String nome);

    String getEmail();
    void setEmail(String email);

    String getTelefone();
    void setTelefone(String telefone);
}
