/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import Classes.Usuarios;
import DAOs.UsuariosDAO;
import java.io.ByteArrayInputStream;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author maria
 */
public class CarregarPerfil {
    // Método de carregar dados
    public static Usuarios carregar(Label lblNomeConfig, Label lblEmailConfig, ImageView imgPerfil){
        // Pega o ID no txt
        int id = SessaoUsuario.carregarId();
        
        // Busca usuário no banco
        Usuarios usuario = new UsuariosDAO().buscarPorId(id);
        
        // Se tiver encontrado
        if(usuario != null){
            
            // Atualiza o nome
            lblNomeConfig.setText(usuario.getNome());
            
            // Atualiza e-mail
            lblEmailConfig.setText(usuario.getEmail());
            
            // Atualiza a foto
            if(imgPerfil != null){
                
                // Se tem foto salva
                if(usuario.getFotoPerfil() != null){
                    
                    // Converte os bytes
                    imgPerfil.setImage(new Image(
                            new ByteArrayInputStream(usuario.getFotoPerfil())
                    ));
                }else{
                    
                    // Se não tiver foto
                    imgPerfil.setImage(new Image(
                            CarregarPerfil.class.getResourceAsStream("/imagens/AvatarPerfil.png")
                    ));
                }
            }
        }
        
        return usuario;
    }
}
