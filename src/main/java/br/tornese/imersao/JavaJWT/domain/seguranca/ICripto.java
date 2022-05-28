package br.tornese.imersao.JavaJWT.domain.seguranca;

public interface ICripto {
    String build(String senha) throws Exception;
}
