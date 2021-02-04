package Controllers;

import Utils.ConexaoBanco;
import DAO.UserDAO;
import Models.User;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public  class UserController {

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
       ArrayList<User> listaUsuarioBanco = UserDAO.BuscarTodosUsuario(); ;;
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
        User userVeioCorpo = gson.fromJson(body, User.class);        
        User userBanco = UserDAO.BuscarUsuarioPorId(id);
        
        if(userVeioCorpo.getNome() != null){
            userBanco.setNome(userVeioCorpo.getNome());
        }
        
        if(userVeioCorpo.getIdade() != 0){
            userBanco.setIdade(userVeioCorpo.getIdade());
        }
        
        String response = UserDAO.AtualizarUsuarioPorId(id, userBanco);
        return response;        
    }
    
    static public String DeletarUsuario(int id){
        String response = UserDAO.DeletarUsuarioPorId(id);
        double testes2 = new Gson().fromJson("testse", Double.class);
        return response;
    }
}
