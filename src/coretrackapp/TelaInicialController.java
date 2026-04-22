package coretrackapp;

import Runnables.NotificadorDesempenho;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Runnables.Verificador;

/**
 *
 * @author maria
 */
public class TelaInicialController implements Initializable {

    private Label label;
    @FXML
    private AnchorPane anPrincipal;
    @FXML
    private HBox hBoxPrincipal;
    @FXML
    private VBox vBoxLateral;
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
    private VBox vBoxComponentes;
    @FXML
    private Label lblTitulo;
    @FXML
    private HBox hBoxPrimeirosComponentes;
    @FXML
    private HBox hBoxCPU;
    @FXML
    private ImageView imgCPU;
    @FXML
    private VBox vBoxCPU;
    @FXML
    private Label lblCPU;
    @FXML
    private ProgressBar progressoCPU;
    @FXML
    private HBox hBoxRAM;
    @FXML
    private ImageView imgRAM;
    @FXML
    private VBox vBoxRAM;
    @FXML
    private Label lblRAM;
    @FXML
    private ProgressBar progressoRAM;
    @FXML
    private HBox hBoxHD;
    @FXML
    private ImageView imgHD;
    @FXML
    private VBox vBoxHD;
    @FXML
    private Label lblHd;
    @FXML
    private ProgressBar progressoHD;
    @FXML
    private VBox vBoxDesempenho;
    @FXML
    private ImageView imgVerificado;
    @FXML
    private Label lblMensagem;
    @FXML
    private ImageView imgGraficos;
    @FXML
    private Button btnGraficos;
    @FXML
    private Button btnRelatorios;
    @FXML
    private ImageView imgRelatorios;
    @FXML
    private HBox hBoxOutrasfuncionalidades;
    @FXML
    private Button btnTempodeUso;
    @FXML
    private Button btnTemperatura;
    private Stage stage;
    @FXML
    private Label lblPorcentagemCPU;
    @FXML
    private Label lblPorcentagemHD;
    @FXML
    private Label lblPorcentagemRAM;
    public static TelaInicialController instancia; // referência global
    static Thread iniciarVerificador, iniciarNotificador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilitarios.CarregarPerfil.carregar(lblCoreTrack, lblTitulo, imgPerfil);
        coretrackapp.Globais.setController(this);
        instancia = this;
        if (iniciarVerificador == null || !iniciarVerificador.isAlive()) {
            Verificador verificar = new Verificador((() -> TelaInicialController.instancia));
            NotificadorDesempenho notificar = new NotificadorDesempenho();
            iniciarVerificador = new Thread(verificar);
            iniciarVerificador.setDaemon(false); // mudar para false na versão final
            iniciarVerificador.start();
            iniciarNotificador = new Thread(notificar);
            iniciarNotificador.setDaemon(false);
            iniciarNotificador.start();
        } 
    }
    /*
     public boolean janelaAberta() {
     return stage != null && stage.isShowing();
     }

     public void atualizarLabel(int valor) {
     if (janelaAberta()) {
     Platform.runLater(() ->
     lblcont.setText(String.valueOf(valor))
     );
     */

    // Método de troca de tela

    /**
     * Ele troca o que está aparecendo na tela Carregando uma arquivo fxml e
     * exibindo no lugar da tela atual
     *
     */
    private void trocarTela(String fxmlPath) {

        try {
            // 1 >> Carrega o FXML
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

            // 2 >> Janela atual
            Stage stage = (Stage) anPrincipal.getScene().getWindow();

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
    private void btnConfiguracoesAction(ActionEvent event) {
        trocarTela("/Fxml/Configuracoes.fxml");
    }

    @FXML
    private void btnNotificacoesAction(ActionEvent event) {
        trocarTela("/Fxml/Notificacoes.fxml");
    }

    @FXML
    private void btnSaibaMaisAction(ActionEvent event) {
        //CRIAR!!
        Utilitarios.Avisos.msgInformacao("Não dísponível no momento", 2);
    }

    @FXML
    private void btnGraficosAction(ActionEvent event) {
        Utilitarios.Avisos.msgInformacao("Não dísponível no momento", 2);
    }

    @FXML
    private void btnRelatoriosAction(ActionEvent event) {
        trocarTela("/Fxml/Relatorios.fxml");
    }

    @FXML
    private void btnTempodeUsoAction(ActionEvent event) {
    }

    @FXML
    private void btnTemperaturaAction(ActionEvent event) {
    }

    @FXML
    private void imgCPUCliked(MouseEvent event) {
    }

    @FXML
    private void imgRAMCliked(MouseEvent event) {
    }

    @FXML
    private void imgHDCliked(MouseEvent event) {
    }

    public void setStage(Stage stage) {// usando para garantir que o Verificador só muda as labels quando a Tela Principal estiver aberta
        this.stage = stage;
    }

    public boolean JanelaAberta() {return anPrincipal != null
        && anPrincipal.getScene() != null
        && anPrincipal.getScene().getWindow() != null
        && anPrincipal.getScene().getWindow().isShowing();

    }

    public void atualizarStatus(String CPUp, double CPU, String RAMp, double RAM, String HDp, double HD, String tempLigado, String temperatura) {
        lblPorcentagemCPU.setText(CPUp + "%");
        progressoCPU.setProgress(CPU);
        lblPorcentagemRAM.setText(RAMp);
        progressoRAM.setProgress(RAM);
        lblPorcentagemHD.setText(HDp);
        progressoHD.setProgress(HD);
        btnTempodeUso.setText(tempLigado);
        btnTemperatura.setText(temperatura);
    }

}
