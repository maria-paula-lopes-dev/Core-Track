package Utilitarios;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Avisos {

  // Método que exibe mensagens de alerta
  public static boolean msgInformacao(String msg, int tipo) {
    Alert.AlertType tipoAlerta;
    String tipomen;

    // Determina o tipo de alerta
    switch (tipo) {
      case 1:
        tipoAlerta = Alert.AlertType.CONFIRMATION;
        tipomen = "Sucesso!";
        break;
      case 2:
        tipoAlerta = Alert.AlertType.WARNING;
        tipomen = "Atenção!";
        break;
      case 3:
        tipoAlerta = Alert.AlertType.ERROR;
        tipomen = "Erro!";
        break;
      default:
        tipoAlerta = Alert.AlertType.INFORMATION;
        tipomen = "Informação";
    }

    Alert alerta = new Alert(tipoAlerta);
    alerta.setTitle("Mensagem");
    alerta.setHeaderText(tipomen);
    alerta.setContentText(msg);
    Optional<ButtonType> result = alerta.showAndWait();

    // Se for confirmação, retorna true ou false
    if (tipoAlerta == Alert.AlertType.CONFIRMATION) {
    //retorna verdadeiro ase clicou em button ok 
      if (result.get() == ButtonType.OK) {
        return true;
      }
    }
   // Para INFORMATION ou ERROR, só exibe a mensagem e retorna false por padrão
    return false;
  }

  public static void soTexto(KeyEvent event) {
    String character = event.getCharacter();
    if (!character.matches("[a-zA-Zá-úÁ-ÚçÇ ]") && event.getCode() != KeyCode.BACK_SPACE) {
      event.consume();  // Bloqueia a entrada do caractere inválido
    }
  }
}