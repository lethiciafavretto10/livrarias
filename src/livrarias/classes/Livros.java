/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrarias.classes;

import java.sql.Date;

/**
 *
 * @author lethicia.favretto
 */
public class Livros {
  private int id_livros;
private String titulo;
private int ano;
 private Editoras editora;
 private Autores autor;

    public int getId_livros() {
        return id_livros;
    }

    public void setId_livros(int id_livros) {
        this.id_livros = id_livros;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Editoras getEditora() {
        return editora;
    }

    public void setEditora(Editoras editora) {
        this.editora = editora;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    
    
 
    }

