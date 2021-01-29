package Controllers;

import Models.User;
import com.google.gson.Gson;
import java.util.ArrayList;

public  class UserController {

    static ArrayList<User> listaUsuario = new ArrayList();
    
    static public String CriarUsuarioBanco(String body){
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        listaUsuario.add(user);
        return body;
    }
    
    static public String ObterTodosUsuarios(){
       Gson gson = new Gson();
       String resultado = gson.toJson(listaUsuario);
       return resultado;
    }
}
