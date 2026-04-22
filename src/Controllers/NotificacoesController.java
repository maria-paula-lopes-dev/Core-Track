/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Classes.Notificacoes;
import DAOs.NotificacoesDAO;
import coretrackapp.Globais;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class NotificacoesController implements Initializable {

    @FXML
    private AnchorPane anNotificacoes;
    @FXML
    private HBox hBoxNotificacoes;
    @FXML
    private VBox vBoxLateralNotificacoes;
    @FXML
    private GridPane grdCoreTrack;
    @FXML
    private Label lblCoreTrack;
    @FXML
    private ImageView imgCoreTrack;
    @FXML
    private ImageView imgPerfil;
    @FXML
    private GridPane grdBotoesPrincipais;
    @FXML
    private ImageView imgConfiguracoes;
    @FXML
    private ImageView imgNotificacoes;
    @FXML
    private ImageView imgSaibaMais;
    @FXML
    private Button btnConfiguracoes;
    @FXML
    private Button btnNotificacoes;
    @FXML
    private Button btnSaibaMais;
    @FXML
    private ImageView imgGraficos;
    @FXML
    private Button btnGraficos;
    @FXML
    private ImageView imgRelatorios;
    @FXML
    private Button btnRelatorios;
    @FXML
    private Button btnVoltar;
    @FXML
    private VBox vBoxNotificacoesPrincipal;
    @FXML
    private Label lblTitulo;
    @FXML
    private HBox hBoxOpcoes;
    @FXML
    private VBox vBoxNotificacoes;
    @FXML
    private ListView<Notificacoes> lstNotificacoes;
    @FXML
    private MenuButton menuNotificacoes;
    @FXML
    private MenuItem miCriticas;
    @FXML
    private MenuItem miAlertas;
    @FXML
    private MenuItem miSujestivas;
    
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnConfiguracoesAction(ActionEvent event) {
        trocarTela("/Fxml/Configuracoes.fxml");
    }

    @FXML
    private void btnNotificacoesAction(ActionEvent event) {
        trocarTela("/Fxml/Notificacoes.fxml");
    }

    @FXML
    private void btnSaibaMaisAction(ActionEvent event) {
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
    private void btnVoltarAction(ActionEvent event) {
        trocarTela("/coretrackapp/TelaInicial.fxml");
    }

    @FXML
    private void menuNotificacoesAction(ActionEvent event) {
    }

    private void trocarTela(String fxmlPath) {

        try {
            // 1 >> Carrega o FXML
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

            // 2 >> Janela atual
            Stage stage = (Stage) anNotificacoes.getScene().getWindow();

            // 3 >> Troca o conteúdo da janela pela nova tela
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // 4 >> Mostra nova cena
            stage.show();

        } catch (IOException e) {
            System.out.println("Erro ao abrir tela: " + e.getMessage());
        }
    }

    @FXML
    private void miCriticasAction(ActionEvent event) {
        menuNotificacoes.setText("Críticas");

        NotificacoesDAO dao = new NotificacoesDAO();

        ObservableList<Notificacoes> lista = dao.listarPorUsuario(Globais.getId_usuario(), "CRITICO");

        lstNotificacoes.setItems(lista);
    }

    @FXML
    private void miAlertasAction(ActionEvent event) {
        menuNotificacoes.setText("Alertas");

        NotificacoesDAO dao = new NotificacoesDAO();

        ObservableList<Notificacoes> lista = dao.listarPorUsuario(Globais.getId_usuario(), "ALERTA");

        lstNotificacoes.setItems(lista);
    }

    
    @FXML
    private void miSujestivasAction(ActionEvent event) {
        Utilitarios.Avisos.msgInformacao("Não dísponível no momento", 2);
    }

}
