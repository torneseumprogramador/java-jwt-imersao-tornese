package br.tornese.imersao.JavaJWT.domain.model_views;

public class ClienteNotFound {
    private int id;
    public ClienteNotFound(int id){
        this.id = id;
    }

    public String getMensagem(){
        return "O cliente com o id (" + id + ") não foi encontrado";
    }
}
