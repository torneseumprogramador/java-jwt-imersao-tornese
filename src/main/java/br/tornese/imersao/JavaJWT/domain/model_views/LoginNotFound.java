package br.tornese.imersao.JavaJWT.domain.model_views;

public class LoginNotFound {
    private String email;
    public LoginNotFound(String email){
        this.email = email;
    }

    public String getMensagem(){
        return "Email (" + this.email + ") não existe em nossa base de dados";
    }
}
