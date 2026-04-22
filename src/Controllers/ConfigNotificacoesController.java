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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class ConfigNotificacoesController implements Initializable {

    @FXML
    private VBox vBoxOpcoes;
    @FXML
    private Label lblConta;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnNotificacoes;
    @FXML
    private ImageView imgPerfil;
    @FXML
    private ImageView imgNotificacoes;
    @FXML
    private VBox vBoxLateralConfiguracoes;
    @FXML
    private ImageView imgCoreTrack;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgInicial;
    @FXML
    private Button btnNotificacoesIcon;
    @FXML
    private Button btnGraficos;
    @FXML
    private ImageView imgGraficos;
    @FXML
    private Button btnRelatorios;
    @FXML
    private ImageView imgRelatorios;
    @FXML
    private Button btnSaibaMais;
    @FXML
    private ImageView imgSaibaMais;
    @FXML
    private VBox vBoxConfiguracoesPrincipal;
    @FXML
    private Label lblTitulo;
    @FXML
    private Text txtNotificacoes;
    @FXML
    private HBox hBoxEmail;
    @FXML
    private Button btnAtivar;
    @FXML
    private Button btnDesativar;
    @FXML
    private Label lblNotificacoes;
    @FXML
    private Label lblHabilitacoes;
    @FXML
    private AnchorPane anConfigNotificaoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    private void trocarTela(String caminhoFXML) {
        try {
            Parent novaTela = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage janela = (Stage) anConfigNotificaoes.getScene().getWindow();
            janela.setScene(new Scene(novaTela));
            janela.show();
        } catch (IOException e) {
            System.out.println("Erro ao trocar de tela: " + e.getMessage());
        }
    }
    

    private void btnPerfilAction(MouseEvent event) {
    }

    @FXML
    private void btnPerfilAction(ActionEvent event) {
        trocarTela("/Fxml/Configuracoes.fxml");
    }

    @FXML
    private void btnNotificacoesAction(ActionEvent event) {
        trocarTela("/Fxml/Notificacoes.fxml");
    }

    @FXML
    private void btnHomeAction(ActionEvent event) {
        trocarTela("/coretrackapp/TelaInicial.fxml");
    }

    @FXML
    private void btnNotificacoesIconAction(ActionEvent event) {
        trocarTela("/Fxml/Notificacoes.fxml");
    }

    @FXML
    private void btnGraficosAction(ActionEvent event) {
        trocarTela("/Fxml/Grafico.fxml");
    }

    @FXML
    private void btnRelatoriosAction(ActionEvent event) {
        trocarTela("/Fxml/Relatorios.fxml");
    }

    @FXML
    private void btnSaibaMaisAction(ActionEvent event) {
    }

    @FXML
    private void btnAtivarAction(ActionEvent event) {
    }

    @FXML
    private void btbDesativarAction(ActionEvent event) {
    }
    
}
