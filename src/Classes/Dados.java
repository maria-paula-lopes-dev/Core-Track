/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author maria
 */
public class Dados {

    private String nome;
    private String email;
    private String fotoPerfil; // caminho do arquivo da foto

    // ----- Construtor vazio -----
    public Dados() {
    }
    
    // ----- Construtor completo -----
    public Dados(String nome, String email, String fotoPerfil) {
        this.nome = nome;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
    }

    // ----- Getters e Setters -----
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}

