package livrarias.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import livrarias.bancoMysql;
import livrarias.classes.Autores;
import livrarias.classes.Editoras;

public class autoresDAO {

    public void Cadastrar(Autores e) {
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("insert into autores(nome,endereco,numero,bairro,cidade,cpf)values (?,?,?,?,?,?)");
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getEndereco());
            stmt.setInt(3, e.getNumero());
            stmt.setString(4, e.getBairro());
            stmt.setString(5, e.getCidade());
            stmt.setBigDecimal(6, new BigDecimal(e.getCPF()));

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "autor cadastrado com sucesso!");
        } catch (SQLException ex) {

        }
    }
    
    public List<Autores> listar(){
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Autores> Autores = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select * from autores");
             rs = stmt.executeQuery();
        
        while(rs.next()){
           Autores a = new Autores();
            a.setId(rs.getInt("id_autores"));
            a.setNome(rs.getString("nome"));
            
           Autores.add(a);
        }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Autores;
        
        
    }
}
