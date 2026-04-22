/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import coretrackapp.Globais;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class RelatoriosController implements Initializable {

    @FXML
    private AnchorPane anRelatorios;
    @FXML
    private Label lblTitulo;
    @FXML
    private HBox hBoxPrincipalRelatorios;
    @FXML
    private ListView<?> lstRelatorios; // não operante, apenas relatórios usando dados desde o início do programa
    @FXML
    private TextArea txtRelatorio;
    @FXML
    private HBox hBoxBotoesRelatorio;
    @FXML
    private Button btnVisualizar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnVoltar;
    @FXML
    private ImageView imgX;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnVisualizarction(ActionEvent event) {
        txtRelatorio.setText(Globais.getRelatorio());
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        Utilitarios.Avisos.msgInformacao("Não dísponível no momento", 2);
    }

    @FXML
    private void btnVoltarAction(ActionEvent event) {
        trocarTela("/coretrackapp/TelaInicial.fxml");
    }
    
    
    private void trocarTela(String fxmlPath){
        
        try{
            // 1 >> Carrega o FXML
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            
            // 2 >> Janela atual
            Stage stage = (Stage) anRelatorios.getScene().getWindow();
            
            // 3 >> Troca o conteúdo da janela pela nova tela
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            // 4 >> Mostra nova cena
            stage.show();
            
        }catch(IOException e){
            System.out.println("Erro ao abrir tela: " + e.getMessage());
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        Utilitarios.Avisos.msgInformacao("Não dísponível no momento", 2);
    }
    
}
