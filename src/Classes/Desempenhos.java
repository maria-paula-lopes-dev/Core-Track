package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author maria
 */
public class Desempenhos {
    private int idDesempenho;   
    private int idUsuario;      
    private double usoCPU;         
    private double usoRAM;      
    private double usoDisco;
    private double tempoDeUso;      // novo
    private double temperatura;     // novo
    private LocalDateTime dataRegistro; 
    
    //contrutor vazio
    public Desempenhos() {
    }
    
    //sem id
    public Desempenhos(int idUsuario, double usoCPU, double usoRAM, double usoDisco, double tempoDeUso, double temperatura, LocalDateTime dataRegistro) {
        this.idUsuario = idUsuario;
        this.usoCPU = usoCPU;
        this.usoRAM = usoRAM;
        this.usoDisco = usoDisco;
        this.tempoDeUso = tempoDeUso;
        this.temperatura = temperatura;
        this.dataRegistro = dataRegistro;
        this.dataRegistro = LocalDateTime.now();
    }
    
    
    //completo
    public Desempenhos(int idDesempenho, int idUsuario, double usoCPU, double usoRAM, double usoDisco, double tempoDeUso, double temperatura, LocalDateTime dataRegistro) {
        this.idDesempenho = idDesempenho;
        this.idUsuario = idUsuario;
        this.usoCPU = usoCPU;
        this.usoRAM = usoRAM;
        this.usoDisco = usoDisco;
        this.tempoDeUso = tempoDeUso;
        this.temperatura = temperatura;
        this.dataRegistro = dataRegistro;
    }
    
    //métodos
    public int getIdDesempenho() {
        return idDesempenho;
    }

    public void setIdDesempenho(int idDesempenho) {
        this.idDesempenho = idDesempenho;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getUsoCPU() {
        return usoCPU;
    }

    public void setUsoCPU(double usoCPU) {
        this.usoCPU = usoCPU;
    }

    public double getUsoRAM() {
        return usoRAM;
    }

    public void setUsoRAM(double usoRAM) {
        this.usoRAM = usoRAM;
    }

    public double getUsoDisco() {
        return usoDisco;
    }

    public void setUsoDisco(double usoDisco) {
        this.usoDisco = usoDisco;
    }

    public double getTempoDeUso() {
        return tempoDeUso;
    }

    public void setTempoDeUso(double tempoDeUso) {
        this.tempoDeUso = tempoDeUso;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    
}
