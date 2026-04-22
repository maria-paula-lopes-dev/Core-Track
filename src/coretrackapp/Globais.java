package coretrackapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Globais {

    private static TelaInicialController telaInicialController;
    private static double RAM = 0;
    private static double mediaRAM = 0;
    private static double HD = 0;
    private static double mediaHD;
    private static String TempoUso;
    private static int contMed = 0;
    private static int id_usuario;

    public Globais() {
    }

    public static void incrementarMedia(double RAM, double HD) {
        Globais.mediaRAM = ((Globais.mediaRAM * Globais.contMed) + RAM) / (Globais.contMed + 1);
        Globais.mediaHD = ((Globais.mediaHD * Globais.contMed) + HD) / (Globais.contMed + 1);
        BigDecimal bdr = new BigDecimal(mediaRAM).setScale(2, RoundingMode.HALF_UP);
        BigDecimal bdh = new BigDecimal(mediaHD).setScale(2, RoundingMode.HALF_UP);
        mediaRAM = bdr.doubleValue();
        mediaHD = bdh.doubleValue();
        Globais.contMed++;
    }

    public static void reset() {
        mediaRAM = 0;
        mediaHD = 0;
        contMed = 0;
    }

// getter e setter de desempenho
    public static String getRelatorio() {
        String relatorio = "Uso médio de RAM: ";
        relatorio += String.valueOf(mediaRAM);
        relatorio += "\n";
        relatorio += "Uso médio de memória: ";
        relatorio += String.valueOf(mediaHD);
        relatorio += "\n";
        relatorio += "Tempo de sistema ativo: ";
        relatorio += TempoUso;
        return relatorio;
    }

    public static int getContMed() {
        return contMed;
    }

    public static void setContMed(int contMed) {
        Globais.contMed = contMed;
    }

    public static double getMediaRAM() {
        return mediaRAM;
    }

    public static void setMediaRAM(double mediaRAM) {
        Globais.mediaRAM = mediaRAM;
    }

    public static double getMediaHD() {
        return mediaHD;
    }

    public static void setMediaHD(double mediaHD) {
        Globais.mediaHD = mediaHD;
    }

    public static int getId_usuario() {
        return id_usuario;
    }

    public static void setId_usuario(int id_usuario) {
        Globais.id_usuario = id_usuario;
    }

    public static double getRAM() {
        return RAM;
    }

    public static void setRAM(double RAM) {
        Globais.RAM = RAM;
    }

    public static double getHD() {
        return HD;
    }

    public static void setHD(double HD) {
        Globais.HD = HD;
    }

    public static String getTempoUso() {
        return TempoUso;
    }

    public static void setTempoUso(String TempoUso) {
        Globais.TempoUso = TempoUso;
    }

// identificar a tela inicial
    public static TelaInicialController getController() {
        return telaInicialController;
    }

    public static void setController(TelaInicialController controller) {
        telaInicialController = controller;
    }

}
