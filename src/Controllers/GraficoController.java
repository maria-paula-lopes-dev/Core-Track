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
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class GraficoController implements Initializable {

    @FXML
    private AnchorPane anGraficos;
    @FXML
    private HBox hBoxGraficos;
    @FXML
    private VBox vBoxLateralGraficos;
    @FXML
    private GridPane grdCoreTrack;
    @FXML
    private ImageView imgCoreTrack;
    @FXML
    private Label lblCoreTrack;
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
    private Button btnRelatorios;
    @FXML
    private Button btnVoltar;
    @FXML
    private VBox vBoxGraficos;
    @FXML
    private Label lblTitulo;
    @FXML
    private VBox vBoxGraficoBr;
    @FXML
    private ImageView imgRelatorios;
    @FXML
    private BarChart<?, ?> grfBarras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    private void trocarTela(String fxmlPath){
        
        try{
            // 1 >> Carrega o FXML
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            
            // 2 >> Janela atual
            Stage stage = (Stage) anGraficos.getScene().getWindow();
            
            // 3 >> Troca o conteúdo da janela pela nova tela
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            // 4 >> Mostra nova cena
            stage.show();
            
        }catch(IOException e){
            System.out.println("Erro ao abrir tela: " + e.getMessage());
        }
    }
}
