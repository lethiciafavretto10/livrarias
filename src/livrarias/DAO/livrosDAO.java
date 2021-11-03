/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import livrarias.classes.Autores;
import livrarias.classes.Editoras;
import livrarias.classes.Livros;

/**
 *
 * @author lethicia.favretto
 */
public class livrosDAO {

    public void cadastrar(Livros l) {
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO livros (id_editoras,id_autores,titulo,ano) VALUES (?,?,?,?)");
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, " Livro cadastrado com sucesso!");

        } catch (SQLException ex) {

        }

    }

    public void alterar(Livros l) {
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE livros set id_editoras = ?,id_autores = ?,titulo = ?,ano =? where id_livros = ?");
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());
            stmt.setInt(5, l.getId_livros());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro alterado com sucesso!");

        } catch (SQLException ex) {

        }
    }

    public List<Livros> listar() {
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Livros> Livros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select livros.id_livros,editoras.nome as nome_editora, autores.nome as nome_autores,livros.titulo,livros.ano  from livros\n"
                    + "inner join editoras on (editoras.id_editoras = livros.id_editoras)\n"
                    + "inner join autores on (autores.id_autores = livros.id_autores);");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livros l = new Livros();
                l.setId_livros(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                Editoras e = new Editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);

                Autores a = new Autores();
                a.setNome(rs.getString("nome_autores"));
                l.setAutor(a);

                Livros.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Livros;

    }

    public void excluir(Livros l) {
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM livros where id_livros= ?");
            stmt.setInt(1, l.getId_livros());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livro excluido com sucesso!");

        } catch (SQLException ex) {

        }
    }

    public List<Livros> pesquisar(String texto) {
        Connection conn = bancoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Livros> livros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select livros.id_livros,editoras.nome as nome_editora, autores.nome as nome_autores,livros.titulo,livros.ano  from livros\n"
                    + "inner join editoras on (editoras.id_editoras = livros.id_editoras)\n"
                    + "inner join autores on (autores.id_autores = livros.id_autores) where titulo like ? ");
            stmt.setString(1, "%" + texto + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livros l = new Livros();
                l.setId_livros(rs.getInt("id_livros"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));
                
                Editoras e = new Editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);
                
                Autores a = new Autores();
                a.setNome(rs.getString("nome_autores"));
                l.setAutor(a);

                livros.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;

    }
}

