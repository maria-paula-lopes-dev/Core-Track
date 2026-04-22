/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Classes.Usuarios;
import DAOs.UsuariosDAO;
import Utilitarios.CarregarPerfil;
import Utilitarios.SessaoUsuario;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class ConfiguracoesController implements Initializable {

    @FXML
    private AnchorPane anConfiguracoes;
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
    private HBox hBoxPerfil;
    @FXML
    private VBox vBoxFotoPerfil;
    @FXML
    private Label lblFotoPrfil;
    @FXML
    private Button btnRemover;
    @FXML
    private HBox hBoxNome;
    @FXML
    private VBox vBoxNome;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblNomeConfig;
    @FXML
    private HBox hBoxEmail;
    @FXML
    private VBox vBoxEmail;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblEmailConfig;
    @FXML
    private Button btnSelecionar;
    @FXML
    private Button btnEditarNome;
    @FXML
    private Button btnEditarEmail;

    private Usuarios usuarioLogado;
    private UsuariosDAO dao = new UsuariosDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // ATUALIZA LABELS E FOTO
        usuarioLogado = CarregarPerfil.carregar(lblNomeConfig, lblEmailConfig, imgPerfil);

        arredondarImagem(imgPerfil);

        // CARREGA OBJETO
        if (usuarioLogado == null) {
            // 1 >> Pega o ID salvo no txt
            int id = SessaoUsuario.carregarId();

            // 2 >> Carrega o usuário do banco -> guarda
            usuarioLogado = dao.buscarPorId(id);
        }

        /**
         * CÓDIGO ANTIGO =============================================
         *
         *
         * // 3 >> Informações na tela // Virifica se o usuário existia no
         * banco // Se sim, preenche os campos do JavaFX if (usuarioLogado !=
         * null) {
         *
         * // Mostra na tela lblNome.setText(usuarioLogado.getNome());
         * lblEmail.setText(usuarioLogado.getEmail());
         *
         * // Verifica se tem foto salva if (usuarioLogado.getFotoPerfil() !=
         * null) {
         *
         * // Transforma os bytes em imagem // Mostra na tela
         * imgPerfil.setImage(new Image(new
         * java.io.ByteArrayInputStream(usuarioLogado.getFotoPerfil()))); } }
         *
         */
    }

    private void trocarTela(String caminhoFXML) {
        try {
            Parent novaTela = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage janela = (Stage) anConfiguracoes.getScene().getWindow();
            janela.setScene(new Scene(novaTela));
            janela.show();
        } catch (IOException e) {
            System.out.println("Erro ao trocar de tela: " + e.getMessage());
        }
    }

    @FXML
    private void btnPerfilAction(ActionEvent event) {
        trocarTela("/Fxml/Configuracoes.fxml");
    }

    @FXML
    private void btnNotificacoesAction(ActionEvent event) {
        trocarTela("/Fxml/ConfigNotificacoes.fxml");
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
        //CRIAR!!
    }

    @FXML
    private void btnRemoverAction(ActionEvent event) {
        // 1 >> Tem que voltar pra imagem padrão
        Image imgPadrao = new Image(getClass().getResourceAsStream("/imagens/AvatarPerfil.png"));
        imgPerfil.setImage(imgPadrao);

        // 2 >> Remover foto do objeto Usuario
        usuarioLogado.setFotoPerfil(null);

        // 3 >> Atualizar no banco
        UsuariosDAO dao = new UsuariosDAO();
        boolean sucesso = dao.atualizarFoto(usuarioLogado.getIdUsuario(), null);

        if (sucesso) {
            mostrarMensagem("Foto removida com sucesso!");
        } else {
            mostrarMensagem("Erro: não foi possível remover a foto.");
        }
    }

    // Usando seletor de arquivos
    @FXML
    private void btnSelecionarAction(ActionEvent event) {
        // 1 >> Declarar um objeto FileChooser
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Selecionar Nova Foto");

        // 2 >> Filtro: Só PNG e JPG
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );

        // 3 >> Chama o método de dialogo
        File arquivo = fileChooser.showOpenDialog(null);

        // 4 >> Verificação
        if (arquivo == null) {
            return; // usuário cancelou
        }

        try {

            byte[] bytes = java.nio.file.Files.readAllBytes(arquivo.toPath());

            // Atualiza imagem na tela
            imgPerfil.setImage(new Image(arquivo.toURI().toString()));

            arredondarImagem(imgPerfil);

            // Atualiza no objeto
            usuarioLogado.setFotoPerfil(bytes);

            // Salva no banco
            dao.atualizar(usuarioLogado);

            mostrarMensagem("Foto atualizada com sucesso!");

        } catch (IOException e) {
            mostrarMensagem("Erro ao carregar a foto");
        }

    }

    @FXML
    private void btnEditarNomeAction(ActionEvent event) {
        // 1 >> Caixa de dialogo
        TextInputDialog dialog = new TextInputDialog(usuarioLogado.getNome());
        dialog.setTitle("Editar Nome");
        dialog.setHeaderText("Digite o novo nome: ");
        dialog.setContentText("Nome: ");

        // Mostra a janela e espera o usuário digitar algo
        // O valor de retorno pode existir ou não
        Optional<String> result = dialog.showAndWait();

        // Se o usuário clicou em OK, entra no bloco
        if (result.isPresent()) {

            // Recupera o nome digitado
            String novoNome = result.get();

            // Atualiza nome exibido
            lblNomeConfig.setText(novoNome);
            usuarioLogado.setNome(novoNome);

            // Atualiza o objeto da memória
            dao.atualizar(usuarioLogado);

            // Informao sucesso
            mostrarMensagem("Nome atualizado com sucesso!");
        }
    }

    @FXML
    private void btnEditarEmailAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(usuarioLogado.getEmail());
        dialog.setTitle("Editar E-mail");
        dialog.setHeaderText("Digite o novo e-mail: ");
        dialog.setContentText("E-mail");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String novoEmail = result.get();

            lblEmailConfig.setText(novoEmail);
            usuarioLogado.setEmail(novoEmail);

            dao.atualizar(usuarioLogado);

            mostrarMensagem("E-mail atualizado com sucesso!");
        }
    }

    private void mostrarMensagem(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    // Deixa a imagem do ImageView redonda
    private void arredondarImagem(ImageView img) {
        double tamanho = 120; // escolha o tamanho que preferir

        img.setFitWidth(tamanho);
        img.setFitHeight(tamanho);

        javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(
                tamanho / 2,
                tamanho / 2,
                tamanho / 2
        );

        img.setClip(clip);
    }

}
