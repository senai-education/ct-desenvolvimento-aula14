package Services;

import Controllers.UserController;
import Models.User;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserService {

    //GET - http://localhost:8080/user -> ObterTodosUsuarios();
    //POST - http://localhost:8080/user -> CriarUsuario();
    //GET - http://localhost:8080/user/123 -> ObterUsuarioPorId();    
    //PUT - http://localhost:8080/user/123 -> ObterTodosUsuarios();
    //DELETE - http://localhost:8080/user/123 -> DeletarUsuario();
    
    @Context
    private UriInfo context;

    public UserService() {
    }
    
    @POST
    @Consumes("application/json")
    public String CriarUsuario(String content) {        
       return UserController.CriarUsuarioBanco(content);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String ObterTodosUsuario() {
        return UserController.ObterTodosUsuarios();
    }
   
    @GET
    @Path("{id}/")
    @Produces("application/json")
    public String ObterUsuarioPorId(@PathParam("id") int id) {
        return "CHEGANDO NA ROTA GET OBTER USUÁRIO COM ID " + id + "COM NOME: "  ;
    } 
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public String EditarUsuario(@PathParam("id") int id) {
        return "EDITAR USUÁRIO COM ID " + id;
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public String DeletarUsuario(@PathParam("id") int id) {
        return "DELETAR USUÁRIO COM ID " + id;
    }

}
