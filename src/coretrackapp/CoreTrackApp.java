/**
 * Aqui, vai verificar de existe ID salvo
 * Se o ID existe -> abre a TelaInicial.fxml
 * Se não existe -> abre Dados.fxml pra preencher os dados
 *
 */
package coretrackapp;

import DAOs.UsuariosDAO;
import Utilitarios.SessaoUsuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author maria
 */
public class CoreTrackApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // 1 >> Carrega o ID salvo no arquivo txt
        int idSalvo = SessaoUsuario.carregarId();  // declara fora do try
        Parent root = null;                         // declara antes do try

        // 2 >> Verifica se já exeite um user salvo
        // 1°x no app -> abre Dados
        // User já existe -> abre a inicial
        // 3 >> Mostra a tela escolhida
        try {
            if (idSalvo == -1 || !UsuariosDAO.existePorId(idSalvo)) {
                root = FXMLLoader.load(getClass().getResource("/Fxml/Dados.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("/coretrackapp/TelaInicial.fxml"));
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            System.out.println("Erro ao carregar a tela inicial: " + e.getMessage());
            e.printStackTrace();
        }

         
         
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Dados.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
