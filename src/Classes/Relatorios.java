package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author maria
 */
public class Relatorios {

    private int idRelatorio;
    private int idUsuario;
    private String resumo;
    private String sugestoes;
    private LocalDateTime dataGeracao;

    // Construtor vazio 
    public Relatorios() {

    }

    // Construtor sem id 
    public Relatorios(int idUsuario, String resumo, String sugestoes) {
        this.idUsuario = idUsuario;
        this.resumo = resumo;
        this.sugestoes = sugestoes;
        this.dataGeracao = LocalDateTime.now(); // gerado automaticamente
    }

    // Construtor completo
    public Relatorios(int idRelatorio, int idUsuario, String resumo, String sugestoes, LocalDateTime dataGeracao) {
        this.idRelatorio = idRelatorio;
        this.idUsuario = idUsuario;
        this.resumo = resumo;
        this.sugestoes = sugestoes;
        this.dataGeracao = dataGeracao;
    }

    // GETTERS e SETTERS ------------------------------------
    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSugestoes() {
        return sugestoes;
    }

    public void setSugestoes(String sugestoes) {
        this.sugestoes = sugestoes;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
