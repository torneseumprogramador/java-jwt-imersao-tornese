package br.tornese.imersao.JavaJWT.domain.model_views;

public class LoginError {
    private Exception err;
    public LoginError(Exception e){
        this.err = e;
    }

    public String getMensagem(){
        return "Ocorreu um erro ao gerar a criptografia (" + err.getMessage() + ")";
    }
}
