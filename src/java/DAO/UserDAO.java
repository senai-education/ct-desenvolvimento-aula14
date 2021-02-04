package DAO;

import Utils.ConexaoBanco;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
       
    static public boolean InserirUsuarioBanco(User user) {
        String sql = "INSERT INTO users (nome, idade) VALUES (?,?)";
        
        Connection conexao = ConexaoBanco.CriaConexao();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);    
            stm.setString(1, user.getNome());
            stm.setInt(2, user.getIdade());
            stm.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("NÃO FOI POSSÍVEL EXECUTAR A TAREFA");
            return false;
        }
    }
    
    static public User BuscarUsuarioPorId(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection conexao = ConexaoBanco.CriaConexao();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet retornoBanco = stm.executeQuery();
            if(retornoBanco.next()){
                int idUser = retornoBanco.getInt("id");
                String nome = retornoBanco.getString("nome");
                int idade = retornoBanco.getInt("idade");                
                User user = new User(idUser, nome, idade);
                return user;
            }else {
                return null;
            }
        }catch(Exception e){
            System.out.println("NÃO FOI POSSÍVEL EXECUTAR A TAREFA");
            return null;
        }
    }
    
    static public ArrayList<User> BuscarTodosUsuario(){ 
        String sql = "SELECT * FROM users";
        Connection conexao = ConexaoBanco.CriaConexao();
        ArrayList<User> listaUsuario = new ArrayList<>();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            ResultSet retornoBanco = stm.executeQuery();
            
            // RESULT SET = 3; ID, NOMES, IDADES
            
            while(retornoBanco.next()){
                int id = retornoBanco.getInt("id");
                String nome = retornoBanco.getString("nome");
                int idade = retornoBanco.getInt("idade");
                User user = new User(id, nome, idade);
                listaUsuario.add(user);
            }
            
            return listaUsuario;
            
        }catch(Exception e){
            System.out.println("NÃO FOI POSSÍVEL EXECUTAR A TAREFA");
            return listaUsuario;
        }
    }

    public static String AtualizarUsuarioPorId(int id, User user) {
        String sql = "UPDATE users SET nome = ?, idade = ? WHERE id = ?";
        Connection conexao = ConexaoBanco.CriaConexao();
        
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, user.getNome());
            stm.setInt(2, user.getIdade());
            stm.setInt(3, id);
            stm.executeUpdate();
            return "USUARIO ATUALIZADO COM SUCESSO";
        } catch (Exception ex){
            System.out.println("NÃO FOI POSSÍVEL EXECUTAR A TAREFA");
            return "NÃO FOI POSSÌVEL FAZER UPDATE DO USUARIO";
        }   
    }

    public static String DeletarUsuarioPorId(int id) {
        String sql = "DELETE FROM users WHERE id = ?";           
        Connection conexao = ConexaoBanco.CriaConexao();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            return "USUARIO DELETADO";
        }catch (Exception ex) {
            System.out.println("NÃO FOI POSSÍVEL EXECUTAR A TAREFA");
            return "NÃO FOI POSSÌVEL DELETAR DO USUARIO";  
        }
    }
}
