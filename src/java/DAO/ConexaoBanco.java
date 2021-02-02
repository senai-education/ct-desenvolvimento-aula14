package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBanco {
    
    private static final String DRIVER = "org.postgresql.Driver";    
    private static final String USUARIO = "postgres"; 
    private static final String SENHA = "postgresql";  
    private static final String URL = "jdbc:postgresql://localhost:5432/webapi";  
    private static Connection conexao = null;
    
    
    public static Connection CriaConexao() {
        if(conexao == null){
            try {
                // DRIVER QUE VAMOS UTILIZAR
                Class.forName(DRIVER);
                // CRIAR A CONEXÃO
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA); // EXCEPTION 
                System.out.println("Conexão feixa com sucesso");
            }catch(Exception e){
                System.out.println("Problema ao conectar no banco: " + e.getMessage());
            }
        }
        return conexao;
    }
}
