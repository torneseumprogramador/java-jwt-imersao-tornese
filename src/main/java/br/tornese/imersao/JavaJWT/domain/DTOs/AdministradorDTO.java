package br.tornese.imersao.JavaJWT.domain.DTOs;

public class AdministradorDTO {
    public AdministradorDTO(String email, String regra, String token){
        this.email = email;
        this.regra = regra;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    private String email;
    private String regra;
    private String token;
    
    public String getEmail() {
        return email;
    }
    public String getRegra() {
        return regra;
    }
    public void setRegra(String regra) {
        this.regra = regra;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
