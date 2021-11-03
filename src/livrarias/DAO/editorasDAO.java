
package livrarias.DAO;

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
import livrarias.classes.Editoras;



public class editorasDAO {

    public void cadastrar(Editoras e){
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("INSERT INTO editoras (nome) values (?)");
            
            stmt.setString(1,e.getNome() );
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Editora cadastrada com sucesso!");
            
        } catch (SQLException ex) {
            
    }
        
}
    public List<Editoras> listar(){
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Editoras> Editoras = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select * from editoras");
             rs = stmt.executeQuery();
        
        while(rs.next()){
            Editoras e = new Editoras();
            e.setId(rs.getInt("id_editoras"));
            e.setNome(rs.getString("nome"));
            
            Editoras.add(e);
        }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Editoras;
        
        
    }
    public void alterar(Editoras e){
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("UPDATE editoras set nome = ? where id_editoras = ?");
            stmt.setString(1,e.getNome() );
            stmt.setInt(2, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Editora alterada com sucesso!");
            
        } catch (SQLException ex) {
            
    }
    }
      public void excluir(Editoras e) {
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement("DELETE FROM editoras where id_editoras = ?");
            stmt.setInt(1, e.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Editora excluida com sucesso!");
            
        } catch (SQLException ex) {
            
    }     
}
     public List<Editoras>pesquisar(String texto){
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Editoras> Editoras = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select * from editoras where nome like ? ");
            stmt.setString(1,"%" + texto + "%");
             rs = stmt.executeQuery();
        
        while(rs.next()){
            Editoras e = new Editoras();
            e.setId(rs.getInt("id_editoras"));
            e.setNome(rs.getString("nome"));
            
            Editoras.add(e);
        }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return Editoras;
            
  
}
}

