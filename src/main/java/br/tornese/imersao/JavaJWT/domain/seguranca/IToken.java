package br.tornese.imersao.JavaJWT.domain.seguranca;

import br.tornese.imersao.JavaJWT.domain.entidades.IAdministrador;

public interface IToken {
    String build(IAdministrador administrador);
}
