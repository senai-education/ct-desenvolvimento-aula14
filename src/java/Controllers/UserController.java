package Controllers;

import DAO.ConexaoBanco;
import Models.User;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;

public  class UserController {

    static ArrayList<User> listaUsuario = new ArrayList();
    
    static public String CriarUsuarioBanco(String body){
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        listaUsuario.add(user);
        Connection con = ConexaoBanco.CriaConexao();
        return body;
    }
    
    static public String ObterTodosUsuarios(){
       Gson gson = new Gson();
       String resultado = gson.toJson(listaUsuario);
       return resultado;
    }
    
    static public String ObterUsuarioPorId(int id) {
        Gson gson = new Gson();
        try{
            User user = listaUsuario.get(id);
            String resultado = gson.toJson(user);
            return resultado;
        }catch(Exception e){
             return "Usuario não encontrado";
        }
    }
    
    static public String EditarUsuarioPorId(int id, String body){
        Gson gson = new Gson();
        
        try{
            // BUSCO USUÁRIO POR ID
            User user = listaUsuario.get(id);
            
            //PEGA USUARIO QUE VEIO PELO CORPO
            User userBody = gson.fromJson(body, User.class);
            System.out.println(userBody.getNome());
            System.out.println(userBody.getIdade());
            
            if(userBody.getNome() != null){
                user.setNome(userBody.getNome());
            }
            
            if(userBody.getIdade() != 0){
                user.setIdade(userBody.getIdade());
            }
            
//            listaUsuario.set(id, user);
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
