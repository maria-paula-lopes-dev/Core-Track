package Controllers;

import Classes.Usuarios;
import DAOs.UsuariosDAO;
import Utilitarios.SessaoUsuario;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DadosController implements Initializable {

    @FXML
    private AnchorPane anDados;
    @FXML
    private VBox vBoxPrincipalDados;
    @FXML
    private ImageView imgDadosIcon;
    @FXML
    private Label lblBemVindo;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnEnviar;
    @FXML
    private VBox vBoxLogo;
    @FXML
    private ImageView imgDados;
    @FXML
    private Button btnProsseguir;

    // IDENTIFICANDO O USUÁRIO DENTRO DO SISTEMA
    private int idUsuario = -1; // -> -1 porque não tem nenhum criado ainda

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // ============================================================
    //  BOTÃO ENVIAR (salva o usuário no banco)
    // ****************************************
    // 1) Salva no banco (UsuáriosDAO.inserir)
    // 2) Busca o ID desse usuário
    // 3) Salva no arquivo txt → SessaoUsuario.salvarId(id)
    // 4) Libera o botão “Prosseguir”
    // ============================================================
    @FXML
    private void btnEnviarAction(ActionEvent event) {
        // 1 >> Pegando os dados digitados
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();

        // 2 >> Validação básica
        if (nome.isEmpty() || email.isEmpty()) {
            lblBemVindo.setText("Preencha todos os campos!");
            return;
        }

        UsuariosDAO dao = new UsuariosDAO();

        // 3 >> Verifica se o usuário já existe pelo email
        Usuarios usuarioBD = dao.buscarPorEmail(email);

        if (usuarioBD != null) {
            // Usuário já existe no banco
            idUsuario = usuarioBD.getIdUsuario();
            SessaoUsuario.salvarId(idUsuario);
            lblBemVindo.setText("Usuário já cadastrado! Pode prosseguir.");
        } else {
            // 4 >> Criar novo objeto usuário
            Usuarios u = new Usuarios();
            u.setNome(nome);
            u.setEmail(email);
            u.setFotoPerfil(new byte[0]); // padrão sem foto
            u.setAdmin(false);             // padrão não admin

            // 5 >> Inserir no banco
            boolean sucesso = dao.inserir(u);

            if (sucesso) {
                // Buscar novamente para obter o ID gerado pelo banco
                usuarioBD = dao.buscarPorEmail(email);
                if (usuarioBD != null) {
                    idUsuario = usuarioBD.getIdUsuario();
                    SessaoUsuario.salvarId(idUsuario);
                    lblBemVindo.setText("Dados salvos! Agora você pode prosseguir.");
                } else {
                    lblBemVindo.setText("Erro ao recuperar ID do usuário.");
                }
            } else {
                lblBemVindo.setText("Erro ao salvar no banco!");
            }
        }
    }

    // ============================================================
    //  BOTÃO PROSSEGUIR (vai para a tela principal do app)
    // ============================================================
    @FXML
    private void btnProsseguirAction(ActionEvent event) {
        // Verifica se o usuário já enviou os dados
        if (idUsuario == -1) {
            lblBemVindo.setText("Envie os dados antes de prosseguir!");
            return;
        }

        try {/*
            Parent root = FXMLLoader.load(getClass().getResource("/coretrackapp/TelaInicial.fxml"));
            Stage stage = (Stage) btnProsseguir.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);   // <<--- TROCA A TELA AQUI!
            stage.show();            // <<--- ATUALIZA A JANELA
            */
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/coretrackapp/TelaInicial.fxml"));
            Parent root = loader.load();

            coretrackapp.TelaInicialController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println("Erro ao abrir a tela principal: " + e.getMessage());
        }
    }

}
