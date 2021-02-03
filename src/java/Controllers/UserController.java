package Controllers;

import DAO.ConexaoBanco;
import DAO.UserDAO;
import Models.User;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;

public  class UserController {

    static ArrayList<User> listaUsuario = new ArrayList();
    
    static public String CriarUsuarioBanco(String body){
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        boolean resposta = UserDAO.InserirUsuarioBanco(user);
        if(resposta == true){
            return body;
        }else {
            return "NÃO FOI POSSÍVEL CRIAR O USUÁRIO";    
        }
    }
    
    static public String ObterTodosUsuarios(){
       ArrayList<User> listaUsuarioBanco = UserDAO.BuscarTodosUsuario(); 
       String resultado = new Gson().toJson(listaUsuarioBanco);
       return resultado;
    }
    
    static public String ObterUsuarioPorId(int id) {
       User user = UserDAO.BuscarUsuarioPorId(id);
       String resultado = new Gson().toJson(user);
       if(user == null){
           return "NÃO FOI POSSÍVEL ENCONTRAR O USUÁRIO";
       }
       return resultado;
    }
    
    static public String EditarUsuarioPorId(int id, String body){
        Gson gson = new Gson();
        
        try{
            // BUSCO USUÁRIO POR ID
            User user = listaUsuario.get(id);
            
            //PEGA USUARIO QUE VEIO PELO CORPO
            User userBody = gson.fromJson(body, User.class);
            
            if(userBody.getNome() != null){
                user.setNome(userBody.getNome());
            }
            
            if(userBody.getIdade() != 0){
                user.setIdade(userBody.getIdade());
            }
            
            String resultado = gson.toJson(user);
            return resultado;
        }catch(Exception e){
             return "Usuario não encontrado";
        }
    }
    
    static public String DeletarUsuario(int id){
        try{
            listaUsuario.remove(id);
        }catch(Exception e){
            return "USUARIO NÃO ENCONTRADO";
        }
        return "USUARIO DELETADO COM SUCESSO";
    }
}
