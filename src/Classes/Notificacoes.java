package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author maria
 */
public class Notificacoes {

    private int idNotificacao;
    private int idUsuario;
    private String mensagem;
    private String tipo;
    private LocalDateTime dataEnvio;
    private boolean lida;

    // ===== CONSTRUTOR VAZIO =====
    public Notificacoes() {
    }

    // ===== CONSTRUTOR SEM ID NOTIFICAÇÃO=====
    public Notificacoes(int idUsuario, String mensagem, String tipo) {
        this.idUsuario = idUsuario;
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.dataEnvio = LocalDateTime.now();
    }
    
    // ===== CONSTRUTOR SEM ID===== (usado para mostrar a notificação na tela)
    public Notificacoes(String mensagem, String tipo, LocalDateTime dataEnvio) {
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.dataEnvio = dataEnvio;
    }
    
    
    // ===== CONSTRUTOR COMPLETO =====
    public Notificacoes(int idNotificacao, int idUsuario, String mensagem, String tipo,
            LocalDateTime dataEnvio, boolean lida) {
        this.idNotificacao = idNotificacao;
        this.idUsuario = idUsuario;
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
    }

    // ===== GETTERS E SETTERS =====
    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }
    @Override
    public String toString() {
        return tipo + " | " + mensagem;
    }
}
